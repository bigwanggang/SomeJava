package com.bigwanggang.reflex;

/**
 * Created by gustaov on 2018/9/9.
 */
public class InitialTest {
    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println(Class1.a);
        System.out.println(Class2.a);
    }
}

class Class1 {
    final static String a = "hello";

    static {
        System.out.println("Class1 init");
    }
}

class Class2 {
    static String a = "hello";

    static {
        System.out.println("Class2 init");
    }
}

