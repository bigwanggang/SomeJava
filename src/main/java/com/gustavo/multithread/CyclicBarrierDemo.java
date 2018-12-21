package com.gustavo.multithread;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    private static int LEN = 10;

    public static void main(String[] args) {
        int[] array = new int[LEN];
        CyclicBarrier barrier = new CyclicBarrier(LEN, new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < LEN; i++) {
                    System.out.print(array[i] + " ");
                }
            }
        });

        for (int i = 0; i < LEN; i++) {
            new Thread(new ArrayRunnable(i, array, barrier)).start();
        }

    }

    private static class ArrayRunnable implements Runnable {
        int index;
        int[] array;
        CyclicBarrier barrier;

        public ArrayRunnable(int index, int[] array, CyclicBarrier barrier) {
            this.index = index;
            this.array = array;
            this.barrier = barrier;
        }

        @Override
        public void run() {
            int n = new Random().nextInt(100);
            array[index] = n;
            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
