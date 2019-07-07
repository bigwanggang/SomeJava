package com.gustavo.reflex;

import java.lang.reflect.Method;

/**
 * 本栗子目的是为了验证，一个接口，继承另一个接口后，如果一个接口同名会怎么样
 * Created by gustaov on 2019/7/7.
 */
public class ReflexDemo {
    public static void main(String[] args) {
        Class clazz = MyList.class;
        System.out.println(clazz.isInterface());
        Method[] methods = clazz.getMethods();
        for (int i = 0; i < methods.length; i++) {
            System.out.println("method: " + methods[i].getName());
        }
    }
}
