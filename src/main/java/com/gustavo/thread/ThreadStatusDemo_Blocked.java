package com.gustavo.thread;

import java.util.concurrent.TimeUnit;

public class ThreadStatusDemo_Blocked {
    static final Object object = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable1());
        Thread t2 = new Thread(new Runnable1());
        t1.start();
        t2.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println(t1.getState());
        System.out.println(t2.getState());

    }

    public static void sleep() {
        for (int i = 0; i < 100000; i++) {
            for (int j = 0; j < 100000; j++) {
                for (int k = 0; k < 100000; k++) {
                    ;
                }
            }
        }
    }

    static class Runnable1 implements Runnable {

        @Override
        public void run() {
            synchronized (object) {
                sleep();
            }
        }
    }
}
