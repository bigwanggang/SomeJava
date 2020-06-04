package com.gustavo.bigFile;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileDemo {
    public static void main(String[] args) throws IOException {
        RandomAccessFile acf = new RandomAccessFile(new File("2.txt"), "r");
        System.out.println(acf.length());
    }
}
