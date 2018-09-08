package com.bigwanggang.multithread;

/**
 * Created by gustaov on 2018/9/8.
 */
class MyRunnable implements Runnable {

    int i;

    public MyRunnable(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        for (int j = 0; j < 50; j++) {
            System.out.println(Thread.currentThread().getName() + "--" + i + ":" + j);
        }
    }
}
