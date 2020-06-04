package com.gustavo.distribute.mysql_distribute_lock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class MySqlLock implements Lock {
    private final static String LOCK_SQL = "insert into lock1 values ('Lock')";
    private final static String UNLOCK_SQL = "delete from lock1";

    private Connection connection;
    private PreparedStatement lockPs;
    private PreparedStatement unlockPs;


    public MySqlLock(Connection connection) throws SQLException {
        this.connection = connection;
        lockPs = connection.prepareStatement(LOCK_SQL);
        unlockPs = connection.prepareStatement(UNLOCK_SQL);
    }

    @Override
    public void lock() {
        if (!tryLock()) {
            System.out.println(Thread.currentThread().getName() + " get lock failure");
            lock();
        }
        System.out.println(Thread.currentThread().getName() + " get lock ***********");
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        try {
            int result = lockPs.executeUpdate();
            return result > 0;
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
            unlockPs.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

        }

    }

    @Override
    public Condition newCondition() {
        return null;
    }


}
