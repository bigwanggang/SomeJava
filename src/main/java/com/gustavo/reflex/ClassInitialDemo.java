package com.gustavo.classloader;

/**
 * 类初始化的几种场景举例
 * 此处例举了5个场景，还有两个场景也是要执行类初始化， 1. 执行main入口类要初始化， 2. 子类初始化，要先执行父类初始化
 */
public class ClassInitialDemo {
    public static void main(String[] args) {
        //1
//        new Hello();
        //2
//        Hello.staticFunction();
        //3
//        System.out.println(Hello.a);
        //4
//        try{
//            Class.forName("com.gustavo.classloader.Hello");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
        //5
//        try {
//            Class clazz = Class.forName("com.gustavo.classloader.Hello", false, ClassInitialDemo.class.getClassLoader());
//            clazz.newInstance();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        }


    }
}
