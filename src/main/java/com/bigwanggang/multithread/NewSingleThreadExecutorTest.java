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
        service.shutdownNow();
    }
}

