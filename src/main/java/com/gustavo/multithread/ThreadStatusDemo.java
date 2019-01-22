package com.gustavo.multithread;

/*
线程状态的理解，
new 一个Thread就是NEW状态
调用start()之后，线程执行之前就是RUNNABLE状态
*/
public class ThreadStatusDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable1());
        System.out.println(t1.getState());
        t1.start();
        System.out.println(t1.getState());
    }

    static class Runnable1 implements Runnable {

        @Override
        public void run() {
            System.out.println("state: " + Thread.currentThread().getState());
        }
    }
}
