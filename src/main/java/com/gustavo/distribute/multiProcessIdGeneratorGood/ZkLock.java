package com.gustavo.distribute.multiProcessIdGeneratorGood;

import lombok.extern.slf4j.Slf4j;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkNodeExistsException;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

@Slf4j
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
                    log.info(Thread.currentThread().getName() + "### receive delete info: " + s);
                    latch.countDown();
                }
            };
            client.subscribeDataChanges(lockPath, listener);
            try {
                latch.await(500, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            client.unsubscribeDataChanges(lockPath, listener);
            lock();
        }
        log.info(Thread.currentThread().getName() + "get lock");
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
    }

    @Override
    public boolean tryLock() {
        try {
            client.createEphemeral(lockPath, "aa");
            log.info(Thread.currentThread().getName() + "###try lock success");
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
        log.info(Thread.currentThread().getName() + "###release lock");
        client.close();
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
