package com.gustavo.java8;

import java.util.function.Function;

/**
 * Function是可以定义输入输出的类型，可以通过andThen将多个Function串联
 * 多个Function串联的执行顺序是，比如：a.andThen(b).andThen(c).apply(d)
 * 是把d传给a，然后a的输出作为b的输入，b的输出作为c的输入
 */
public class FunctionDemo {
    public static void main(String[] args) {
        Function<Integer, String> toString = i -> String.valueOf(i + 1);
        Function<String, Integer> toInt = Integer::valueOf;
        String s = toString.apply(1);
        System.out.println(s);
        int a = toString.andThen(toInt).apply(1);
        System.out.println(a);
    }
}
