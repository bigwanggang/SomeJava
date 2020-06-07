package com.gustavo.java8;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;

/**
 * listFiles的入参FileFilter是函数式接口，接口的唯一方法：boolean accept(File pathname);
 * 只要入参和返回值和accept方法能对应上的lambda表达式，都可以使用
 */
public class ListFiles {
    public static void main(String[] args) {
        java6Demo();
        System.out.println("------------");
        java8Demo();
    }

    private static void java6Demo() {
        File[] files = new File(".").listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isFile();
            }
        });
        for (File file : files) {
            System.out.println(file);
        }
    }
    private static void java8Demo() {
        File[] files = new File(".").listFiles(File::isFile);
        Arrays.stream(files).forEach(System.out::println);
    }
}
