package com.gustavo.classloader;

/**
 * Created by gustaov on 2019/6/16.
 */
public class ClassLoaderDemo {
    public static void main(String[] args) {
        System.out.println(ClassLoader.getSystemClassLoader());
        ClassLoader c = ClassLoaderDemo.class.getClassLoader();
        System.out.println(c);
    }
}
