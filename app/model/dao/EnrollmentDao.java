package model.dao;

import com.avaje.ebean.Model;
import model.entity.Enrollment;

/**
 * Created by ftominc on 7/19/17.
 */
public class EnrollmentDao extends Model.Finder<Long, Enrollment> {
    public EnrollmentDao(Class<Enrollment> type) {
        super(type);
    }

    public EnrollmentDao(String serverName, Class<Enrollment> type) {
        super(serverName, type);
    }
}
