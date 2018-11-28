package com.bigwanggang.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class StatisticOfCode {
    public static final String Path = "path";

    public static  int total = 0;

    public static void main(String[] args) throws IOException {
        scanAllFiles(new File(Path));
        System.out.println(total);
    }

    public static void scanAllFiles(File file) throws IOException {
        File[] files = file.listFiles();
        for (File f : files) {
            if (f.isDirectory()) {
                scanAllFiles(f);
            } else if (f.isFile()) {
                String fileName = f.getName();
                if (fileName.endsWith(".java")) {
                    int line = totalLines(f);
                    total += line;
                    System.out.println(fileName + "\t\t" + line);

                }
            }
        }
    }

    public static int totalLines(File f) throws IOException {
        int lines = 0;
        BufferedReader br = new BufferedReader(new FileReader(f));
        while (br.readLine() != null) {
            lines++;
        }
        return lines;
    }

}
