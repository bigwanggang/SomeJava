package com.gustavo.dataStructureAndAlgo;

import com.gustavo.dataStructureAndAlgo.pointOffer.NumberOfBit;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by gustaov on 2019/5/8.
 */
public class NumberOfBitTest {
    @Test
    public void numberOf1() throws Exception {
        Assert.assertEquals(1, NumberOfBit.numberOf1(1));
        Assert.assertEquals(2, NumberOfBit.numberOf1(9));
        Assert.assertEquals(4, NumberOfBit.numberOf1(15));
        Assert.assertEquals(31, NumberOfBit.numberOf1(Integer.MAX_VALUE));
        Assert.assertEquals(29, NumberOfBit.numberOf1(Integer.MAX_VALUE - 6));
        Assert.assertEquals(1, NumberOfBit.numberOf1(Integer.MIN_VALUE));
        Assert.assertEquals(32, NumberOfBit.numberOf1(-1));



    }

}