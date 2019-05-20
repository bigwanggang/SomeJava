package com.gustavo.multithread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 线程池能执行两个线程，用5个线程执行countDown，说明countDown是不阻塞的
 */
public class CountDownLatchDemo1 {

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(5);
        ExecutorService service = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 5; i++) {
            service.submit(new MyRunnable(latch));
        }
        System.out.println("main waiting----" + System.currentTimeMillis());
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main over----" + System.currentTimeMillis());
        service.shutdown();

    }

    static class MyRunnable implements Runnable {
        CountDownLatch latch;

        public MyRunnable(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " " + System.currentTimeMillis());
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latch.countDown();
        }
    }


}
