package model.entity;

import com.avaje.ebean.Model;
import play.data.format.Formats;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by ftominc on 7/17/17.
 */
@Entity
public class Teacher extends Model implements Person{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Constraints.Required
    private String name;
    @Formats.DateTime(pattern = "dd/MM/yyyy")
    private Date birthday;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "teacher")
    List<Subject> subjects;

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
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Date getBirthday() {
        return birthday;
    }

    @Override
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
