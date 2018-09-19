package com.bigwanggang.tools;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindAllFiles {
    static String path = "D:\\workspace\\code\\";
    static Pattern pattern = Pattern.compile(".*.java");
    static String[] findString = {"Thread", "Runnable"};

    public static void main(String[] args) {
        iteratAllFiles(new File(path));

    }

    public static void iteratAllFiles(File file) {
        if (file.isFile()) {
            String fileName = file.getName();
            Matcher m = pattern.matcher(fileName);
            if (m.matches()) {
                boolean find = findStringInFile(file);
                if (find) {
                    System.out.println("fileName: " + file.getAbsolutePath() + "\t\t" + findStringInFile(file));
                }
            }
        } else {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                iteratAllFiles(files[i]);
            }
        }
    }

    private static boolean findStringInFile(File f) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(f));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            String line = "";
            while ((line = br.readLine()) != null) {
                for (int i = 0; i < findString.length; i++) {
                    if (line.contains(findString[i])) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static int countLines(File f) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(f));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int line = 0;
        try {
            while (br.readLine() != null) {
                line++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }
}
