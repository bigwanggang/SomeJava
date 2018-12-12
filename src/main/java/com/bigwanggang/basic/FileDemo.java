package com.bigwanggang.basic;

import java.io.File;

/**
 * Created by gustaov on 2018/12/12.
 */
public class FileDemo {
    public static void main(String[] args) {
            String arg = "C:\\Users\\gustaov\\Desktop\\tmp\\test.class";
            System.out.println("string: " + arg);
            File file = new File(arg);
            System.out.println("getAbsolutePath: " + file.getAbsolutePath());
            System.out.println("isDirectory: " + file.isDirectory());
            System.out.println("isAbsolute: " + file.isAbsolute());
            System.out.println("isFile: " + file.isFile());
            System.out.println("exist: " + file.exists());
    }
}
