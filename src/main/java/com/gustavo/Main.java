package com.gustavo;

/**
 * Created by gustaov on 2017/9/25.
 */
public class Main {
    public static void main(String[] args) {
        ClassLoader loader = Main.class.getClassLoader();
        System.out.println(loader);
        System.out.println(loader.getParent());
        System.out.println(loader.getParent().getParent());

    }
}
