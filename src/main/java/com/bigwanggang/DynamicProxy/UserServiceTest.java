package com.bigwanggang.DynamicProxy;

import java.lang.reflect.Proxy;

/**
 * Created by gustaov on 2017/3/18.
 */
public class UserServiceTest {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoImpl();
        LogInterceptor log = new LogInterceptor();
        log.setTarget(userDao);
        UserDao userDaoProxy = (UserDao) Proxy.newProxyInstance(userDao.getClass().getClassLoader(),userDao.getClass().getInterfaces(), log);
        System.out.println(userDao.getClass().getInterfaces().getClass());
        System.out.println(userDaoProxy.getClass().getInterfaces().getClass());
        userDaoProxy.save(new User());
    }
}
