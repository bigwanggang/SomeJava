package com.bigwanggang.multithread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class LockInterruptTest implements Runnable {
    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        Thread t1 = new Thread(new LockInterruptTest());
        Thread t2 = new Thread(new LockInterruptTest());
        t1.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
        System.out.println("t2 start");
        t2.interrupt();
    }

    @Override
    public void run() {
        try {
            lock.lockInterruptibly();
            System.out.println(Thread.currentThread().getName() + " in run()...");
            for (int i = 0; i < 100000; i++) {
                for (int j = 0; j < 100000; j++) {
                    for (int k = 0; k < 10000; k++) {
                        ;
                    }
                }
            }
            System.out.println(Thread.currentThread().getName() + " out run()...");
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " interrupt...");
        } finally {
            lock.unlock();
        }
    }
}
