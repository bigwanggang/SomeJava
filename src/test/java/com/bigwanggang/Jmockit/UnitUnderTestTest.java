package com.bigwanggang.Jmockit;

import mockit.Expectations;
import mockit.NonStrictExpectations;
import mockit.Mocked;
import mockit.Verifications;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;


@RunWith(JMockit.class)
public class UnitUnderTestTest {
    @Mocked
    DependencyAbc abc;
    @Test
    public void doSomethingHandlesSomeCheckedException() throws Exception {
        new Expectations() {
//            DependencyAbc abc;

            {
//                new DependencyAbc();

                abc.intReturningMethod();
                result = 3;

                abc.stringReturningMethod();
                returns("str1", "str2");
                result = new SomeCheckedException();
            }
        };

        new UnitUnderTest().doSomething();
        new Verifications() {

        };
    }
}