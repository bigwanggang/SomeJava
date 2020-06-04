package com.gustavo.distribute.mysql_distribute_lock;

import com.mysql.jdbc.TimeUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * 用mysql实现分布式锁
 */
public class DistributeLockDemo {
    public static void main(String[] args) {

        for (int i = 0; i < 200; i++) {
            Connection c = getConnection("jdbc:mysql://localhost:3306/test1", "root", "");

            Lock lock = null;
            try {
                lock = new MySqlLock(c);
            } catch (SQLException e) {
                continue;
            }

            IdGenerator generator = new IdGenerator(lock);

            GeneartorConsumer consumer = new GeneartorConsumer(generator);

            new Thread(consumer).start();

        }

        try {
            TimeUnit.SECONDS.sleep(60);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("id: " + IdGenerator.i);
    }

    public static Connection getConnection(String url, String user, String password) {
        Connection var1 = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            var1 = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException var3) {
            var3.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return var1;
    }
}
