package com.bigwanggang.Jmockit;

import junit.framework.Assert;
import mockit.NonStrictExpectations;
import mockit.Verifications;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JMockit.class)
public class MyObject1 {

    @Test
    public void testHello() {
        final MyObject obj = new MyObject();
        new NonStrictExpectations(obj) {
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
