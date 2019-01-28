package com.gustavo.producerConsumer;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ProducerConsumer_waitNotify_notgood {

    static final int capacity = 20;
    static int count = 0;
    static int num = 0;
    static final Object lock = new Object();
    static final Random random = new Random();

    public static void main(String[] args) {
        Producer producer = new Producer();
        Consumer consumer = new Consumer();
        new Thread(producer, "producer1").start();
        new Thread(producer, "producer2").start();
        new Thread(producer, "producer3").start();

        new Thread(consumer, "consumer1").start();
        new Thread(consumer, "consumer2").start();
        new Thread(consumer, "consumer3").start();
        new Thread(consumer, "consumer4").start();
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
                    System.out.println(Thread.currentThread().getName() + " consume : " + num++);
                    count--;

                    try {
                        TimeUnit.SECONDS.sleep(random.nextInt(3));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lock.notifyAll();

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
                    System.out.println(Thread.currentThread().getName() + " produce : " + (count + num));
                    count++;
                    try {
                        TimeUnit.SECONDS.sleep(random.nextInt(3));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lock.notifyAll();

                }
            }
        }
    }
}
