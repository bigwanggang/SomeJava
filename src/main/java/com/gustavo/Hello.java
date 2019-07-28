package com.gustavo;

/**
 * Created by gustaov on 2019/6/16.
 */
public class Hello {
    public Hello() {
        System.out.println("new instance");
    }

    static {
        System.out.println("initial HelloWorld");
    }

    public void hello() {
        System.out.println("learn classLoader...");
    }
}
