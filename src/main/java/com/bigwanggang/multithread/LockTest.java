package com.bigwanggang.multithread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by 10183960 on 2018/9/11.
 */
public class LockTest implements Runnable {
    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        Thread t1 = new Thread(new LockTest());
        Thread t2 = new Thread(new LockTest());
        t1.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
        t2.interrupt();
    }

    @Override
    public void run() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " in run()...");
            TimeUnit.SECONDS.sleep(3);
            System.out.println(Thread.currentThread().getName() + " out run()...");
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " interrupt()...");
        } finally {
            lock.unlock();
        }
    }
}
