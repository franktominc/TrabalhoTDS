package model.dao;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Finder;
import model.entity.ClassDate;

import java.util.List;

public class ClassDateDao extends Finder<Long, ClassDate>{
    public ClassDateDao(Class<ClassDate> type) {
        super(type);
    }

    public ClassDateDao(Class<ClassDate> type, String serverName) {
        super(type, serverName);
    }

    public List<ClassDate> bySubjectId(Long id){
        return Ebean.find(ClassDate.class)
                .where()
                .eq("subject_id", id)
                .findList();
    }
}
