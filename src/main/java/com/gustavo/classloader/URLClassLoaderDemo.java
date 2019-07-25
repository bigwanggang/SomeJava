package com.gustavo.classloader;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * 通过URLClassLoader 加载任意路径的class
 */
public class URLClassLoaderDemo {
    public static void main(String[] args) throws MalformedURLException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        String path = System.getProperty("user.dir") + "\\src\\main\\resources\\";
        path = "file:" + path;

        System.out.println(path);
        URLClassLoader classLoader = new URLClassLoader(new URL[]{new URL(path)});
        Class clazz = classLoader.loadClass("com.gustavo.HelloWorld");
        Object obj = clazz.newInstance();
        clazz.getDeclaredMethod("hello").invoke(obj);

    }
}
