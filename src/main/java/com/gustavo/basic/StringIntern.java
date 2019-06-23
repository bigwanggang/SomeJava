package com.gustavo.basic;

/**
 * Created by gustaov on 2019/6/23.
 */
public class StringIntern {
    public static void main(String[] args) {
        String str1 = new StringBuilder().append("hello").append("world").toString();
        System.out.println(str1.intern() == str1);

        String str2 = new StringBuilder().append("ja").append("va").toString();
        System.out.println(str2.intern() == str2);

    }
}
