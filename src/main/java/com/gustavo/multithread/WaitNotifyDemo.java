package com.gustavo.multithread;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * wait notify的栗子，wait之后线程释放锁，通过打印可知，3个线程打印时间一样，然后就等待Producer线程notify
 * 本栗还可以验证一件事：多个wait的线程，收到notifyAll信号后，依然要抢锁，抢到锁后，从之前wait的地方继续执行
 * 但是有件事还需要验证，为什么 抢到锁的顺序和wait之后再次抢锁的顺序的相反的？ 是偶然还是必然的
 *
 */
public class WaitNotifyDemo {
    private static final Object lock = new Object();
    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<String>();
        for (int i = 0; i < 3; i++) {
            new Thread(new Consumer(list), "thread: " + i).start();
        }
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(new Producer(list)).start();
    }

    static class Producer implements Runnable {
        public Producer(LinkedList<String> list) {
            this.list = list;
        }

        LinkedList<String> list;

        @Override
        public void run() {
            synchronized (lock) {
                list.add("hello");
                list.add("world");
                list.add("3");
                lock.notifyAll();
            }
        }
    }

    static class Consumer implements Runnable {
        public Consumer(LinkedList<String> list) {
            this.list = list;
        }

        LinkedList<String> list;

        @Override
        public void run() {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + " get the lock time: " + System.currentTimeMillis());
                try {
                    lock.wait();
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " get the list value: " + list.pollFirst() + " time: " + System.currentTimeMillis());
            }
        }
    }
}
