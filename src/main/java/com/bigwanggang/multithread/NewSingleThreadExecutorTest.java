package com.bigwanggang.multithread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by gustaov on 2018/9/8.
 */
public class NewSingleThreadExecutorTest {
    public static void main(String[] args) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            Runnable runnable = new MyRunnable(i);
            service.submit(runnable);
        }
    }
}

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
