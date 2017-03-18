package com.bigwanggang.DynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by gustaov on 2017/3/18.
 */
public class LogInterceptor implements InvocationHandler {
    private Object target;      //被代理的对象

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public void beforeMethod(Method m) {
        System.out.println(m.getName() + " start");
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        beforeMethod(method);
        method.invoke(target, args);
        return null;
    }
}
