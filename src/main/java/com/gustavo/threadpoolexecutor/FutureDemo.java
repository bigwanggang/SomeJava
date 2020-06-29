package com.gustavo.threadpoolexecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 验证future.get是阻塞等待的，
 */
public class FutureDemo {
    public static void main(String[] args) {
        ExecutorService executorService =Executors.newFixedThreadPool(2);
        List<Future<String>> futures = new ArrayList<>();
        futures.add(executorService.submit(()->{
            TimeUnit.SECONDS.sleep(5);
            return "sleep 2 sec";
        }));
        futures.add(executorService.submit(()->{
            return "not sleep";
        }));

        for (Future<String> future : futures) {
            try {
                System.out.println( System.currentTimeMillis());
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
