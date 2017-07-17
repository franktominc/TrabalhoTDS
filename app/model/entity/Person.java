package model.entity;

import java.util.Date;

/**
 * Created by ftominc on 7/17/17.
 */
public interface Person {
    Long getId();
    void setId(Long id);
    String getName();
    void setName(String name);
    Date getBirthday();
    void setBirthday(Date birthday) ;
}
