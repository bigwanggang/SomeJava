package com.gustavo.producerConsumer;

/**
 * Created by gustaov on 2019/7/8.
 */
public class OddEvenPrint {
    public static final Object lock = new Object();
    public static int i = 0;

    public static void main(String[] args) {
        new Thread(new EvenPrinter()).start();
        new Thread(new OddPrinter()).start();
    }
}
