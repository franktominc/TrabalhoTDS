package controllers;


import com.avaje.ebean.EbeanServer;
import model.dao.StudentDao;
import model.entity.Student;
import play.Logger;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.StudentTable;
import views.html.index;
import views.html.studentform;

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
        return ok(studentform.render(studentForm));
    }

    public Result listStudents(){
        List<Student> studentList = studentDao.findList();
        studentList.sort((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()));
        return ok(StudentTable.render(studentList));
    }
}
