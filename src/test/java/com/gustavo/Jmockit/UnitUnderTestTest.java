package com.gustavo.Jmockit;

import mockit.*;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(JMockit.class)
public class UnitUnderTestTest {
    @Mocked
    DependencyAbc abc;

    @Test
    public void doSomethingHandlesSomeCheckedException() throws Exception {
        new Expectations() {
            {
                abc.intReturningMethod();
                result = 3;
            }

        };

        UnitUnderTest test = new UnitUnderTest();
        Deencapsulation.setField(test, "abc", abc);
        test.doSomething();

        new Verifications() {


            {
                abc.stringReturningMethod();
                times = 3;
            }
        };
    }
}