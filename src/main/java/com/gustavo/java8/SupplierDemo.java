package com.gustavo.java8;

import java.util.function.Supplier;

/**
 * 通过Supplier接口只能调用无参构造器
 */
public class SupplierDemo {
    public static void main(String[] args) {
        Supplier<Person> supplier = Person::new;
        System.out.println(supplier.get());
        System.out.println(supplier.get());

    }

    static class Person {
        private String name;

        public Person() {
            this.name = "default";
        }
    }
}
