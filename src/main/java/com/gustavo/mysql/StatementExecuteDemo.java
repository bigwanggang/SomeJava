package com.gustavo.mysql;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static com.gustavo.mysql.InsertData.*;

public class StatementExecuteDemo {
    public static void main(String[] args) {
        Connection conn = Utils.getConn(JDBC_DRIVER, DB_URL, USER, PASS);
        String sql = "insert into person (name,age,address) values('xiaoming', 22, 'shenzhen')";
        Statement pstmt = null;
        try {
            pstmt = conn.createStatement();

            System.out.println(pstmt.execute(sql));
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
