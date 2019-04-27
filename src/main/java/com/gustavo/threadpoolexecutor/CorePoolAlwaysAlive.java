package com.gustavo.threadpoolexecutor;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CorePoolAlwaysAlive {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 3, 2, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());
        System.out.println("core pool size: " + executor.getCorePoolSize());
        executor.submit(new MyRunnable());
        executor.submit(new MyRunnable());
        executor.submit(new MyRunnable());
        System.out.println("core pool size: " + executor.getCorePoolSize());
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("core pool size: " + executor.getCorePoolSize());
        executor.shutdown();
    }

    private static class MyRunnable implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " out...");
        }
    }
}
