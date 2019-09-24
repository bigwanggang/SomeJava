package com.gustavo.distribute.mysql_distribute_lock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class MySqlLock implements Lock {
    private final static String LOCK_SQL = "insert into lock1 values ('Lock')";
    private final static String UNLOCK_SQL = "delete from lock1 where name ='Lock'";

    private Connection connection;

    public MySqlLock(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void lock() {
        while (!tryLock())
            ;
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        try {
            PreparedStatement ps = connection.prepareStatement(LOCK_SQL);
            boolean result = ps.execute();
            return result;
        } catch (SQLException e) {

            return false;
        }
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        try {
            PreparedStatement ps = connection.prepareStatement(UNLOCK_SQL);
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
