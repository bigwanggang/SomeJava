package com.bigwanggang.multithread;

import java.util.concurrent.*;

public class StopBlockingThread implements Callable<String> {
    @Override
    public String call() throws Exception {
        TimeUnit.SECONDS.sleep(4);
        return "helloworld";
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        Future<String> result = executor.submit(new StopBlockingThread());
        new Thread(new FutureThread(result)).start();
        System.out.println("out");
        executor.shutdown();
    }
    private static class FutureThread implements Runnable{
        Future future ;

        public FutureThread(Future future) {
            this.future = future;
        }

        @Override
        public void run() {
            try {
                future.get(2, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                System.out.println("time oust");
                e.printStackTrace();
            }
        }
    }
}
