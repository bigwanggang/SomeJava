package com.gustavo.classloader;

public class Hello {
    static {
        System.out.println("static block");
    }

    public static void staticFunction(){
        System.out.println("staticFunction");
    }

    public static String a = "hello";
}
