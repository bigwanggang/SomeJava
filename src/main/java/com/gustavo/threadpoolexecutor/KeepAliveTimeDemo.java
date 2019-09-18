package com.gustavo.threadpoolexecutor;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 测试线程池的keepalivetime 为0时， 是否为一直不回收核心线程
 * Created by gustaov on 2019/9/18.
 */
public class KeepAliveTimeDemo {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(0, 200, 30, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
        System.out.println(executor.getCorePoolSize());
        for (int i = 0; i < 30; i++) {
            executor.execute(new MyRunning());
        }
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(executor.getCorePoolSize());
    }

    static class MyRunning implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
        }
    }
}
