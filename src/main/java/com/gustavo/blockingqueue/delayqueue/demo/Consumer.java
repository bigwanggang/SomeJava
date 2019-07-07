package com.gustavo.blockingqueue.delayqueue.demo;

import java.util.concurrent.DelayQueue;

/**
 * Created by gustaov on 2019/4/26.
 */
public class Consumer implements Runnable {
    private DelayQueue<Message> delayQueue;
    private boolean isStop = false;

    public Consumer(DelayQueue<Message> delayQueue) {
        this.delayQueue = delayQueue;
    }

    @Override
    public void run() {
        while (!isStop){
            try {
                Message m = delayQueue.take();
                MultiThreadRemoveDelayQueue.atoic.incrementAndGet();
                System.out.println(Thread.currentThread().getName() + " get: " + m.getMsg() + " at time: " + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        isStop = true;
    }
}
