package com.gustavo.java8;

import java.util.function.Consumer;

/**
 * Consumer接口，accept方法，接受一个入参，无返回值，所有操作都在方法内执行，consumer的名字起的很准确
 * andThen方法是将多个consumer组合到一起，顺序是从前到后
 */
public class ConsumerDemo {
    public static void main(String[] args) {
        Consumer<Integer> add1 = s -> {

            System.out.println(s + 1);
        };
        Consumer<Integer> add2 = s -> {

            System.out.println(s + 2);
        };
        add1.accept(1);
        add2.accept(2);
        add1.andThen(add1).andThen(add2).accept(1);

    }
}
