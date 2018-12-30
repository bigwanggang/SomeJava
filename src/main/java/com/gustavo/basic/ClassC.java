package com.gustavo.basic;

public class ClassC extends ClassB {

    @Override
    public void func() {
        System.out.println("ClassC ...");
        super.func();
    }

    public static void main(String[] args) {
        ClassC c = new ClassC();
        c.func();
    }
}
