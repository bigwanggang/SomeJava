package com.gustavo.Jmockit;

import java.sql.SQLException;

public class MyObject {
    public String hello(String name) {
        return "Hello " + name;
    }

    public static int tripple(int i) {
        return i * 3;
    }

    public String returnString() {
        return "String";
    }

    public void underTest() throws SQLException {
        StaticClass.getSqlResult();
    }

}
