package model.entity;

import com.avaje.ebean.Model;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.Year;

/**
 * Created by ftominc on 7/17/17.
 */
@Entity
public class Enrollment extends Model{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    private Student student;
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    private Subject subject;
    @DateTimeFormat(pattern = "yyyy")
    private Year year;
    private Boolean isPresenceRequired;

    public Enrollment(Student student, Subject subject, Year year, Boolean isPresenceRequired) {
        this.student = student;
        this.subject = subject;
        this.year = year;
        this.isPresenceRequired = isPresenceRequired;
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

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public Boolean getPresenceRequired() {
        return isPresenceRequired;
    }

    public void setPresenceRequired(Boolean presenceRequired) {
        isPresenceRequired = presenceRequired;
    }
}
