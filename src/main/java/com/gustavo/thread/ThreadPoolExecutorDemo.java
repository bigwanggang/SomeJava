package com.gustavo.thread;

import java.util.concurrent.*;

/**
 * Created by gustaov on 2019/2/19.
 */
public class ThreadPoolExecutorDemo {
    public static void main(String[] args) {
        ExecutorService service = new ThreadPoolExecutor(0, 4, 60, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
        service.submit(new Print());
        service.submit(new Print());
        service.submit(new Print());
        service.shutdown();
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newSingleThreadExecutor();
        executorService.setCorePoolSize(2);
    }

    static class Print implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
        }
    }
}
