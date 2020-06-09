package com.gustavo.java8;

import java.util.function.BinaryOperator;

/**
 * BinaryOperator是特殊的BiFunction，当两个入参和返回的类型都相同时，可以用BinaryOperator表示
 */
public class BinaryOperatorDemo {
    public static void main(String[] args) {
        BinaryOperator<Integer> add = (a, b) -> a + b;
        int c = add.apply(23,3);
        System.out.println(c);
    }
}
