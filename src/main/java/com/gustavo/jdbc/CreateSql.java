package com.gustavo.jdbc;

import java.text.MessageFormat;

public class CreateSql {
    public static final String sql = "insert into student values(''{0}'', ''name_{0}'');";
    public static void main(String[] args) {
        for (int i = 0; i < 200; i++) {
            System.out.println(MessageFormat.format(sql, String.valueOf(i)));
        }
    }
}
