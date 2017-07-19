package controllers;

import com.avaje.ebean.EbeanServer;
import model.dao.SubjectDao;
import model.dao.TeacherDao;
import model.entity.Subject;
import model.entity.Teacher;
import play.data.DynamicForm;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.SubjectForm;
import views.html.SubjectTable;
import views.html.SubjectView;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by ftominc on 7/18/17.
 */
public class SubjectController extends Controller {
    @Inject
    private FormFactory formFactory;
    private SubjectDao subjectDao = new SubjectDao(Subject.class);
    private TeacherDao teacherDao = new TeacherDao(Teacher.class);
    public Result listSubjects(){
        List<Subject> subjects = subjectDao.findList();
        subjects.sort((o1,o2) -> o1.getName().compareToIgnoreCase(o2.getName()));
        return ok(SubjectTable.render(subjects));
    }

    public Result newSubject(){
        Form<Subject> subjectForm = formFactory.form(Subject.class);
        return ok(SubjectForm.render(subjectForm, teacherDao.findList()));
    }

    public Result addSubject(){
        DynamicForm requestData = formFactory.form().bindFromRequest();
        String name = requestData.get("name");
        Long teacherId = Long.parseLong(requestData.get("teacher"));

        Subject subject = new Subject(name, teacherDao.byId(teacherId));
        subject.save();
        return redirect("/subjects");
    }

    public Result viewSubject(Long id){
        Subject subject = subjectDao.byId(id);

        return ok(SubjectView.render(subject));
    }
}
