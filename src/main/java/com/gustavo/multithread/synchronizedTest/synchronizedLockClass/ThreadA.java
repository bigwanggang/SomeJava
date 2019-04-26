package com.gustavo.multithread.synchronizedTest.synchronizedLockClass;

/**
 * Created by gustaov on 2019/4/26.
 */
public class ThreadA extends Thread {
    private Service service;

    public ThreadA(Service service) {
        this.service = service;
    }

    @Override
    public void run(){
        service.printA();
    }
}
