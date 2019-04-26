package com.gustavo.multithread.synchronizedTest.synchronizedLockClassStatic;

/**
 * Created by gustaov on 2019/4/26.
 */
public class ThreadB extends Thread {


    @Override
    public void run(){
        Service.printB();
    }
}
