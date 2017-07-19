package controllers;

import model.dao.ClassDateDao;
import model.dao.EnrollmentDao;
import model.dao.StudentDao;
import model.dao.SubjectDao;
import model.entity.ClassDate;
import model.entity.Enrollment;
import model.entity.Student;
import model.entity.Subject;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.ResultView;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class ResultController extends Controller{

    @Inject
    FormFactory formFactory;
    StudentDao studentDao = new StudentDao(Student.class);
    ClassDateDao classDateDao = new ClassDateDao(ClassDate.class);
    SubjectDao subjectDao = new SubjectDao(Subject.class);
    EnrollmentDao enrollmentDao = new EnrollmentDao(Enrollment.class);

    public Result computeResult(Long subjectId){
        Subject subject = subjectDao.byId(subjectId);
        List<Student> enrolledStudents = enrollmentDao.all().stream()
                .filter(e -> e.getSubject().equals(subject))
                .map(p->p.getStudent().getId())
                .map(p->studentDao.byId(p))
                .collect(Collectors.toList());
        return ok(ResultView.render(subject, enrolledStudents));
    }
}
