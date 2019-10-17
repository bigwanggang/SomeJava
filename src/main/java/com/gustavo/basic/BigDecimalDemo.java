package com.gustavo.basic;

import java.math.BigDecimal;

/**
 * 浮点数的加减乘除，不能直接用double\float运算
 * 要用BigDecimal
 */
public class BigDecimalDemo {
    public static void main(String[] args) {
        double d = 0.1;
        for (int i = 0; i < 10; i++) {
            d = d - 0.01;
        }
        System.out.println(d > 0);
        BigDecimal b = new BigDecimal("0.1");
        for (int i = 0; i < 10; i++) {
            b = b.subtract(new BigDecimal("0.01"));
        }
        System.out.println(b.doubleValue() > 0);
    }
}
