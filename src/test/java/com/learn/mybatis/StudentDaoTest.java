package com.learn.mybatis;

import com.learn.mybatis.dao.StudentDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

public class StudentDaoTest {
    static StudentDao studentDao;

    @BeforeAll
    static void initMybatis() throws IOException {
        InputStream stream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(stream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        studentDao = sqlSession.getMapper(StudentDao.class);
    }

    @Test
    void testGetStudent() {
        Assertions.assertEquals(1, studentDao.getStudentById(1).getId());
    }

    @Test
    void testGetStudents() {
        Assertions.assertNotNull(studentDao.getStudents());
    }
}
