package com.gustavo.classloader;

/**
 * 类初始化的几种场景举例
 * 此处例举了6个场景，还有两个场景也是要执行类初始化：  执行main入口类要初始化， 和 子类初始化，要先执行父类初始化
 * 4: Class.forName 常用的有两种调用方式，Class.forName(String className) 和Class.forName(String name, boolean initialize,ClassLoader loader)
 * Class.forName(String name, boolean initialize,ClassLoader loader) 的第二个参数是是否执行初始化的过程（加载、连接、初始化）
 * 而Class.forName(String className) 没有指定initialize和loader，这时initialize的默认值为true，也就是默认执行类初始化
 * 4和5的区别就是是否执行类初始化， 5中clazz.newInstance()才执行类的初始化，如果注释掉该行，不会执行static代码块
 * 而loader就是默认的类加载器
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
//        try {
//            Class.forName("com.gustavo.classloader.Hello");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
        //5
        try {
            Class clazz = Class.forName("com.gustavo.classloader.Hello", false, ClassInitialDemo.class.getClassLoader());
//            clazz.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
//        catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        }
        //6
//        try {
//            Constructor constructor = Hello.class.getConstructor(String.class);
//            constructor.newInstance("hello world");
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
    }
}
