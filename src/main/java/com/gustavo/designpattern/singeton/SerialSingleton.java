package com.gustavo.designpattern.singeton;


import java.io.Serializable;

/**
 * 通过UT测试单例对象的序列化与反序列化
 * 测试发现，增加readResolve方法，反序列化得到的对象就是单例，否则就不是单例
 */
public class SerialSingleton implements Serializable {

    private SerialSingleton() {
    }

    static {
        System.out.println("outclass in instance");
    }

    private static class InnerClass {
        private static SerialSingleton singleton = new SerialSingleton();

        static {
            System.out.println("inner class is instanced");
        }
    }

    public static SerialSingleton getInstance() {
        return InnerClass.singleton;
    }

    public static void staticFunc() {
        System.out.println("call function");
    }

    private Object readResolve(){
        return InnerClass.singleton;
    }
}
