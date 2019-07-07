package com.gustavo.multithread;

/**
 * Created by gustaov on 2019/7/7.
 */
public class ThreadUtils {
    public static void startAndJoin(Thread[] threads){
        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
