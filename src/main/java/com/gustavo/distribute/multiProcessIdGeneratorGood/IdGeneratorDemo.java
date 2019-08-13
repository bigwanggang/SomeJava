package com.gustavo.distribute.multiProcessIdGeneratorGood;

import org.I0Itec.zkclient.ZkClient;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;

/**
 * 该栗子演示，即使是多把锁，也能达到同步的目的，因为每个都是zookeeper锁
 * 但是该栗子有个惊群效应的问题
 * Created by gustaov on 2019/8/12.
 */
public class IdGeneratorDemo {
    private static int num = 100;

    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(num);

        for (int i = 0; i < num; i++) {
            ZkClient client = new ZkClient("localhost:2181", 5000);
            client.setZkSerializer(new MyZkSerializer());
            Lock lock = new ZkLock(client, "/node1/d");
            IdGenerator generator = new IdGenerator(lock);
            Thread t = new GetIdThread(barrier, generator);
            t.start();
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
            System.out.println("###" + Thread.currentThread().getName() + " -- " + i);
            if (i == num) {
                System.out.println("good");
            }
        }
    }
}
