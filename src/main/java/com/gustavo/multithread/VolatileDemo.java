package com.gustavo.multithread;

import java.util.concurrent.TimeUnit;

public class VolatileDemo {
    private int a = 0 ;
    private  volatile boolean flag=true;
    public static void main(String[] args) {
       final VolatileDemo instance = new VolatileDemo();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (instance.flag) {
                    instance.a ++;
                }
                System.out.println(instance.a);
            }
        }).start();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        instance.flag = false;
        System.out.println("set false");
    }
}
