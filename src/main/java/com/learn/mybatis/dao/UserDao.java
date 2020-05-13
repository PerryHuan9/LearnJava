package com.learn.mybatis.dao;

import com.learn.mybatis.bean.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserDao {
    @Select("SELECT * FROM users Where id = #{id}")
    User getUser(long id);


    @Select("SELECT * FROM users")
    List<User> getUsers();


}
