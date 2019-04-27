package com.gustavo.blockingqueue.delayqueue;

import com.gustavo.blockingqueue.buyTicket.DelayPayment;

import java.util.concurrent.DelayQueue;

/**
 * 多线程从DelayQueue里remove的栗子
 * Created by gustaov on 2019/2/16.
 */
public class DelayQueueRemoveDemo {
    public static final int THREAD_NUM = 100;

    public static void main(String[] args) {
        DelayQueue<DelayPayment> queue = new DelayQueue<DelayPayment>();
        for (int i = 0; i < THREAD_NUM * 1000; i++) {
            queue.add(new DelayPayment(1000 * 1000, i, i));
        }
        //queue里就有了100*1000个元素
        System.out.println(queue.size());
        Thread[] threads = new Thread[THREAD_NUM];
        for (int i = 0; i < THREAD_NUM; i++) {
            threads[i] = new Thread(new RemoveTask(i, queue));
        }
        for (int i = 0; i < THREAD_NUM; i++) {
            threads[i].start();
        }
        for (int i = 0; i < THREAD_NUM; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(queue.size());


    }

    static class RemoveTask implements Runnable {
        private int id;
        DelayQueue<DelayPayment> queue;

        public RemoveTask(int id, DelayQueue<DelayPayment> queue) {
            this.id = id;
            this.queue = queue;
        }

        @Override
        public void run() {
            for (DelayPayment delayPayment : queue) {
                if (delayPayment.getTicketId() % THREAD_NUM == id) {
                    queue.remove(delayPayment);
                }
            }
        }
    }
}
