package model.entity;

import com.avaje.ebean.Model;

import javax.persistence.*;

/**
 * Created by ftominc on 7/17/17.
 */
@Entity
public class Attendance extends Model {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(optional = false)
    private Student student;
    @ManyToOne(optional = false)
    private Subject subject;
    @ManyToOne(optional = false)
    private ClassDate classDate;


    public Attendance(Student student,Subject subject, ClassDate classDate) {
        this.student = student;
        this.classDate = classDate;
        this.subject = subject;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public ClassDate getClassDate() {
        return classDate;
    }

    public void setClassDate(ClassDate classDate) {
        this.classDate = classDate;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
