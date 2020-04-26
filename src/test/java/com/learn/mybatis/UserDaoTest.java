package com.learn.mybatis;

import com.learn.mybatis.bean.User;
import com.learn.mybatis.dao.UserDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

public class UserDaoTest {
    static UserDao userDao;

    @BeforeAll
    static void initMybatis() throws IOException {
        InputStream stream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(stream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        userDao = sqlSession.getMapper(UserDao.class);
    }

    @Test
    void testGetUser() {
        User user = userDao.getUser(1);
        System.out.println(user);
        Assertions.assertEquals(1, user.getId());
    }
}
