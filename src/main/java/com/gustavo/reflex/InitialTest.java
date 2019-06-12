package com.gustavo.reflex;

/**
 * Created by gustaov on 2018/9/9.
 */
public class InitialTest {
    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println(Class1.a);
        System.out.println(Class2.a);
        Class<Class1> class1Class = Class1.class;
        Class<Class1> class2Class = Class1.class;
        System.out.println(class1Class == class2Class);

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

