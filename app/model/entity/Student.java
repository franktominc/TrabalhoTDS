package model.entity;

import com.avaje.ebean.Model;
import org.springframework.format.annotation.DateTimeFormat;
import play.data.format.Formats;
import play.data.validation.Constraints;

import java.util.Date;
import java.util.List;


import javax.persistence.*;
import javax.validation.Constraint;

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
    private List<Presence> presences;
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

    public List<Presence> getPresences() {
        return presences;
    }

    public void setPresences(List<Presence> presences) {
        this.presences = presences;
    }
}
