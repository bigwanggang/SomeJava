package com.gustavo.multithread.synchronizedTest;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class SynchronousQueueTest3 {
    public static void main(String[] args) throws InterruptedException {
        SynchronousQueue<String> queue = new SynchronousQueue<String>();
        new Thread(new MyRunn(queue)).start();
        new Thread(new MyConcum(queue)).start();
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
                queue.put("a");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static final class MyConcum implements Runnable {
        SynchronousQueue<String> queue;

        public MyConcum(SynchronousQueue<String> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                System.out.println(queue.take());
                System.out.println(queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
