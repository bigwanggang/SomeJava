package com.gustavo.bigFile;

import java.io.File;
import java.io.FileInputStream;

public class ReadBigFile {
    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream(new File("2.txt"));
        byte[] b = new byte[2048];
        int n = 0;
        long l = 0L;
        int i = 0;
        while ((n = fis.read(b)) != -1)  //当n不等于-1,则代表未到末尾
        {
            System.out.println(n);
            l += n;
            i++;
        }
        System.out.println("total bytes: " + l);
        System.out.println("read time: " + i);
    }
}
