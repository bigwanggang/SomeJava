package com.gustavo.dataStructureAndAlgo;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by gustaov on 2019/5/8.
 */
public class FibonacciTest {
    @Test
    public void fabonacci() throws Exception {
        Assert.assertEquals(0, Fibonacci.fabonacci(0));
        Assert.assertEquals(1, Fibonacci.fabonacci(1));
        Assert.assertEquals(1, Fibonacci.fabonacci(2));
        Assert.assertEquals(2, Fibonacci.fabonacci(3));
        Assert.assertEquals(3, Fibonacci.fabonacci(4));
        Assert.assertEquals(5, Fibonacci.fabonacci(5));
        Assert.assertEquals(89, Fibonacci.fabonacci(11));
    }

}