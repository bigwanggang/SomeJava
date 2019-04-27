package com.gustavo.multithread.join;

import java.util.concurrent.TimeUnit;

public class JoinTimeDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new MyRunnable());
        t.start();
        t.join(1000);
        System.out.println("    main time: " + System.currentTimeMillis());
    }

    static class MyRunnable implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " time: " + System.currentTimeMillis());
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " time: " + System.currentTimeMillis());
        }
    }
}
