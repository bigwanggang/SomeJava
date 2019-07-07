package com.gustavo.dynamicProxy;

/**
 * 动态代理的目标对象
 * Created by gustaov on 2017/3/18.
 */
public class UserDaoImpl implements UserDao {
    @Override
    public void save(User u) {
        System.out.println("user saved");
    }
}
