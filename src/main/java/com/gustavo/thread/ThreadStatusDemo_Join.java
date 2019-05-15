package com.gustavo.thread;

import java.util.concurrent.TimeUnit;

/**
 * Thread.join的栗子: MyRunning线程sleep 3 秒
 * Joinning线程对MyRunning线程执行join，因此oinning线程会在MyRunning线程执行完之后才执行
 * 在等待MyRunning线程的时间段中，Joinning的状态是：WAITING
 */
public class ThreadStatusDemo_Join {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new MyRunning());
        Thread t2 = new Thread(new Joinning(t1));
        t1.start();
        t2.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("State of Joinning: " + t2.getState());
    }

    static class Joinning implements Runnable {

        Thread thread;

        public Joinning(Thread thread) {
            this.thread = thread;
        }

        @Override
        public void run() {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class MyRunning implements Runnable {
        @Override
        public void run() {
            System.out.println("in thread.");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("out thread..");
        }
    }
}
