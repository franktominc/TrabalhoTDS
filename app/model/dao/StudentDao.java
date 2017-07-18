package model.dao;

import com.avaje.ebean.Model;
import model.entity.Student;

/**
 * Created by ftominc on 7/17/17.
 */
public class StudentDao extends Model.Finder<Long, Student> {
    public StudentDao(Class<Student> type) {
        super(type);
    }

    public StudentDao(String serverName, Class<Student> type) {
        super(serverName, type);
    }
}
