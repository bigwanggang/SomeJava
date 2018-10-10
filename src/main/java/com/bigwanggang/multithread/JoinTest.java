package com.bigwanggang.multithread;

public class JoinTest {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(new MyRunnable());
            t.start();
            t.join();
        }
        System.out.println("main");
    }
    static class MyRunnable implements Runnable{

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        }
    }
}
