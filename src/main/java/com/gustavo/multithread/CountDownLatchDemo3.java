package com.gustavo.multithread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * CountDownLatchDemo1栗子是5个线程countDown，一个线程等待，本栗子是把等待的线程改为3个
 * 线程池能执行两个线程，用5个线程执行countDown，说明countDown是不阻塞的
 */
public class CountDownLatchDemo3 {

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(5);
        ExecutorService countDownService = Executors.newFixedThreadPool(2);
        ExecutorService waitService = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 3; i++) {
            waitService.submit(new Waitinging(latch));
        }

        for (int i = 0; i < 5; i++) {
            countDownService.submit(new CountDownRunning(latch));
        }

        countDownService.shutdown();
        waitService.shutdown();

    }

    static class CountDownRunning implements Runnable {
        CountDownLatch latch;

        public CountDownRunning(CountDownLatch latch) {
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

    private static class Waitinging implements Runnable {
        private CountDownLatch latch;

        public Waitinging(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            System.out.println("in " + Thread.currentThread().getName() + "--" + System.currentTimeMillis());
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("out " + Thread.currentThread().getName() + "--" + System.currentTimeMillis());
        }
    }


}
