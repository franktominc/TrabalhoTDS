package controllers;

import com.avaje.ebean.EbeanServer;
import model.dao.ClassDateDao;
import model.dao.SubjectDao;
import model.dao.TeacherDao;
import model.entity.ClassDate;
import model.entity.Subject;
import model.entity.Teacher;
import org.joda.time.DateTime;
import play.Logger;
import play.data.DynamicForm;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by ftominc on 7/18/17.
 */
public class SubjectController extends Controller {
    @Inject
    private FormFactory formFactory;
    private SubjectDao subjectDao = new SubjectDao(Subject.class);
    private TeacherDao teacherDao = new TeacherDao(Teacher.class);
    private ClassDateDao classDateDao = new ClassDateDao(ClassDate.class);
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
    public Result listClassDates(Long id){
        List<ClassDate> classDates = classDateDao.bySubjectId(id);
        return ok(ListClassDatesView.render(classDates));
    }
    public Result newClassDate(Long id){
        Form<ClassDate> classDateForm = formFactory.form(ClassDate.class);
        return ok(AddClassDateForm.render(classDateForm, id));
    }
    public Result addClassDate(Long id){
        DynamicForm form = formFactory.form().bindFromRequest();
        String date1 = form.get("date");
        System.out.println(date1);
        DateTime date = DateTime.parse(date1);
        ClassDate classDate = new ClassDate(date.toDate(), subjectDao.byId(id));
        classDate.save();
        return redirect("/subject/"+classDate.getSubject().getId());
    }
}
