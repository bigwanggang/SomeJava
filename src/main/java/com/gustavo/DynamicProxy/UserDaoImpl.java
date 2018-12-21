package com.gustavo.DynamicProxy;

/**
 * Created by gustaov on 2017/3/18.
 */
public class UserDaoImpl implements UserDao {
    @Override
    public void save(User u) {
        System.out.println("user saved");
    }
}
