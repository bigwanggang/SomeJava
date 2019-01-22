package com.gustavo.thread;

/**
 * 测试线程在wait时的状态
 */
public class ThreadStatusDemo_WaitNotify {
    static final Object object = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new MyRunn());
        t.start();
        Thread.sleep(500);
        System.out.println(t.getState());
        synchronized (object) {
            object.notifyAll();
        }
        Thread.sleep(500);
        System.out.println(t.getState());
    }

    static class MyRunn implements Runnable {

        @Override
        public void run() {
            System.out.println("in MyRunn.");
            synchronized (object) {
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("out MyRunn.");
        }
    }
}
