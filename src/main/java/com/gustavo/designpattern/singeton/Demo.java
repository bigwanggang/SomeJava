package com.gustavo.designpattern.singeton;

import java.lang.reflect.Method;

/**
 * Created by gustaov on 2019/9/13.
 */
public class Demo {
    public static void main(String[] args) {
        Class<?> clazz = Singleton.class;
        Class<?>[] classes = clazz.getClasses();
        for (Class<?> aClass : classes) {
            System.out.println(aClass.getName());
        }
    }
}
