package com.bigwanggang.reflex;

/**
 * Created by gustaov on 2018/9/9.
 */
public class InitialTest {
    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("com.bigwanggang.reflex.Class1");
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

