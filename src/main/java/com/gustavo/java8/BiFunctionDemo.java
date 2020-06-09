package com.gustavo.java8;

import java.util.function.BiFunction;

public class BiFunctionDemo {
    public static void main(String[] args) {
        BiFunction<String, Integer, Person> p = Person::new;
        System.out.println(p.apply("james", 23));
    }

    static class Person {
        String name;
        Integer age;

        public Person(String name, Integer age) {
            this.name = name;
            this.age = age;
        }
    }
}
