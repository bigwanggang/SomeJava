package com.gustavo.bigFile;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 利用Guava提供的工具读取文件，是要将文件的所有内容缓存，如果文件过大，则必定内存不够
 */
public class ReadFileError {
    public static void main(String[] args) {
        try {
            List<String> lines = Files.readLines(new File("1.txt"), Charsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
