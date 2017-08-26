package com.bigwanggang.Jmockit;

import junit.framework.Assert;
import mockit.*;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JMockit.class)
public class MyObjectTest {
    @Mocked
    MyObject obj;

    @Test
    public void testHello() {

        Assert.assertEquals(null, obj.hello(""));
        Assert.assertEquals(null, obj.returnString());
    }
}
