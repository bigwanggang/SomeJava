package com.bigwanggang.Jmockit;

import mockit.NonStrictExpectations;
import mockit.Verifications;
import mockit.integration.junit4.JMockit;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JMockit.class)
public class StaticMock {
    @Test
    public void testMockStaticMethod() {
        new NonStrictExpectations(MyObject.class) {
            {
                MyObject.tripple(1);//也可以使用参数匹配：ClassMocked.getDouble(anyDouble);
                result = 3;
            }
        };

        Assert.assertEquals(3, MyObject.tripple(1));

        new Verifications() {
            {
                MyObject.tripple(1);
                times = 1;
            }
        };
    }
}

