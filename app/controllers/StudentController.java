package controllers;


import com.avaje.ebean.EbeanServer;
import model.dao.StudentDao;
import model.entity.Student;
import play.Logger;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

import javax.inject.Inject;
import java.util.Comparator;
import java.util.List;

/**
 * Created by ftominc on 7/17/17.
 */

public class StudentController extends Controller {

    private final FormFactory formFactory;
    private final StudentDao studentDao;

    @Inject()
    public StudentController(FormFactory formFactory) {
        this.formFactory = formFactory;
        studentDao = new StudentDao(Student.class);
    }

    public Result addStudent(){
        Form<Student> studentForm = formFactory.form(Student.class);
        Student student = studentForm.bindFromRequest().get();
        student.save();
        return redirect("/students");
    }

    public Result newStudent() {
        Form<Student> studentForm = formFactory.form(Student.class);
        return ok(CreateStudentForm.render(studentForm));
    }

    public Result listStudents(){
        List<Student> studentList = studentDao.findList();
        studentList.sort((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()));
        return ok(StudentTable.render(studentList));
    }
    public Result removeStudent(Long id){
        studentDao.deleteById(id);
        return redirect("/students");
    }

    public Result viewStudent(Long id){
        Student student = studentDao.byId(id);

        return ok(StudentView.render(student));
    }
    public Result editStudent(Long id){
        Student student = studentDao.byId(id);
        Form<Student> studentForm = formFactory.form(Student.class).fill(student);
        return ok(EditStudentForm.render(studentForm));
    }

    public Result updateStudent(Long id){
        Student student = formFactory.form(Student.class).bindFromRequest().get();
        student.update();
        return redirect("/students");
    }
}
