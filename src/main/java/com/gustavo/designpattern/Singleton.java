package com.gustavo.designpattern;

/**
 * 内部类方式的多线程安全的单例模式
 * Created by gustaov on 2019/4/30.
 */
public class Singleton {
    private Singleton() {
    }

    private static class InnerClass {
        private static Singleton singleton = new Singleton();
    }

    public static Singleton getInstance() {
        return InnerClass.singleton;
    }
}
