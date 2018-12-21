package com.gustavo.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.gustavo.mysql.InsertData.*;

public class InsertDataMultithread {
    public static void main(String[] args) {
        Connection conn = Utils.getConn(JDBC_DRIVER, DB_URL, USER, PASS);
        String sql = "insert into person (name,age,address) values(?,?,?)";
        Thread[] threads = new Thread[10];
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            Long start = System.currentTimeMillis();
            for (int i = 0; i < 10; i++) {
                threads[i] = new Thread(new InsertRunnable(i * 1000, i * 1000 + 999, pstmt));
            }
            for (int i = 0; i < 10; i++) {
                threads[i].start();
            }
            for (int i = 0; i < 10; i++) {
                threads[i].join();
            }

            System.out.println(System.currentTimeMillis() - start);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    static class InsertRunnable implements Runnable {
        int start;
        int end;
        PreparedStatement pstmt;

        public InsertRunnable(int start, int end, PreparedStatement pstmt) {
            this.start = start;
            this.end = end;
            this.pstmt = pstmt;
        }

        @Override
        public void run() {
            for (int i = start; i <= end; i++) {
                try {
                    pstmt.setString(1, "xiaoming" + i);
                    pstmt.setString(2, "22");
                    pstmt.setString(3, "shenzhen" + i);
                    pstmt.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
