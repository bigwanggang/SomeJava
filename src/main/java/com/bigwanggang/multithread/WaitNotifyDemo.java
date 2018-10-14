package com.bigwanggang.multithread;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class WaitNotifyDemo {
    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>();
        for (int i = 0; i < 3; i++) {
            new Thread(new Consumer(list), "thread: " + i).start();
        }
        try {
            TimeUnit.SECONDS.sleep(3);
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
            synchronized (list) {
                list.add("hello");
                list.add("world");
                list.add("3");
                list.notifyAll();
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
            synchronized (list) {
                System.out.println(Thread.currentThread().getName() + " get the lock time: " + System.currentTimeMillis());
                try {
                    list.wait();
                    TimeUnit.SECONDS.sleep(4);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " get the list value: " + list.pollFirst() + " time: " + System.currentTimeMillis());
            }
        }
    }
}
