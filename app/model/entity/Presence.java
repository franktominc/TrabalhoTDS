package model.entity;

import com.avaje.ebean.Model;

import javax.persistence.*;

/**
 * Created by ftominc on 7/17/17.
 */
@Entity
public class Presence extends Model {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    private Student student;
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    private ClassDate classDate;


    public Presence(Student student, ClassDate classDate) {
        this.student = student;
        this.classDate = classDate;
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
}
