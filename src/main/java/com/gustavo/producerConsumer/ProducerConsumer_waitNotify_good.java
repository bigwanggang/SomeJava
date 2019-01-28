package com.gustavo.producerConsumer;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ProducerConsumer_waitNotify_good {

    static final int capacity = 3;
    static int count = 0;
    static int num = 0;
    static final Object lock = new Object();
    static final Random random = new Random();

    public static void main(String[] args) {

        new Thread(new Producer(), "producer1").start();
        new Thread(new Producer(), "producer2").start();
        new Thread(new Producer(), "producer3").start();

        new Thread(new Consumer(), "consumer1").start();
        new Thread(new Consumer(), "consumer2").start();
        new Thread(new Consumer(), "consumer3").start();
        new Thread(new Consumer(), "consumer4").start();
    }

    static class Consumer implements Runnable {

        @Override
        public void run() {
            while (true) {
                synchronized (lock) {

                    while (count == 0) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.print(Thread.currentThread().getName() + " consume : " + num++);
                    count--;
                    System.out.println(" num: " + num + " count: " + count);
                    lock.notifyAll();
                }
                try {
                    TimeUnit.SECONDS.sleep(random.nextInt(3));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Producer implements Runnable {

        @Override
        public void run() {
            while (true) {
                synchronized (lock) {
                    while (count >= capacity) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.print(Thread.currentThread().getName() + " produce : " + (count + num));
                    count++;
                    System.out.println(" num: " + num + " count: " + count);
                    lock.notifyAll();
                }
                try {
                    TimeUnit.SECONDS.sleep(random.nextInt(3));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
