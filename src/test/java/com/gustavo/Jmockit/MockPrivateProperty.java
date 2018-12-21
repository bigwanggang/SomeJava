package com.gustavo.Jmockit;

import mockit.NonStrictExpectations;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static mockit.Deencapsulation.setField;
import static org.junit.Assert.assertEquals;

@RunWith(JMockit.class)
public class MockPrivateProperty {
    @Test
    public void testMockPrivateProperty() throws IOException {
        final ClassMocked obj = new ClassMocked();
        new NonStrictExpectations(obj) {
            {
                setField(obj, "name", "name has bean change!");
            }
        };

        assertEquals("name has bean change!", obj.getName());
    }

    @Test
    public void testMockPrivateStaticProperty() throws IOException {
        new NonStrictExpectations(ClassMocked.class) {
            {
                setField(ClassMocked.class, "className", "className has bean change!");
            }
        };

        assertEquals("className has bean change!", ClassMocked.getClassName());
    }
}
