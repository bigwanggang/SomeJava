package com.gustavo.designpattern.singeton;

/**
 * 内部类方式的多线程安全的单例模式
 * 内部类的方式实现延迟加载，而且利用jvm加载完成加载，不用考虑线程同步问题
 * Created by gustaov on 2019/4/30.
 */
public class Singleton {

    private Singleton() {
    }

    static {
        System.out.println("outclass in instance");
    }

    private static class InnerClass {
        private static Singleton singleton = new Singleton();

        static {
            System.out.println("inner class is instanced");
        }
    }

    public static Singleton getInstance() {
        return InnerClass.singleton;
    }

    public static void staticFunc(){
        System.out.println("call function");
    }
}
