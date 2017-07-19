package controllers;

import model.dao.ClassDateDao;
import model.dao.EnrollmentDao;
import model.dao.StudentDao;
import model.dao.SubjectDao;
import model.entity.*;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.AddAttendanceView;
import views.html.AddGradeView;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class GradeController extends Controller {
    @Inject
    FormFactory formFactory;
    StudentDao studentDao = new StudentDao(Student.class);
    ClassDateDao classDateDao = new ClassDateDao(ClassDate.class);
    SubjectDao subjectDao = new SubjectDao(Subject.class);
    EnrollmentDao enrollmentDao = new EnrollmentDao(Enrollment.class);

    public Result addGrade(Long id){
        Subject subject = subjectDao.byId(id);
        List<Student> enrolledStudents = enrollmentDao.all().stream()
                .filter(e -> e.getSubject().equals(subject))
                .map(p->p.getStudent().getId())
                .map(p->studentDao.byId(p))
                .collect(Collectors.toList());

        return ok(AddGradeView.render(id, enrolledStudents, formFactory.form(Grade.class)));
    }

    public Result newGrade(Long id){
        DynamicForm dynamicForm = formFactory.form().bindFromRequest();
        Long studentId = Long.parseLong(dynamicForm.get("student"));
        Integer grade = Integer.parseInt(dynamicForm.get("grade"));
        Grade grade1 = new Grade(studentDao.byId(studentId),subjectDao.byId(id), grade);
        grade1.save();
        return redirect(routes.SubjectController.viewSubject(id));
    }


}
