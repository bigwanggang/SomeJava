package com.gustavo.multithread.synchronizedTest.synchronizedLockClassStatic;

/**
 * Created by gustaov on 2019/4/26.
 */
public class SynchronizedLockClass {
    public static void main(String[] args) {
        Service service = new Service();
        Thread t1 = new ThreadA(service);
        t1.setName("threadA");
        t1.start();
        Thread t2 = new ThreadB();
        t2.setName("threadB");
        t2.start();
    }
}
