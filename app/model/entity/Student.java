package model.entity;

import com.avaje.ebean.Model;
import model.dao.ClassDateDao;
import model.dao.SubjectDao;
import play.Logger;
import play.data.validation.Constraints;

import java.util.Date;
import java.util.List;
import java.util.OptionalDouble;
import java.util.function.BinaryOperator;
import java.util.function.DoubleSupplier;
import java.util.stream.Collectors;


import javax.persistence.*;

/**
 * Created by ftominc on 7/17/17.
 */
@Entity
public class Student extends Model implements Person {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Constraints.Required
    private String Name;
    private Date Birthday;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Enrollment> enrollments;
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Attendance> attendances;
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Grade> grades;

    public Student(String name, Date birthday) {
        Name = name;
        Birthday = birthday;
    }
    @Override
    public Long getId() {
        return id;
    }
    @Override
    public void setId(Long id) {
        this.id = id;
    }
    @Override
    public String getName() {
        return Name;
    }
    @Override
    public void setName(String name) {
        Name = name;
    }
    @Override
    public Date getBirthday() {
        return Birthday;
    }
    @Override
    public void setBirthday(Date birthday) {
        Birthday = birthday;
    }

    @Override
    public String toString() {
        return "Student(" + Name + "," + Birthday + ")";
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    public List<Attendance> getAttendances() {
        return attendances;
    }

    public void setAttendances(List<Attendance> attendances) {
        this.attendances = attendances;
    }

    public List<Attendance> getAttendancesBySubject(Long subjectId){
        return this.attendances.stream().filter(x -> x.getSubject().getId() == subjectId).collect(Collectors.toList());
    }
    public Double getAttendancePercentBySubject(Long subjectId){
        List<ClassDate> classDates = new ClassDateDao(ClassDate.class).bySubjectId(subjectId);
        List<Attendance> collect = this.attendances.stream().filter(x -> x.getSubject().getId() == subjectId).collect(Collectors.toList());
        if(classDates.size() <= 0) return 0.0;
        else return collect.size() / (classDates.size() * 1.0);
    }
    public Integer getGradeBySubject(Long subjectId){
        List<Grade> grades = this.grades.stream().filter(x -> x.getSubject().getId() == subjectId).collect(Collectors.toList());
        OptionalDouble average = grades.stream().mapToInt(Grade::getGrade).average();
        Double asDouble = average.getAsDouble();
        return asDouble.intValue();
    }
}
