package com.gustavo.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
* 多线程操作数据库
**/
public class MulthreadGetConnection {
    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Connection c = getConnection("jdbc:mysql://localhost:3306/test", "root", "");
                    PreparedStatement ps = null;
                    try {
                        ps = c.prepareStatement("insert into test(a,b) values(?,?)");
                        ps.setString(1, Thread.currentThread().getName());
                        ps.setString(2, Thread.currentThread().getName());
                        int result = ps.executeUpdate();
                        if (result > 0) {
                            System.out.println(Thread.currentThread().getName() + " insert success");
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
            }).start();
        }
    }

    public static Connection getConnection(String url, String user, String password) {
        Connection var1 = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            var1 = DriverManager.getConnection(url, user, password);
            System.out.println(Thread.currentThread().getName() + " get connectin: " + var1);

        } catch (ClassNotFoundException var3) {
            var3.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return var1;
    }
}
