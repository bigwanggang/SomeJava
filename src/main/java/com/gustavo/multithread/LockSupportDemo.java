package com.gustavo.multithread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class LockSupportDemo {
    public static void main(String[] args) throws InterruptedException {
        Object object = new Object();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("in thread1..");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                LockSupport.park(object);
                System.out.println("out thread1..");
            }
        }, "thread1");
        t1.start();
        LockSupport.unpark(t1);

    }
}
