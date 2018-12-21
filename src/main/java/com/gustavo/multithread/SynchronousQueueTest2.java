package com.gustavo.multithread;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class SynchronousQueueTest2 {
    public static void main(String[] args) throws InterruptedException {
        SynchronousQueue<String> queue = new SynchronousQueue<>();
        new Thread(new MyRunn(queue)).start();
        queue.put("hehe");
    }

    static final class MyRunn implements Runnable {

        private SynchronousQueue<String> queue;

        public MyRunn(SynchronousQueue<String> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println(queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
