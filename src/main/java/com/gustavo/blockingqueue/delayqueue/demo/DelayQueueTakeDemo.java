package com.gustavo.blockingqueue.delayqueue.demo;

import java.util.concurrent.DelayQueue;

/**
 * 验证个问题：看代码发现在delayQueue中take会阻塞, 先放进DelayQueue里一个长时间的任务
 * 任何启动线程take，之后再往DelayQueue里加一个时间比较短的任务
 */
public class DelayQueueTakeDemo {
    public static void main(String[] args) {
        final DelayQueue<Message> delayQueue = new DelayQueue<Message>();
        boolean b = delayQueue.offer(new Message("hello", 10));
        System.out.println(b);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Message m = delayQueue.take();
                        System.out.println(m);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        boolean b1 = delayQueue.offer(new Message("world", 1));
        System.out.println(b1);
    }
}
