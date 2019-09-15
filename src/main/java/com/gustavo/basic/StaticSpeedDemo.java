package com.gustavo.basic;

/**
 * 尽量是用局部变量，因为局部变量的操作速度较快
 * 本程序是对比局部变量和static变量的操作，结果差别比较大
 * Created by gustaov on 2019/9/14.
 */
public class StaticSpeedDemo {
    static int si = 0;

    public static void main(String[] args) {
        long s1 = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            si++;
        }
        System.out.println(System.currentTimeMillis() - s1);
        int s = 0;
        s1 = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            s++;
        }
        System.out.println(System.currentTimeMillis() - s1);
    }
}
