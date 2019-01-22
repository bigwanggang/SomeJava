package com.gustavo.thread;

public class SetUncatchedExceptionDemo {
    static class MyExceptionHandler implements Thread.UncaughtExceptionHandler {
        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.printf("An exception has been captured\n");
            System.out.printf("Thread:%s\n", t.getName());
            System.out.printf("Exception: %s: %s:\n", e.getClass().getName(), e.getMessage());
            System.out.printf("Stack Trace:\n");
            e.printStackTrace();
            System.out.printf("Thread status:%s\n", t.getState());
        }
    }

    public static void main(String[] args) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                Thread.currentThread().setUncaughtExceptionHandler(new MyExceptionHandler());
                double d = 1/0;
            }
        });
        t.start();
    }

}
