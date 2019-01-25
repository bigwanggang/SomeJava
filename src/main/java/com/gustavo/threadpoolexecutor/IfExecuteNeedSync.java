package com.gustavo.threadpoolexecutor;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class IfExecuteNeedSync {
    static ThreadPoolExecutor threadpool = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
    static int i = 0;

    static synchronized void increment() {
        i++;
    }

    public static void main(String[] args) {
        int num = 100;
        Thread[] threads = new Thread[num];
        for (int j = 0; j < num; j++) {
            threads[j] = new Thread(new AddRunnable());
        }
        for (int j = 0; j < num; j++) {
            threads[j].start();
        }

        while (threadpool.getActiveCount() != 0)
            ;
        System.out.println(i);
        threadpool.shutdown();
    }

    static class AddRunnable implements Runnable {

        @Override
        public void run() {
            for (int j = 0; j < 1000; j++) {
                threadpool.execute(new MyRunnable());
            }
        }
    }

    static class MyRunnable implements Runnable {

        @Override
        public void run() {
            for (int j = 0; j < 10000; j++) {
                increment();
            }
        }
    }
}
