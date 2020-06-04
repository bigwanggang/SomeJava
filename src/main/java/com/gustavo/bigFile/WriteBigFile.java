package com.gustavo.bigFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class WriteBigFile {
    public static void main(String[] args) throws IOException {
        File file = new File("2.txt");
        FileOutputStream fos = new FileOutputStream(file);
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 100; j++) {
                try {
                    String t = "helloworld";
//                    String t = "helloworld:" + i + "-" + j + "||";
                    fos.write(t.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            fos.write('\n');
        }

    }
}
