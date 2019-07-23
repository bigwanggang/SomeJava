package com.gustavo.multithread.oddevenprint;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Printer {
    private int i = 0;
    private ReentrantLock lock = new ReentrantLock();
    private Condition evenCon = lock.newCondition();
    private Condition oddCon = lock.newCondition();

    public void printEven() {
        lock.lock();
        try {
            if ((i & 1) == 1) {
                try {
                    oddCon.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + " print: " + i++);
            evenCon.signal();
        } finally {
            lock.unlock();
        }
    }

    public void printOdd() {
        lock.lock();
        try {
            if ((i & 1) == 0) {
                try {
                    evenCon.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + " print: " + i++);
            oddCon.signal();
        } finally {
            lock.unlock();
        }
    }
}
