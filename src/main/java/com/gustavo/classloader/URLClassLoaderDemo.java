package com.gustavo.classloader;

import com.gustavo.Hello;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * 通过URLClassLoader 加载任意路径的class
 * 在通过classLoader.loadClass 加载完任意class之后，通过clazz.getClassLoader()测试类加载器时，容易出现一个错误：
 * 如果该class是在相同的ide下编译的，有可能在加载的时候并没有加载你想要加载的那个class文件，而是加载的ide编译后的class路径
 * 即使你把java文件删除，有时class文件还在，所以测试该功能时，最好是在其他地方编译的class文件
 * 最好是将class放在其他地方，例如放在d盘下
 */
public class URLClassLoaderDemo {
    public static void main(String[] args) throws MalformedURLException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        String path = "d:\\";
        path = "file:" + path;

        System.out.println(path);
        URLClassLoader classLoader = new URLClassLoader(new URL[]{new URL(path)});
        Class clazz = classLoader.loadClass("com.demo.HelloWorld");
        Object obj = clazz.getConstructor(String.class).newInstance("hello");
        Object obj1 = clazz.getConstructor(String.class).newInstance("hello");
        System.out.println("same classloader, if equals: " + obj.equals(obj1));

        URLClassLoader classLoader1 = new URLClassLoader(new URL[]{new URL(path)});
        Class clazz1 = classLoader1.loadClass("com.demo.HelloWorld");
        Object obj2 = clazz1.getConstructor(String.class).newInstance("hello");

        System.out.println("two classloader, if equals: " + obj2.equals(obj));
        System.out.println(classLoader1);
        System.out.println(obj2.getClass().getClassLoader());

        clazz.getDeclaredMethod("hello").invoke(obj);
        System.out.println(clazz.getClassLoader());
        System.out.println(ClassLoader.getSystemClassLoader());
        System.out.println(obj instanceof Hello);

    }
}
