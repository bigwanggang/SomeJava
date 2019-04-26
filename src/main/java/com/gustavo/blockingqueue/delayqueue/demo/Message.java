package com.gustavo.blockingqueue.delayqueue.demo;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class Message implements Delayed {
    private long executeTime;
    private String msg;

    public Message(String msg, long executeTime) {
        this.msg = msg;
        this.executeTime = TimeUnit.NANOSECONDS.convert(executeTime, TimeUnit.SECONDS) + System.nanoTime();
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return executeTime - System.nanoTime();
    }

    @Override
    public int compareTo(Delayed o) {
        Message other = (Message) o;
        long diff = executeTime - other.executeTime;
        if (diff > 0) {
            return 1;
        } else if (diff < 0) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return "Message{" +
                "msg='" + msg + '\'' +
                '}';
    }

    public String getMsg() {
        return msg;
    }
}
