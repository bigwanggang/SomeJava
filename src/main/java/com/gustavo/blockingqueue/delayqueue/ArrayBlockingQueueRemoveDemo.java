package com.gustavo.blockingqueue.delayqueue;

import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by gustaov on 2019/2/20.
 */
public class ArrayBlockingQueueRemoveDemo {
    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(100 * 1000);
        for (int i = 0; i < 100 * 1000; i++) {
            queue.add(i);
        }
        Thread[] threads = new Thread[100];
        for (int i = 0; i < 100; i++) {
            threads[i] = new Thread(new Removeing(i, queue));
        }
        for (int i = 0; i < 100; i++) {
            threads[i].start();
        }
        for (int i = 0; i < 100; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(queue.size());

    }

    static class Removeing implements Runnable {
        int id;
        BlockingQueue<Integer> queue;

        public Removeing(int id, BlockingQueue<Integer> queue) {
            this.id = id;
            this.queue = queue;
        }

        @Override
        public void run() {
            Iterator<Integer> iter = queue.iterator();
            while (iter.hasNext()) {
                if (iter.next() % 100 == id) {
                    iter.remove();
                }
            }
        }
    }
}
