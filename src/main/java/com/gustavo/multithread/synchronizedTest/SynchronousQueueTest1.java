package com.gustavo.multithread.synchronizedTest;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class SynchronousQueueTest1 {
    public static void main(String[] args) throws InterruptedException {
        SynchronousQueue<String> queue = new SynchronousQueue<>();
        new Thread(new MyRunn(queue)).start();
        System.out.println(queue.take());
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
                queue.put("haha");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
