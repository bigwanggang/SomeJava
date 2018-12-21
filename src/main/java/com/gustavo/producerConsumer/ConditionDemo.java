package com.gustavo.producerConsumer;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionDemo {
    public static void main(String[] args) {
        Resource resource = new Resource();
        new Thread(new Producer(resource), "Producer_1").start();
        new Thread(new Producer(resource), "Producer_2").start();
        new Thread(new Producer(resource), "Producer_3").start();
        new Thread(new Producer(resource), "Producer_4").start();


        new Thread(new Consumer(resource), "Consumer_1").start();
        new Thread(new Consumer(resource), "Consumer_2").start();
    }

    static class Resource {
        LinkedList<String> list = new LinkedList<>();
        int MAX = 10;
        int num;
        int index = 0;
        private Lock lock = new ReentrantLock();
        Condition full = lock.newCondition();
        Condition empty = lock.newCondition();

        public void addProduct() {
            lock.lock();
            try {
                if (num >= MAX) {
                    System.out.println(Thread.currentThread().getName() + " add wait");
                    full.await();
                }
                list.add("product" + index);
                System.out.println(Thread.currentThread().getName() + " add product" + index);
                index++;
                num++;
                empty.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void removeProduct() {
            lock.lock();
            try {
                if (num <= 0) {
                    System.out.println(Thread.currentThread().getName() + " remove wait");
                    empty.await();
                }
                System.out.println(Thread.currentThread().getName() + " remove : " + list.pollFirst());

                num--;
                full.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

    }

    static class Producer implements Runnable {
        private Resource resource;

        public Producer(Resource resource) {
            this.resource = resource;
        }

        @Override
        public void run() {
            while (true) {
                resource.addProduct();
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Consumer implements Runnable {
        private Resource resource;

        public Consumer(Resource resource) {
            this.resource = resource;
        }

        @Override
        public void run() {
            while (true) {
                resource.removeProduct();
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
