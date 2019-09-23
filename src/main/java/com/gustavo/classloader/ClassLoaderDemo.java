package com.gustavo.classloader;


//import sun.util.resources.ar.CalendarData_ar;

/**
 * Created by gustaov on 2019/6/16.
 */
public class ClassLoaderDemo {
    public static void main(String[] args) {
        System.out.println(ClassLoader.getSystemClassLoader());
        ClassLoader c = ClassLoaderDemo.class.getClassLoader();
        // String 的classloader 为bootstrapClassLoader，在java中为null
        String s = "a";
        System.out.println(s.getClass().getClassLoader());
        System.out.println(c);
        System.out.println(System.getProperty("sun.boot.class.path"));
        System.out.println(System.getProperty("java.class.path"));
        System.out.println(System.getProperty("java.ext.dirs"));

        //CalendarData_ar的classLoaderw为extClassLoader
//        Class clazz = CalendarData_ar.class;
//        System.out.println(clazz.getClassLoader());

    }
}
