package com.gustavo.jdbc;

import org.junit.Assert;
import org.junit.Test;

public class MysqlConnectionFactoryTest {

    @Test
    public void getConnection() {
        String url = "jdbc:mysql://localhost:3306/test";
        Assert.assertNotNull(MysqlConnectionFactory.getConnection(url, "root", ""));
    }
}
