package com.gustavo.basic;

public class GenericDemo {
    static <sdfdi, idfid> String f(sdfdi a, idfid b) {
        if (a instanceof Integer) {
            System.out.println(a + " instanceof Integer");
        }
        if (b instanceof String) {
            System.out.println(b + " instanceof String");
        }
        return a.toString() + b.toString();
    }

    public static void main(String[] args) {
        String a = f(123, "hello");
        System.out.println(a);
    }
}
