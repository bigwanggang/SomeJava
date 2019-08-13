package com.gustavo.distribute.multiProcessIdGeneratorGood;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkNodeExistsException;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class ZkLock implements Lock {
    private ZkClient client;
    private String lockPath;

    public ZkLock(ZkClient client, String lockPath) {
        this.client = client;
        this.lockPath = lockPath;
    }

    @Override
    public void lock() {
        if (!tryLock()) {
            final CountDownLatch latch = new CountDownLatch(1);
            IZkDataListener listener = new IZkDataListener() {
                @Override
                public void handleDataChange(String s, Object o) throws Exception {

                }

                @Override
                public void handleDataDeleted(String s) throws Exception {
                    System.out.println("receive delete info: " + s);
                    latch.countDown();
                }
            };
            client.subscribeDataChanges(lockPath, listener);
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            client.unsubscribeDataChanges(lockPath, listener);
            lock();
        }
        System.out.println(Thread.currentThread().getName() + "get lock");
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
    }

    @Override
    public boolean tryLock() {
        try {
            client.createEphemeral(lockPath, "aa");
            System.out.println("try lock success");
            return true;
        } catch (ZkNodeExistsException exception) {
        }
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        client.delete(lockPath);
        System.out.println("release lock");
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
