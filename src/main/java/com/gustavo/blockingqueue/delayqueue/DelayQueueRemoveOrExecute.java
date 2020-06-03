package com.gustavo.blockingqueue.delayqueue;

import com.gustavo.blockingqueue.buyTicket.DelayPayment;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 多线程从DelayQueue里remove的栗子,测试,任务要么执行，要么被移除，不会出现，查看队列里是否有某个元素，查看时存在
 * 但是同时又到达任务的执行时间，任务又执行了
 * Created by gustaov on 2019/2/16.
 */
public class DelayQueueRemoveOrExecute {
    public static final int THREAD_NUM = 100;
    public static AtomicInteger executeNum = new AtomicInteger();
    public static AtomicInteger removeNum = new AtomicInteger();

    public static void main(String[] args) {
        DelayQueue<DelayPayment> queue = new DelayQueue<DelayPayment>();
        for (int i = 0; i < THREAD_NUM * 100; i++) {
            queue.add(new DelayPayment(1 * 1000, i, i));
        }
        //queue里就有了100*1000个元素
        System.out.println(queue.size());

        Thread execute = new Thread(new ExecuteTask(0, queue));
        execute.start();


        try {
            Thread.sleep(990);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread[] threads = new Thread[THREAD_NUM];
        for (int i = 0; i < THREAD_NUM; i++) {
            threads[i] = new Thread(new RemoveTask(i, queue));
        }
        for (int i = 0; i < THREAD_NUM; i++) {
            threads[i].start();
        }

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(executeNum.get());
        System.out.println(removeNum.get());
        System.out.println(executeNum.get() + removeNum.get());
    }

    static class ExecuteTask implements Runnable {
        private int id;
        DelayQueue<DelayPayment> queue;

        public ExecuteTask(int id, DelayQueue<DelayPayment> queue) {
            this.id = id;
            this.queue = queue;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    DelayPayment take = queue.take();
                    executeNum.incrementAndGet();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
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
            while (true) {
                for (DelayPayment delayPayment : queue) {

                    if (queue.remove(delayPayment)) {
                        removeNum.incrementAndGet();
                    }

                }
//            for (DelayPayment delayPayment : queue) {
//                if (delayPayment.getTicketId() % THREAD_NUM == id) {
//                    if (queue.remove(delayPayment)) {
//                        removeNum.incrementAndGet();
//                    }
//                }
//            }

            }
        }
    }
}
