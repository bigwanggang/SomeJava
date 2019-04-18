package com.gustavo.blockingqueue.delayqueue.demo;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Created by gustaov on 2019/4/18.
 */
public class Message implements Delayed {
    private long executeTime;

    public Message(long milliseconds) {
        this.executeTime = TimeUnit.NANOSECONDS.convert(milliseconds, TimeUnit.MILLISECONDS) + System.nanoTime();
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return this.executeTime - System.nanoTime();
    }

    @Override
    public int compareTo(Delayed o) {
        return 0;
    }
}
