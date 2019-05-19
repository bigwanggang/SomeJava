package com.gustavo.dataStructureAndAlgo.pointOffer;

/**
 * 剑指offer
 * 面试题9：斐波那契数列
 * Created by gustaov on 2019/5/8.
 */
public class Fibonacci {
    public static int fabonacci(int i) {
        if (i <= 0)
            return 0;
        if (i == 1)
            return 1;
        int a = 0, b = 1;
        int result = 0;
        for (int j = 2; j <= i; j++) {
            result = a + b;
            a = b;
            b = result;
        }
        return result;
    }
}
