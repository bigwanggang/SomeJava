package com.gustavo.classloader;

import java.io.*;

/**
 * 码出高效 中例子
 * Created by gustaov on 2019/6/12.
 */
public class MyClassLoader extends ClassLoader {

    private final static String path = System.getProperty("user.dir") + "\\src\\main\\resources\\";

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] bytes = getClassFromPath(name);
        System.out.println(bytes.length);
        return defineClass(name, bytes, 0, bytes.length);
    }

    private byte[] getClassFromPath(String name) {
        InputStream in = null;
        byte[] tmp = new byte[2048];
        name= name.replace(".", "\\");
        String filePath= path + name + ".class";
        ByteArrayOutputStream outputStream = null;
        try {
            in = new FileInputStream(filePath);
            outputStream = new ByteArrayOutputStream();
            int i = -1;

            while ((i = in.read(tmp)) != -1) {
                outputStream.write(tmp, 0, i);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputStream.toByteArray();

    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        MyClassLoader loader = new MyClassLoader();
        Class clazz =loader.findClass("com.gustavo.HelloWorld");
        System.out.println(clazz.getClassLoader());
        System.out.println(loader);
        System.out.println(loader.getParent());
        System.out.println(loader.getParent().getParent());
    }
}
