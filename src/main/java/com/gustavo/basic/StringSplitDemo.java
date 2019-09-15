package com.gustavo.basic;

/**
 * Created by gustaov on 2019/9/13.
 */
public class StringSplitDemo {
    public static void main(String[] args) {
        String s = null;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            sb.append(i);
            sb.append(";");
        }
        s = sb.toString();
        String s1 = new String(s);
        long start = System.currentTimeMillis();
        s.split(";");
        System.out.println(System.currentTimeMillis() - start);

        long start1 = System.currentTimeMillis();
        s.split(";");
        System.out.println(System.currentTimeMillis() - start1);

    }
}
