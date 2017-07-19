package model.entity;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Grade extends Model{

    @Id
    private Long Id;
    @ManyToOne(optional = false)
    Enrollment enrollment;
    Integer grade;

    public Grade(Long id, Enrollment enrollment, Integer grade) {
        Id = id;
        this.enrollment = enrollment;
        this.grade = grade;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Enrollment getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(Enrollment enrollment) {
        this.enrollment = enrollment;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }
}
