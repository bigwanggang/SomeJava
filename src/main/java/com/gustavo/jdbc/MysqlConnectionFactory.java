package com.gustavo.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlConnectionFactory {
    private static String driver = "com.mysql.jdbc.Driver";

    private MysqlConnectionFactory() {
    }

    public static Connection getConnection(String url, String user, String password) {
        Connection var1 = null;

        try {
            Class.forName(driver);
            var1 = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException var3) {
            var3.printStackTrace();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return var1;
    }
}
