package com.gustavo.java8;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * BiConsumer 跟Consumer类似，区别是accept入参是两个，同样无返回值
 * 如果不好理解，只需记住，BiConsumer是消费，lambda表达式里面写的是怎么消费传入的参数
 */
public class BiConsumerDemo {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("a", "a");
        map.put("b", "b");
        map.put("c", "c");
        map.put("d", "d");
        map.forEach((k, v) -> {
            System.out.println(k);
            System.out.println(v);
        });

        BiConsumer<Integer, String> doubleConsumer = (i, s) -> {
            System.out.println(i * 2);
            System.out.println(s + s);
        };

        doubleConsumer.accept(3, "hello");
    }
}
