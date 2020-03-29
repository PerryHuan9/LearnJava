package com.learn.jdbc;

import java.sql.*;

public class TestJdbc {
    static String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/learnjdbc";
    static String JDBC_USER = "root";
    static String JDBC_PASSWORD = "123456";

    public static void learn() {
        query();
        insert();
        update();
        delete();
        transaction();
        batch();
    }

    public static void query() {
        System.out.println("id\tgrade\tname\tgender");
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet set = statement.executeQuery("SELECT id, grade, name, gender FROM students WHERE gender=\'M\'")) {
                    while (set.next()) {
                        long id = set.getLong(1); // 注意：索引从1开始
                        long grade = set.getLong(2);
                        String name = set.getString(3);
                        String gender = set.getString(4);
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder
                                .append(id).append("\t")
                                .append(grade).append("\t")
                                .append(name).append("\t\t")
                                .append(gender).append("\t");
                        System.out.println(stringBuilder.toString());

                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("************");
        // Statement有SQL注入的风险，应该使用PrepareStatement
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            try (PreparedStatement ps = conn.prepareStatement(
                    "select id, grade, name, gender from students where gender=? and grade=?")) {
                ps.setObject(1, 1);
                ps.setObject(2, 3);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        long id = rs.getLong(1); // 注意：索引从1开始
                        long grade = rs.getLong(2);
                        String name = rs.getString(3);
                        String gender = rs.getString(4);
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder
                                .append(id).append("\t")
                                .append(grade).append("\t")
                                .append(name).append("\t\t")
                                .append(gender).append("\t");
                        System.out.println(stringBuilder.toString());
                    }
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void insert() {
        String insertSql = "insert into students (name, grade, gender, score) values (?,?,?,?)";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            try (PreparedStatement ps = connection.prepareStatement(insertSql)) {
                ps.setObject(1, "Perry Huang");
                ps.setObject(2, 15);
                ps.setObject(3, 1);
                ps.setObject(4, 100);
                int result = ps.executeUpdate();
                int result1 = ps.executeUpdate();
                System.out.println(result);
                System.out.println(result1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 插入并获取主键
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            try (PreparedStatement ps = conn.prepareStatement(insertSql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                ps.setObject(1, "黄益凛");
                ps.setObject(2, 14);
                ps.setObject(3, 0);
                ps.setObject(4, 99);
                ps.executeUpdate();
                try (ResultSet set = ps.getGeneratedKeys()) {
                    while (set.next()) {
                        long id = set.getLong(1);
                        System.out.println("插入数据的主键：" + id);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void update() {
        String updateSql = "update students set gender=? where id=?";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            try (PreparedStatement ps = connection.prepareStatement(updateSql)) {
                ps.setObject(1, 1);
                ps.setObject(2, 1007);
                ps.executeUpdate();
            }
            try (PreparedStatement ps = connection.prepareStatement("select * from students where id=?")) {
                ps.setObject(1, 1007);
                ps.executeQuery();
                try (ResultSet set = ps.getResultSet()) {
                    while (set.next()) {
                        System.out.println(set.getLong(1));
                        System.out.println(set.getString(2));
                        System.out.println(set.getInt(3));
                        System.out.println(set.getInt(4));
                        System.out.println(set.getInt(5));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void delete() {
        String deleteSql = "delete from students where id=?";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            try (PreparedStatement ps = connection.prepareStatement(deleteSql)) {
                ps.setObject(1, 1004);
                int result = ps.executeUpdate();
                System.out.println(result);
                ps.setObject(1, 1010);
                result = ps.executeUpdate();
                System.out.println(result);
                ps.setObject(1, 10000);
                result = ps.executeUpdate();
                System.out.println(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }

    /**
     * 事务
     */
    public static void transaction() {
        try ( Connection connection = getConnection()){
            connection.setAutoCommit(false); // 关闭自动提交，即事务开启
            try (PreparedStatement ps = connection.prepareStatement(
                    "insert into students (name, gender, grade, score) values (?,?,?,?)")) {
                ps.setObject(1, "黄益威");
                ps.setObject(2, 1);
                ps.setObject(3, 9);
                ps.setObject(4, 88);
                ps.executeUpdate();
            }
            try (PreparedStatement ps = connection.prepareStatement(
                    "update students set name=? where id=?")) {
                ps.setObject(1, "龙傲天");
                ps.setObject(2, 3);
                ps.executeUpdate();
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 批量操作
     */
    public static void batch() {
        try(Connection connection = getConnection()) {
            try(PreparedStatement ps = connection.prepareStatement(
                    "insert into students (name, grade, gender, score) values (?,?,?,?)")) {
                Object[][] data = {{"1",12, 1,38},{"2",12, 1,38},{"3",12, 1,38}};
                for (Object[] row: data) {
                    for (int i=0;i<row.length;i++) {
                        ps.setObject(i+1, row[i]);
                    }
                    ps.addBatch();
                }
                ps.executeBatch();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
