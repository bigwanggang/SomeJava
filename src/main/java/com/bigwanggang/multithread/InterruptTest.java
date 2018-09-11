package com.zte.sync;

public class InterruptTest implements Runnable {
    public static void main(String[] args) {
        Thread t = new Thread(new InterruptTest());
        t.start();
        t.interrupt();
    }

    @Override
    public void run() {
        System.out.println("in run()...");
        for (int i = 0; i < 100000; i++) {
            for (int j = 0; j < 100000; j++) {
                ;
            }
        }
        System.out.println("out run()...");
    }
}
