package com.bigwanggang.multithread;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    private static int LEN = 10;

    public static void main(String[] args) {
        int[] array = new int[LEN];
        CountDownLatch latch = new CountDownLatch(LEN);

        for (int i = 0; i < LEN; i++) {
            new Thread(new ArrayRunnable(i, array, latch)).start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < LEN; i++) {
            System.out.print(array[i] + " ");

        }


    }

    private static class ArrayRunnable implements Runnable {
        int index;
        int[] array;
        CountDownLatch latch;

        public ArrayRunnable(int index, int[] array, CountDownLatch latch) {
            this.index = index;
            this.array = array;
            this.latch = latch;
        }

        @Override
        public void run() {
            int n = new Random().nextInt(100);
            array[index] = n;
            latch.countDown();
        }
    }
}
