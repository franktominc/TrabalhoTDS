package model.entity;

import com.avaje.ebean.Model;
import play.data.validation.Constraints;

import java.util.Date;
import java.util.List;
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
}
