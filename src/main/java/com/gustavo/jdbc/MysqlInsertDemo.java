package com.gustavo.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.gustavo.jdbc.MulthreadGetConnection.getConnection;

public class MysqlInsertDemo {
    public static void main(String[] args) {
        Connection c = null;
        try {
            c = getConnection("jdbc:mysql://localhost:3306/test", "root", "");
        } catch (Throwable e) {
            System.out.println("get connection failure");
        }

        String sql = "insert into t values (?)";
        try {
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, "hello");
            boolean b = ps.execute();
            if (b) {
                System.out.println("execute success");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                c.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

}
