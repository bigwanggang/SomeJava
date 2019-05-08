package com.gustavo.dataStructureAndAlgo;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by gustaov on 2019/5/8.
 */
public class FindKLargestNumInArrayTest {
    @Test
    public void kLargest() throws Exception {
        int[] num1 = {4, 3, 6, 1, 3, 2, 7, 7, 2, 9, 8, 5, 6, 10};
        Assert.assertEquals(10, FindKLargestNumInArray.kLargest(num1, 1));
        Assert.assertEquals(9, FindKLargestNumInArray.kLargest(num1, 2));
        Assert.assertEquals(8, FindKLargestNumInArray.kLargest(num1, 3));
        Assert.assertEquals(7, FindKLargestNumInArray.kLargest(num1, 4));
        Assert.assertEquals(6, FindKLargestNumInArray.kLargest(num1, 5));

    }

}