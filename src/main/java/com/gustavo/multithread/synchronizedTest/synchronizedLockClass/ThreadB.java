package com.gustavo.multithread.synchronizedTest.synchronizedLockClass;

/**
 * Created by gustaov on 2019/4/26.
 */
public class ThreadB extends Thread {
    private Service service;

    public ThreadB(Service service) {
        this.service = service;
    }

    @Override
    public void run(){
        service.printB();
    }
}
