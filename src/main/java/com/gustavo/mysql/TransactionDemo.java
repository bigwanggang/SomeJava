package com.gustavo.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.gustavo.mysql.InsertData.*;

public class TransactionDemo {
    public static void main(String[] args) {
        Connection conn = Utils.getConn(JDBC_DRIVER, DB_URL, USER, PASS);

        String sql = "insert into person (name,age,address) values(?,?,?)";
        PreparedStatement pstmt = null;
        try {
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "xiaoming");
            pstmt.setInt(2, 22);
            pstmt.setString(3, "shenzhen");
            System.out.println(pstmt.executeUpdate());

            pstmt.setString(1, "xiaoming");
            pstmt.setInt(2, 22);
            pstmt.setString(3, null);
            System.out.println(pstmt.executeUpdate());

            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

        }
        try {
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        try {
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
