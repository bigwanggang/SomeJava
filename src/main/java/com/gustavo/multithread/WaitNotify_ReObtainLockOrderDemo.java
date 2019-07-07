package com.gustavo.multithread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * WaitNotifyDemo的栗子有3个线程wait，收到notifyAll信号后，获取锁的顺序正好和第一次获取锁的顺序是相反的，
 * 本栗，通过增加wait的线程数，验证这一现象是偶然还是必然
 * 通过验证发现，这是个必然现象，为什么？
 */
public class WaitNotify_ReObtainLockOrderDemo {
    private static final Object lock = new Object();

    public static void main(String[] args) {
        List<String> list1 = new ArrayList<String>();
        List<String> list2 = new ArrayList<String>();
        for (int i = 0; i < 100; i++) {
            new Thread(new Consumer(list1, list2), "thread: " + i).start();
        }
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(new Producer()).start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Collections.reverse(list2);
        System.out.println(list1);
        System.out.println(list2);
        System.out.println(list1.equals(list2));

    }

    static class Producer implements Runnable {


        @Override
        public void run() {
            synchronized (lock) {
                lock.notifyAll();
            }
        }
    }

    static class Consumer implements Runnable {

        List<String> list1;
        List<String> list2;

        public Consumer(List<String> list1, List<String> list2) {
            this.list1 = list1;
            this.list2 = list2;
        }

        @Override
        public void run() {
            synchronized (lock) {
                list1.add(Thread.currentThread().getName());
                System.out.println(Thread.currentThread().getName() + " get the lock time: " + System.currentTimeMillis());
                try {
                    lock.wait();
                    list2.add(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " release the lock  time: " + System.currentTimeMillis());
            }
        }
    }
}
