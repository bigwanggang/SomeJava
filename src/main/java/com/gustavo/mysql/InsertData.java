package com.gustavo.mysql;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertData {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/jpatest";

    static final String USER = "root";
    static final String PASS = "root123";

    public static void main(String[] args) {
        Connection conn = Utils.getConn(JDBC_DRIVER, DB_URL, USER, PASS);

        String sql = "insert into person (name,age,address) values(?,?,?)";
        PreparedStatement pstmt = null;
        Long start = System.currentTimeMillis();
        try {
            pstmt = conn.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 10000; i++) {
            try {
                pstmt.setString(1, "xiaoming" + i);
                pstmt.setString(2, "22");
                pstmt.setString(3, "shenzhen" + i);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println("time: " + String.valueOf(System.currentTimeMillis() - start));
        try {
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
