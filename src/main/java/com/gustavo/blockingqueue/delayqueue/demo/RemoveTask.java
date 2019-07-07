package com.gustavo.blockingqueue.delayqueue.demo;


import java.util.Iterator;
import java.util.concurrent.DelayQueue;

public class RemoveTask implements Runnable {
    private String removeString;
    private DelayQueue<Message> queue;

    public RemoveTask(String s, DelayQueue<Message> queue) {
        this.removeString = s;
        this.queue = queue;
    }

    @Override
    public void run() {
        Iterator<Message> iterator = queue.iterator();
        while (iterator.hasNext()) {
            Message m = iterator.next();
            if (removeString.equals(m.getMsg())) {
                iterator.remove();
                MultiThreadRemoveDelayQueue.atoic.incrementAndGet();
                System.out.println("remove: " + removeString + " at " + System.currentTimeMillis());
            }
        }
    }
}
