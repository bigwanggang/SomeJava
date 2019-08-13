package com.gustavo.distribute.multiProcessIdGeneratorGood;

import java.util.concurrent.locks.Lock;

/**
 * Created by gustaov on 2019/8/12.
 */
public class IdGenerator {
    private static int i = 1;
    private Lock lock;

    public IdGenerator(Lock lock) {
        this.lock = lock;
    }

    public int nextId() {
        lock.lock();
        try {
            return i++;
        } finally {
            lock.unlock();
        }
    }

}
