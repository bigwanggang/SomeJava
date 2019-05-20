package com.gustavo.multithread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 验证是否count到0，await就立即执行还是要等所有countDown的线程执行完之后，await才执行
 */
public class CountDownLatchDemo2 {

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(2);
        ExecutorService service = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 2; i++) {
            service.submit(new MyRunnable(latch));
        }
        System.out.println("main waiting----" + System.currentTimeMillis());
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main over-------" + System.currentTimeMillis());
        service.shutdown();

    }

    static class MyRunnable implements Runnable {
        CountDownLatch latch;

        public MyRunnable(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            latch.countDown();
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " " + System.currentTimeMillis());
        }
    }


}
