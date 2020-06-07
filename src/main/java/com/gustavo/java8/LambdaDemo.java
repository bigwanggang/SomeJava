package com.gustavo.java8;

public class LambdaDemo {
    public static void main(String[] args) {
        int num = 1;
        Converter<String, Integer> c = Integer::valueOf;
        System.out.println(c.convert("12"));
        Converter<Integer, String> c1 = (from -> String.valueOf(from+num));
        System.out.println(c1.convert(234));
    }
}

interface Converter<F, T> {
    T convert(F from);
}