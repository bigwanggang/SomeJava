package com.gustavo.oom;

/**
 * jdk1.6 返回两个false
 * jdk1.7 返回 true 和 false
 */
public class RuntimeConstantPoolDemo {
    public static void main(String[] args) {
        String str1 = new StringBuilder("computer").append("software").toString();
        System.out.println(str1.intern() == str1);


        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() == str2);
    }
}
