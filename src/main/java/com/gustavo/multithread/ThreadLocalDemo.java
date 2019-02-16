package com.gustavo.multithread;

public class ThreadLocalDemo {

    public static void main(String[] args) throws InterruptedException {
        ThreadLocal<String> local = new ThreadLocal<String>(){
            protected String initialValue() {
                return "initial value";
            }
        };

        Thread t = new Thread(new InnerRunnable(local));
        t.start();
        t.join();
        System.out.println(local.get());

    }
    static class InnerRunnable implements Runnable{
        ThreadLocal<String> local;

        public InnerRunnable(ThreadLocal<String> local) {
            this.local = local;
        }

        @Override
        public void run() {
            System.out.println(local.get());
            local.set(Thread.currentThread() + " set");
            System.out.println(local.get());
        }
    }
}
