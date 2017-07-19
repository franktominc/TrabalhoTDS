package model.dao;

import com.avaje.ebean.Model;
import model.entity.Subject;

/**
 * Created by ftominc on 7/18/17.
 */
public class SubjectDao extends Model.Finder<Long, Subject> {
    public SubjectDao(Class<Subject> type) {
        super(type);
    }

    public SubjectDao(String serverName, Class<Subject> type) {
        super(serverName, type);
    }
}
