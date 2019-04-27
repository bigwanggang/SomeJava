package com.gustavo.multithread.join;

/**
 * Created by gustaov on 2019/4/27.
 */
public class DeepInJoin1 {
    static volatile int i =0;
    static class AddRunning implements Runnable{

        @Override
        public void run() {
            for (int j = 0; j < 1000; j++) {
                System.out.println(Thread.currentThread().getName() + ": " + i++);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[100];
        for (int j = 0; j < 100; j++) {
            threads[j] = new Thread(new AddRunning());
            threads[j].start();
        }

        for (int j = 0; j < 100; j++) {
            threads[j].join();
            System.out.println("join");
        }

        System.out.println(i);

    }
}
