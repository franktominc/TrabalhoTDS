package model.entity;

import com.avaje.ebean.Model;

import javax.persistence.*;


@Entity
public class Grade extends Model{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long Id;
    @ManyToOne(optional = false)
    private Subject subject;
    private Integer grade;
    @ManyToOne(optional = false)
    private Student student;


    public Grade(Student student, Subject subject, Integer grade) {
        this.subject = subject;
        this.grade = grade;
        this.student = student;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
