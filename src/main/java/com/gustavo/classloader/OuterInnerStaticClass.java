package com.gustavo.classloader;

/**
 * 静态内部类初始化，是不会初始化外部类的，如果访问InnerStaticClass.a, 只初始化InnerStaticClass
 */
public class OuterInnerStaticClass {
    static {
        System.out.println("OuterInnerStaticClass initialize");
    }

    static class InnerStaticClass {
        static String a = "hello";

        static {
            System.out.println("InnerStaticClass initializtion");
        }
    }
}
