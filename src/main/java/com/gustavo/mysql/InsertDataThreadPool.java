package com.gustavo.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.gustavo.mysql.InsertData.*;

public class InsertDataThreadPool {
    public static void main(String[] args) {
        Connection conn = Utils.getConn(JDBC_DRIVER, DB_URL, USER, PASS);
        ExecutorService service = Executors.newFixedThreadPool(2);
        CountDownLatch latch = new CountDownLatch(10);
        String sql = "insert into person (name,age,address) values(?,?,?)";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            Long start = System.currentTimeMillis();
            for (int i = 0; i < 10; i++) {
                service.execute(new InsertRunnable(i * 1000, i * 1000 + 999, pstmt, latch));
            }
            latch.await();
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
        CountDownLatch latch;

        public InsertRunnable(int start, int end, PreparedStatement pstmt, CountDownLatch latch) {
            this.start = start;
            this.end = end;
            this.pstmt = pstmt;
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                for (int i = start; i <= end; i++) {

                    pstmt.setString(1, "xiaoming" + i);
                    pstmt.setString(2, "22");
                    pstmt.setString(3, "shenzhen" + i);
                    pstmt.executeUpdate();

                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                latch.countDown();
            }
        }
    }
}
