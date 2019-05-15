package com.gustavo.thread;

import java.util.concurrent.TimeUnit;

/**
 * sleep线程的状态，为：TIMED_WAITING
 * 注意：要等一段时间再去查看线程的状态，否则可能出现RUNNABLE状态
 */
public class ThreadStatusDemo_Sleeping {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Sleeping(), "Sleeping");
        t1.start();
        TimeUnit.SECONDS.sleep(2);
        System.out.println("state of Sleeping thread is : " + t1.getState());
    }

    static class Sleeping implements Runnable {
        @Override
        public void run() {

            try {
                TimeUnit.SECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }


}
