package com.bigwanggang.multithread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class ProducerConsumer {
    private  static BlockingQueue<String> queue = new ArrayBlockingQueue<String>(5);
    public static void main(String[] args) {
        new Thread(new Producer()).start();
        new Thread(new Consumer()).start();
    }
    static class Producer implements Runnable{

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                try {
                    queue.put("food, No." + i);
                    System.out.println("produce : food: " + i);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Consumer implements Runnable{

        @Override
        public void run() {
            try {
                while(true) {

                    System.out.println(queue.take());
                    TimeUnit.SECONDS.sleep(1);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
