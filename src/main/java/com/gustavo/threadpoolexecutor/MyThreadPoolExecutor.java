package com.gustavo.threadpoolexecutor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 扩展ThreadPoolExecutor
 * 可以在线程池执行任务前后增加处理逻辑
 * Created by gustaov on 2019/9/18.
 */
public class MyThreadPoolExecutor extends ThreadPoolExecutor {
    public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    public void beforeExecute(Thread t, Runnable r) {
        System.out.println("---Before");
        System.out.println(t.getName());
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        System.out.println("---after");
        System.out.println("throwable: " + t);
    }

    public static void main(String[] args) {
        MyThreadPoolExecutor executor = new MyThreadPoolExecutor(10, 10, 30, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(100));
        for (int i = 0; i < 10; i++) {
            executor.execute(new MyRunning());
        }
        executor.shutdown();
    }

    static class MyRunning implements Runnable {

        @Override
        public void run() {
            System.out.println("thread is execute: " + Thread.currentThread().getName());
        }
    }
}
