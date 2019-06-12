package com.gustavo.basic;

import java.io.*;

/**
 * Created by gustaov on 2019/6/12.
 */
public class CopyFile {
    public static void copy(String src, String dest) throws FileNotFoundException {
        InputStream in = new FileInputStream(src);
        OutputStream out = new FileOutputStream(dest);
        int i =-1;
        try{
        while ((i=in.read())!=-1){
            out.write(i);
        }} catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(in!=null)
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if(out!=null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        String src = "d:\\1.txt";
        String dest = "e:\\2.txt";
        copy(src,dest);
    }
}
