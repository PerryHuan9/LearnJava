package com.learn.mybatis.dao;

import com.learn.mybatis.bean.Student;

import java.util.List;

public interface StudentDao {
    Student getStudentById(long id);

    List<Student> getStudents();
}
