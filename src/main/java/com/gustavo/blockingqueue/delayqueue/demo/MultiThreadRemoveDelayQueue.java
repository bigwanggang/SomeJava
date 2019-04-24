package com.gustavo.blockingqueue.delayqueue.demo;

import java.util.concurrent.DelayQueue;

public class MultiThreadRemoveDelayQueue {
    public static void main(String[] args) throws InterruptedException {
        DelayQueue<Message> queue = new DelayQueue<Message>();
        for (int i = 0; i < 1000; i++) {
            queue.add(new Message(String.valueOf(i + 1), i + 1));
        }

        new Thread(new Consumer(queue)).start();


        for (int i = 0; i < 100; i++) {
            new Thread(new RemoveTask(String.valueOf(i * 10), queue)).start();
        }

    }
}
