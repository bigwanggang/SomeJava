package com.bigwanggang.Jmockit;

import java.sql.Connection;
import java.sql.SQLException;

import static com.bigwanggang.Jmockit.DBUtils.*;

public class StaticClass {

    public static void getSqlResult() throws SQLException {
        Connection connection = DBUtils.getConn(driver, url, username, password);
        if (connection == null) {
            System.out.println("connection is null");
        }
        System.out.println(connection.getCatalog());
    }
}
