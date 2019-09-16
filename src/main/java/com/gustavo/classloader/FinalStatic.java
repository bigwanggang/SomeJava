package com.gustavo.classloader;

/**
 * 调用一个类的final static属性，不会触发该类的初始化
 */
public class FinalStatic {
    final static String s = "hello";
    static {
        System.out.println("FinalStatic is initialized");
    }
}
