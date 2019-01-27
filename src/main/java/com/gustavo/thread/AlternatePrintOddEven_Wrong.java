package com.gustavo.thread;

public class AlternatePrintOddEven_Wrong {
    static int i = 0;

    public static void main(String[] args) {
        new Thread(new PringEven(), "even").start();
        new Thread(new PringOdd(), "odd ").start();
    }

    static class PringOdd implements Runnable {

        @Override
        public void run() {
            for (int j = 0; j < 100; j++) {
                synchronized (this) {
                    try {
                        while ((i & 1) == 0) {
                            wait();
                        }
                        System.out.println(Thread.currentThread().getName() + ": " + i++);
                        notify();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static class PringEven implements Runnable {

        @Override
        public void run() {
            for (int j = 0; j < 100; j++) {
                synchronized (this) {
                    try {
                        while ((i & 1) != 0) {
                            wait();
                        }
                        System.out.println(Thread.currentThread().getName() + ": " + i++);
                        notify();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


            }
        }
    }
}


