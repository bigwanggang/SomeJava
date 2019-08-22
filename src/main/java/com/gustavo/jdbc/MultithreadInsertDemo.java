package com.gustavo.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.gustavo.jdbc.MulthreadGetConnection.getConnection;

/**
 * 测试100个线程每个线程向数据库插入100条记录，共1w条记录，
 * 比较InnoDB和MyISAM两种存储引擎哪个快
 * 测试中先通过下面的脚本生成表，默认存储引擎为InnoDB
 create table test(
  value varchar(30)
  );
    跑完把表的存储引擎改为MyISAM，然后再重新执行一遍
    MyISAM为4秒，InnoDB为54秒，为什么差别这么大？？
 */
public class MultithreadInsertDemo {
    public static void main(String[] args) {
        Connection c = null;
        try {
            c = getConnection("jdbc:mysql://localhost:3306/test", "root", "");
        } catch (Throwable e) {
            System.out.println("get connection failure");
        }
        Thread[] threads = new Thread[100];
        for (int i = 0; i < 100; i++) {
            threads[i] = new Thread(new InsertRunnable(c, "Thread_" + i));
        }
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            threads[i].start();
        }
        for (int i = 0; i < 100; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(System.currentTimeMillis() - start);
        try {
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static class InsertRunnable implements Runnable {
        private Connection c;
        private String name;

        public InsertRunnable(Connection c, String name) {
            this.c = c;
            this.name = name;
        }

        @Override
        public void run() {
            try {
                PreparedStatement ps = c.prepareStatement("insert into test(value) values(?)");
                for (int i = 0; i < 100; i++) {
                    ps.setString(1, name + "###" + i);
                    ps.execute();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
