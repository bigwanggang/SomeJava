package com.gustavo.classloader;

import java.io.*;

/**
 * 码出高效 的栗子的扩展
 * 本栗中，自定义一个ClassLoader,加载class文件，然后classpath下还有个同样的类（类的全限定名一样），
 * 但是通过instanceof 判断返回false，因为两个类是通过不同类加载器加载的
 * Created by gustaov on 2019/6/12.
 */
public class MyClassLoader extends ClassLoader {

    private final static String path = System.getProperty("user.dir") + "\\src\\main\\resources\\";

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        MyClassLoader loader = new MyClassLoader();
        Class clazz = loader.findClass("com.gustavo.HelloWorld");
//        Object o = clazz.newInstance();
//        System.out.println(o.getClass().getClassLoader());


    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] bytes = getClassFromPath(name);
        System.out.println(bytes.length);
        return defineClass(name, bytes, 0, bytes.length);
    }

    private byte[] getClassFromPath(String name) {
        InputStream in = null;
        byte[] tmp = null;
        name = name.replace(".", "\\");
        String filePath = path + name + ".class";
        ByteArrayOutputStream outputStream = null;
        try {
            in = new FileInputStream(filePath);

            tmp = new byte[in.available()];
            in.read(tmp);

//            while ((i = in.read(tmp)) != -1) {
//                outputStream.write(tmp, 0, i);
//            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tmp;

    }
}
