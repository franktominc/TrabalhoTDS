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

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class AttendanceController extends Controller{
    @Inject
    FormFactory formFactory;
    StudentDao studentDao = new StudentDao(Student.class);
    ClassDateDao classDateDao = new ClassDateDao(ClassDate.class);
    SubjectDao subjectDao = new SubjectDao(Subject.class);
    EnrollmentDao enrollmentDao = new EnrollmentDao(Enrollment.class);

    public Result addAttendance(Long id){
        List<ClassDate> classDates = classDateDao.bySubjectId(id);
        Subject subject = subjectDao.byId(id);
        List<Student> enrolledStudents = enrollmentDao.all().stream()
                .filter(e -> e.getSubject().equals(subject))
                .map(p->p.getStudent().getId())
                .map(p->studentDao.byId(p))
                .collect(Collectors.toList());

        return ok(AddAttendanceView.render(id, enrolledStudents, classDates,  formFactory.form(Attendance.class)));
    }

    public Result newAttendance(Long id){
        DynamicForm dynamicForm = formFactory.form().bindFromRequest();
        Long studentId = Long.parseLong(dynamicForm.get("student"));
        Long classDate = Long.parseLong(dynamicForm.get("class_date"));
        Attendance attendance = new Attendance(studentDao.byId(studentId), subjectDao.byId(id), classDateDao.byId(classDate));
        attendance.save();
        return redirect(routes.SubjectController.viewSubject(id));
    }
}
