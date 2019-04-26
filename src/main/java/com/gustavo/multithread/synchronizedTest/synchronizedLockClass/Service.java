package com.gustavo.multithread.synchronizedTest.synchronizedLockClass;

import java.util.concurrent.TimeUnit;

/**
 * Created by gustaov on 2019/4/26.
 */
public class Service {
    public static void printA() {
        synchronized (Service.class) {
            try {
                System.out.println(Thread.currentThread().getName() + " " + System.currentTimeMillis() + " in pringA()..");
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + " " + System.currentTimeMillis() + " out pringA()..");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public static void printB() {
        synchronized (Service.class) {
            try {
                System.out.println(Thread.currentThread().getName() + " " + System.currentTimeMillis() + " in pringB()..");
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + " " + System.currentTimeMillis() + " out printB()..");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
