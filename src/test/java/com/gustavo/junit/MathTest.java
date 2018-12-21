package com.gustavo.junit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.*;

public class MathTest {
    private Math math;

    @Before
    public void before() {
        math = new Math();
    }

    @Test
    public void add() throws Exception {
        Assert.assertThat(math.add(1, 3), lessThan(5));
        Assert.assertThat(math.add(2, 4), is(6));
        Assert.assertThat(math.add(2, 4), allOf(lessThan(7), greaterThan(5)));
    }

}
