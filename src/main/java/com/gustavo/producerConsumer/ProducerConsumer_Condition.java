package com.gustavo.producerConsumer;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumer_Condition {
    static final ReentrantLock lock = new ReentrantLock();
    static final Condition emtpy = lock.newCondition();
    static final Condition full = lock.newCondition();
    static int num = 0;
    static int count = 0;
    static final int MAX = 3;
    static final Random random = new Random();

    public static void main(String[] args) {
        new Thread(new Producer(), "producer_1").start();
        new Thread(new Producer(), "producer_2").start();
        new Thread(new Producer(), "producer_3").start();
        new Thread(new Consumer(), "consumer_1").start();
        new Thread(new Consumer(), "consumer_1").start();
        new Thread(new Consumer(), "consumer_1").start();
    }

    static class Producer implements Runnable {

        @Override
        public void run() {
            while (true) {
                lock.lock();
                try {
                    while (count >= MAX) {
                        full.await();
                    }
                    System.out.print(Thread.currentThread().getName() + " produce: " + (num + count));
                    count++;
                    System.out.println(" num: " + num + ", count: " + count);
                    emtpy.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
                try {
                    TimeUnit.SECONDS.sleep(random.nextInt(3));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Consumer implements Runnable {

        @Override
        public void run() {
            while (true) {
                lock.lock();
                try {
                    while (count == 0) {
                        emtpy.await();
                    }
                    System.out.print(Thread.currentThread().getName() + " consume: " + num++);
                    count--;
                    System.out.println(" num: " + num + ", count: " + count);
                    full.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
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

