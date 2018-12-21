package com.gustavo.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.gustavo.mysql.InsertData.*;

public class ExecuteDemo {
    public static void main(String[] args) {
        Connection conn = Utils.getConn(JDBC_DRIVER, DB_URL, USER, PASS);
        String sql = "insert into person (name,age,address) values(?,?,?)";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "xiaoming");
            pstmt.setInt(2, 22);
            pstmt.setString(3, "shenzhen");
            System.out.println(pstmt.execute());
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
