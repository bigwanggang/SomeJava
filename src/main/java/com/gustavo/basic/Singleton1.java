package com.gustavo.basic;

/**
 *
 * Created by gustaov on 2019/4/30.
 */
public class Singleton1 {
    private volatile static Singleton1 instance;
    private Singleton1(){
    }

    public static Singleton1 getInstance(){
        if(instance == null){
            synchronized (Singleton1.class){
                if(instance == null){
                    instance = new Singleton1();
                }
            }
        }
        return instance;
    }
}
