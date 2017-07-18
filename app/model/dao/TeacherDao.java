package model.dao;

import com.avaje.ebean.Model;
import model.entity.Teacher;

/**
 * Created by ftominc on 7/18/17.
 */
public class TeacherDao extends Model.Finder<Long, Teacher> {
    public TeacherDao(Class<Teacher> type) {
        super(type);
    }

    public TeacherDao(String serverName, Class<Teacher> type) {
        super(serverName, type);
    }
}
