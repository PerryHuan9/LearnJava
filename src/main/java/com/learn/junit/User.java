package com.learn.junit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class User {
    public static String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/test";
    public static String JDBC_USER = "root";
    public static String JDBC_PASSWORD = "123456";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }

    public static boolean executeUpdate(String sql, Object... os) {
        try (Connection connection = getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                for (int i = 0; i < os.length; i++) {
                    ps.setObject(i + 1, os[i]);
                }
                ps.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS user (" +
                "id INT UNSIGNED AUTO_INCREMENT," +
                "name varchar(50) not null," +
                "age varchar(10)," +
                "sex varchar(20)" +
                ")";
        return executeUpdate(sql);
    }

    public static  boolean dropTable() {
        String sql = "drop table user if exits";
        return  executeUpdate(sql);
    }

    public boolean addUser(String name, String sex, int age) {
        return executeUpdate("insert into user(name, sex, age) values (?, ?, ?)", name, sex, age);
    }
}
