package com.gustavo.tools;

import java.io.*;

public class DeleteCertainLines {
    public static void main(String[] args) {
        String path = "path";
        f(new File(path));
    }

    public static void f(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            int i = 0;
            for (File f : files) {
                f(f);
            }
        } else if (file.getName().endsWith(".java")) {

            System.out.println(file.getAbsolutePath());

            deleteCertainLines(file);
        }
    }

    private static void deleteCertainLines(File file) {
        StringBuilder sb = new StringBuilder();
        boolean isModify = false;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = br.readLine()) != null) {
                if (ifRead(line)) {
                    sb.append(line + "\r\n");
                } else {
                    isModify = true;
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (isModify) {
            try {
                FileWriter fw = new FileWriter(file.getAbsolutePath());
                fw.append(sb.toString());
                fw.flush();
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(file.getAbsolutePath() + " need to be modified");
        }
    }

    /**
     * 判断读取行的条件
     * @param line
     * @return
     */
    private static boolean ifRead(String line) {
        return (!line.contains("DebugPrn") && !line.contains("debugLog"));
    }

}
