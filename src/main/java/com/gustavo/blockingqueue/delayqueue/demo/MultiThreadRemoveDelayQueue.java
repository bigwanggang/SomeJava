package com.gustavo.blockingqueue.delayqueue.demo;

import com.gustavo.multithread.ThreadUtils;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class MultiThreadRemoveDelayQueue {
    public static AtomicInteger atoic = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {
        DelayQueue<Message> queue = new DelayQueue<Message>();
        for (int i = 0; i < 1000; i++) {
            queue.add(new Message(String.valueOf(i + 1), i + 1));
        }

        Consumer r = new Consumer(queue);
        Thread takeThread = new Thread(r);
        takeThread.start();

        Thread[] threads = new Thread[100];
        for (int i = 0; i < 100; i++) {
            threads[i] = new Thread(new RemoveTask(String.valueOf(i * 10), queue));
        }

        ThreadUtils.startAndJoin(threads);

        while (!queue.isEmpty());
        r.stop();
        System.out.println(atoic.get());

    }
}
