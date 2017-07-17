package model.entity;

import com.avaje.ebean.Model;
import play.data.format.Formats;
import play.data.validation.Constraints;

import java.util.Date;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Constraint;

/**
 * Created by ftominc on 7/17/17.
 */
@Entity
public class Student extends Model implements Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Constraints.Required
    private String Name;
    @Formats.DateTime(pattern = "dd/MM/yyyy")
    private Date Birthday;

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
}
