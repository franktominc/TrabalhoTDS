package model.entity;

import com.avaje.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.*;
import javax.validation.Constraint;
import java.util.List;

/**
 * Created by ftominc on 7/17/17.
 */
@Entity
public class Subject extends Model{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Constraints.Required
    private String name;
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false)
    private Teacher teacher;


    @OneToMany(cascade = CascadeType.ALL)
    private List<Enrollment> enrollments;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ClassDate> classDates;

    public Subject(String name, Teacher teacher) {
        this.name = name;
        this.teacher = teacher;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }
}
