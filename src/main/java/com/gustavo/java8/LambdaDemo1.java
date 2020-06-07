package com.gustavo.java8;

import java.io.File;
import java.io.FileFilter;

public class LambdaDemo1 {
    public static void main(String[] args) {
        FileFilter filter = File::isFile;


    }
}

interface IsFile{
    boolean isFile(File f);
}