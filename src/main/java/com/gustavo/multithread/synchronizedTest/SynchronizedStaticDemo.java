package com.gustavo.multithread.synchronizedTest;

import java.util.concurrent.TimeUnit;

public class SynchronizedStaticDemo {
    public synchronized static void f() {
        System.out.println(Thread.currentThread().getName() + " in f..");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " out f..");
    }

    public synchronized static void g() {
        System.out.println(Thread.currentThread().getName() + " in g..");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " out g..");
    }

    public static void h() {
        System.out.println(Thread.currentThread().getName() + " in h..");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " out h..");
    }


    public static void main(String[] args) {
        new Thread(new Runningf()).start();
        new Thread(new Runningg()).start();
    }

    static class Runningf implements Runnable {

        @Override
        public void run() {
            f();
        }
    }

    static class Runningg implements Runnable {

        @Override
        public void run() {
            g();
        }
    }

}
