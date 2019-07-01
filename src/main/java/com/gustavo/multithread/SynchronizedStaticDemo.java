package com.gustavo.multithread;

import java.util.concurrent.TimeUnit;

/**
 * 验证synchronized static 的方法同步，
 * synchronized (SynchronizedStaticDemo.class) 也是要获取类的锁
 */
public class SynchronizedStaticDemo {
    public synchronized static void f() {
        System.out.println(Thread.currentThread().getName() + " in f..");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " out f..");
    }

    public synchronized static void g() {
        System.out.println(Thread.currentThread().getName() + " in g..");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " out g..");
    }

    public static void main(String[] args) {
        new Thread(new Runningf()).start();
        new Thread(new Runningg()).start();
        new Thread(new Runningh()).start();
    }

    public void h() {
        synchronized (SynchronizedStaticDemo.class) {
            System.out.println(Thread.currentThread().getName() + " in h..");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " out h..");
        }
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

    static class Runningh implements Runnable {

        @Override
        public void run() {
            new SynchronizedStaticDemo().h();
        }
    }

}
