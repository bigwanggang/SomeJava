package com.gustavo.producerConsumer;

import static com.gustavo.producerConsumer.OddEvenPrint.i;
import static com.gustavo.producerConsumer.OddEvenPrint.lock;

/**
 * Created by gustaov on 2019/7/8.
 */
public class OddPrinter implements Runnable {
    @Override
    public void run() {
        while (true){
            synchronized (lock){
                while ((i&1)==1){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("OddPrinter: " +i++);
                lock.notify();
            }
        }
    }
}
