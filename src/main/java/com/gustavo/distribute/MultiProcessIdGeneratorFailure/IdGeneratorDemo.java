package com.gustavo.distribute.MultiProcessIdGeneratorFailure;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 该栗子演示多把锁的情况下，是不能达到同步的目的的
 * Created by gustaov on 2019/8/12.
 */
public class IdGeneratorDemo {
    private static int num = 500;

    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(num);
        for (int i = 0; i < num; i++) {
            Lock lock = new ReentrantLock();
            IdGenerator generator = new IdGenerator(lock);
            Thread t = new GetIdThread(barrier, generator);
            t.start();
        }
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static class GetIdThread extends Thread {
        private CyclicBarrier barrier;
        private IdGenerator idGenerator;

        public GetIdThread(CyclicBarrier barrier, IdGenerator idGenerator) {
            this.barrier = barrier;
            this.idGenerator = idGenerator;
        }

        @Override
        public void run() {
            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            int i = idGenerator.nextId();
            System.out.println(Thread.currentThread().getName() + " -- " + i);
            if (i == num) {
                System.out.println("good");
            }
        }
    }
}
