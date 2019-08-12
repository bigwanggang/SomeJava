package com.gustavo.distribute.oneProcessIdGeneratorSuccess;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * 单进程下的id生成器的多线程场景
 * Created by gustaov on 2019/8/12.
 */
public class IdGeneratorInOneProcessDemo {
    private static int num = 500;
    private static Set<Integer> set = new HashSet<Integer>();

    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(num);
        IdGenerator generator = new IdGenerator();
        for (int i = 0; i < num; i++) {
            Thread t = new GetIdThread(barrier, generator);
            t.start();
        }
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 如果set.size() 的值小于num的值，就说明出现线程同步问题
        System.out.println(set.size());
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
            set.add(i);
            System.out.println(Thread.currentThread().getName() + "--" + i);
        }
    }
}
