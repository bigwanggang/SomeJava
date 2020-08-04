package com.gustavo.tools;

import java.io.File;

/**
 * 统计一个文件下每个文件的大小
 */
public class AllFileSizeOfFolder {
    static int num = 3; //找出该目录下文件大小前100的文件

    public static void main(String[] args) {

        String path = "d:\\com";
        File[] bigFiles = new File[num];
        for (int i = 0; i < num; i++) {
            bigFiles[i] = new File("");
        }
        helper(new File(path), bigFiles);
        for (File bigFile : bigFiles) {
            System.out.println(bigFile.getAbsolutePath() + " : " + bigFile.length());
        }
    }

    private static void helper(File file, File[] bigFiles) {
        File[] files = file.listFiles();
        for (File file1 : files) {
            if (file1.isDirectory()) {
                helper(file1, bigFiles);
            } else if (file1.isFile()) {
                long length = file1.length();
                int index = num - 1;
                while (index > 0 && length > bigFiles[index].length()) {
                    bigFiles[index] = bigFiles[index - 1];
                    index--;
                }
                if (index < num -1 || (index == num -1 && length > bigFiles[index].length())) {
                    bigFiles[index] = file1;
                }
            }
        }

    }

}
