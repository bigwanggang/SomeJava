package com.gustavo.designpattern;

/**
 * 双重检查锁实现的线程安全的单例模式
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
