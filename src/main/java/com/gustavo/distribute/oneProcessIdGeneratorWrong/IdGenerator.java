package com.gustavo.distribute.oneProcessIdGeneratorWrong;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by gustaov on 2019/8/12.
 */
public class IdGenerator {
    private Lock lock = new ReentrantLock();

    private int i = 1;

    public synchronized int nextId() {
        return i++;

    }

}
