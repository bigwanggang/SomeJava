package com.gustavo.producerConsumer;

import static com.gustavo.producerConsumer.OddEvenPrint.i;
import static com.gustavo.producerConsumer.OddEvenPrint.lock;

/**
 * Created by gustaov on 2019/7/8.
 */
public class EvenPrinter implements Runnable {
    @Override
    public void run() {
        while (true) {
            synchronized (lock) {
                while ((i & 1) == 0) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("EvenPrinter： " + i++);
                lock.notify();
            }
        }
    }
}
