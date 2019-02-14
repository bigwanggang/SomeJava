package com.gustavo.basic;

public class StringBuilderDemo {
    public static String getStr() {
        String str = "start";
        for (int i = 0; i < 100; i++) {
            str = str + "hello";
        }
        return str;
    }

    public static String getStr1() {
        StringBuilder sb = new StringBuilder();
        sb.append("start");
        for (int i = 0; i < 100; i++) {
            sb.append("hello");
        }
        return sb.toString();
    }
}
