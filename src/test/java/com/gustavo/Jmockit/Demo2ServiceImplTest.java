package com.gustavo.Jmockit;

import mockit.Expectations;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JMockit.class)
public class Demo2ServiceImplTest {
    @Mocked
    Demo2ServiceImpl demo2Service;


    @Test
    public void testSayName() {
        new Expectations() {

            {

                demo2Service.isTp();
                result = true;
            }
        };
        Assert.assertEquals(demo2Service.sayName(), "valid");
    }
}