package com.gustavo.classloader;

import java.io.*;

/**
 * 网上找的栗子
 * Created by gustaov on 2019/6/12.
 */
public class MyClassLoader_1 extends ClassLoader {

    private final static String PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\";

    private String path;   //类的加载路径
    private String name;   //类加载器的名字

    public MyClassLoader_1() {}
    public MyClassLoader_1(String path, String name){
        this.path = path;
        this.name = name;
    }


    //用于寻找类文件
    public Class findClass(String name) {
        byte[] b = new byte[0];
        try {
            b = loadClassData(name);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(b.length);
        return defineClass(name, b, 0, b.length);
    }

    //用于加载类文件
    private byte[] loadClassData(String name) throws FileNotFoundException {
        byte[] result = null;
        name =name.replace(".", "//");
        name = path + name + ".class";
        if(!new File(name).exists()){
            throw new FileNotFoundException(name);
        }
        //使用输入流读取类文件
        InputStream in = null;
        //使用byteArrayOutputStream保存类文件。然后转化为byte数组
        ByteArrayOutputStream out = null;
        try {
            in = new FileInputStream(new File(name));
            out = new ByteArrayOutputStream();
            int i = 0;
            while ( (i = in.read()) != -1){
                out.write(i);
            }

            result = out.toByteArray();
        }catch (Exception e){}
        finally {
            try {
                if(out!=null) {
                    out.close();
                }
                if(in!=null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return result;

    }

    public String getName() {
        return name;
    }

    public void setPath(String path) {
        this.path = path;
    }


    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        MyClassLoader_1 m = new MyClassLoader_1(PATH,"");

        Class c = m.findClass("com.gustavo.HelloWorld");
        System.out.println(c.getClassLoader());
        Class.forName("com.gustavo.HelloWorld");
        c.newInstance();
    }
}
