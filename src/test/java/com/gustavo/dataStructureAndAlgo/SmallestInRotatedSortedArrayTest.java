package com.gustavo.dataStructureAndAlgo;

import com.gustavo.dataStructureAndAlgo.pointOffer.MinNumberInRotateArray;
import org.junit.Assert;
import org.junit.Test;

public class SmallestInRotatedSortedArrayTest {

    @Test
    public void searchSmallest() {
        int[] nums = {1};
        Assert.assertEquals(1, MinNumberInRotateArray.searchSmallest(nums));
        int[] nums1 = {5, 6, 7, 1, 2, 3, 4};
        Assert.assertEquals(1, MinNumberInRotateArray.searchSmallest(nums1));
        int[] nums2 = {5, 6, 7, 1,  3, 4};
        Assert.assertEquals(1, MinNumberInRotateArray.searchSmallest(nums2));
    }
}
