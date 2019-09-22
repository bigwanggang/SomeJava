package com.gustavo.gc;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;

/**
 * -XX:PermSize=2M -XX:MaxPermSize=4M -XX:+PrintGCDetails
 * Created by gustaov on 2019/9/22.
 */
public class JavassistDemo {
    public static void main(String[] args) throws NotFoundException, CannotCompileException, IllegalAccessException, InstantiationException {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            CtClass c = ClassPool.getDefault().makeClass("MyClass" + i);
            c.setSuperclass(ClassPool.getDefault().get("com.gustavo.gc.JavaBeanObject"));
            Class clz = c.toClass();
            JavaBeanObject v = (JavaBeanObject) clz.newInstance();
        }
    }

}
