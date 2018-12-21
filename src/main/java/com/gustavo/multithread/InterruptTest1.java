package com.gustavo.multithread;

import java.util.concurrent.TimeUnit;

public class InterruptTest1 implements Runnable {
    public static void main(String[] args) {
        Thread t = new Thread(new InterruptTest1());
        t.start();
        t.interrupt();
    }

    @Override
    public void run() {
        System.out.println("in run()...");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("out run()...");
    }
}
