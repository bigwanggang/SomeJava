package com.gustavo.distribute.mysql_distribute_lock;

import java.util.concurrent.locks.Lock;

public class IdGenerator {
    public static int i = 0;
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
