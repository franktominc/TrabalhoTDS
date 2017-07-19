package controllers;

import com.sun.org.apache.xpath.internal.operations.Bool;
import model.dao.StudentDao;
import model.dao.SubjectDao;
import model.entity.Enrollment;
import model.entity.Student;
import model.entity.Subject;
import play.data.DynamicForm;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.EnrollmentForm;

import javax.inject.Inject;
import java.time.Year;
import java.util.List;

/**
 * Created by ftominc on 7/19/17.
 */
public class EnrollmentController extends Controller {
    @Inject
    FormFactory formFactory;
    StudentDao studentDao = new StudentDao(Student.class);
    SubjectDao subjectDao = new SubjectDao(Subject.class);


    public Result newEnrollment(){
        Form<Enrollment> enrollmentForm = formFactory.form(Enrollment.class);
        return ok(EnrollmentForm.render(enrollmentForm, studentDao.findList(), subjectDao.findList()));
    }

    public Result addEnrollment(){
        DynamicForm requestData = formFactory.form().bindFromRequest();
        Long studentId = Long.parseLong(requestData.get("student"));
        Long subjectId = Long.parseLong(requestData.get("subject"));
        Integer year = Integer.parseInt(requestData.get("year"));
        Boolean isPresenceRequired = Boolean.parseBoolean(requestData.get("isPresenceRequired"));

        Enrollment enrollment = new Enrollment(studentDao.byId(studentId), subjectDao.byId(subjectId), Year.of(year) ,isPresenceRequired);
        enrollment.save();

        return redirect("/students");
    }
    /*
    public Result addSubject(){

        String name = requestData.get("name");
        Long teacherId = Long.parseLong(requestData.get("teacher"));

        Subject subject = new Subject(name, teacherDao.byId(teacherId));
        subject.save();
        return redirect("/subjects");
    }
    */
}
