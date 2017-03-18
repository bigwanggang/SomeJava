package com.bigwanggang.Jmockit;

import junit.framework.Assert;
import mockit.Mocked;
import mockit.NonStrictExpectations;
import mockit.Verifications;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JMockit.class)
public class MyObjectTest {
    @Mocked
    MyObject obj;

    @Test
    public void testHello() {
        new NonStrictExpectations() {
            {
                obj.hello("nihao");
                returns("hello nihao");
            }
        };
        Assert.assertEquals("hello nihao", obj.hello("nihao"));
        new Verifications() {
            {
                obj.hello("nihao");
                times = 1;
            }
        };
    }
}
