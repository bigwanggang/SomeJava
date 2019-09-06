package com.gustavo.dynamicProxy.demo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyDemo {
    public static void main(String[] args) {
        ADriver driver = new ADriver();
        Driver d = (Driver) getProxy(driver);

        d.drive();

    }

    static Object getProxy(final Object target) {
        Object proxy = Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("proxy intance");
                        method.invoke(target, args);
                        return null;
                    }
                });
        return proxy;
    }
}
