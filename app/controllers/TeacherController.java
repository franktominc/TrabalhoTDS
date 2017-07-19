package controllers;

import model.dao.TeacherDao;
import model.entity.Student;
import model.entity.Teacher;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by ftominc on 7/18/17.
 */
public class TeacherController extends Controller {
    private final FormFactory formFactory;
    private TeacherDao teacherDao;

    @Inject()
    public TeacherController(FormFactory formFactory) {
        this.formFactory = formFactory;
        teacherDao = new TeacherDao(Teacher.class);
    }

    public Result addTeacher(){
        Form<Teacher> teacherForm = formFactory.form(Teacher.class);
        Teacher teacher = teacherForm.bindFromRequest().get();
        teacher.save();
        return redirect("/teachers");
    }

    public Result newTeacher(){
        Form<Teacher> teacherForm = formFactory.form(Teacher.class);
        return ok(TeacherForm.render(teacherForm));
    }

    public Result listTeachers(){
        List<Teacher> teachers = teacherDao.findList();
        teachers.sort(((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName())));
        return ok(TeacherTable.render(teachers));
    }

    public Result removeTeacher(Long id){
        teacherDao.deleteById(id);
        return redirect("/teachers");
    }

    public Result viewTeacher(Long id){
        Teacher teacher = teacherDao.byId(id);

        return ok(TeacherView.render(teacher));
    }

    public Result editTeacher(Long id){
        Teacher teacher = teacherDao.byId(id);
        Form<Teacher> teacherForm= formFactory.form(Teacher.class).fill(teacher);
        return ok(EditTeacherForm.render(teacherForm));
    }

    public Result updateTeacher(Long id){
        Teacher teacher = formFactory.form(Teacher.class).bindFromRequest().get();
        teacher.update();
        return redirect("/teachers");
    }
}
