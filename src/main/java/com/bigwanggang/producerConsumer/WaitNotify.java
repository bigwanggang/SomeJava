package com.bigwanggang.producerConsumer;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class WaitNotify {
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

        public synchronized void addProduct() {
            if (num >= MAX) {
                try {
                    System.out.println(Thread.currentThread().getName() + "已满，不能生产");
                    wait();
                    System.out.println(Thread.currentThread().getName() + "停止wait");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return;
            }
            num++;
            index++;
            list.add("product: " + index);
            System.out.println(Thread.currentThread().getName() + "生产了一个产品: ---------product: " + index);
            notifyAll();
        }

        public synchronized void getProduct() {
            if (list.isEmpty()) {
                System.out.println(Thread.currentThread().getName() + "没有产品了");
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return;
            }
            num--;
            System.out.println(Thread.currentThread().getName() + "消费了一个产品: " + list.pollFirst());
            notifyAll();
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
                resource.getProduct();
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
