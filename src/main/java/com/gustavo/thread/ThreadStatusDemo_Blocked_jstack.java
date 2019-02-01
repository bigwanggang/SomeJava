package com.gustavo.thread;

/**
 * 用jstack工具分析 blocked状态
 */
public class ThreadStatusDemo_Blocked_jstack {
    static final Object object = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable1(), "thread1");
        Thread t2 = new Thread(new Runnable1(), "thread2");
        t1.start();
        t2.start();

    }

    public static void sleep() {
        while (true)
            ;
    }

    static class Runnable1 implements Runnable {

        @Override
        public void run() {
            synchronized (object) {
                sleep();
            }
        }
    }
}
