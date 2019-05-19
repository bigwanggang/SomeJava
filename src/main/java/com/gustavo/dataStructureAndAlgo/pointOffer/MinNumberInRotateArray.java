package com.gustavo.dataStructureAndAlgo.pointOffer;

/**
 * 剑指offer 面试题8：旋转数组的最小数字
 * Created by gustaov on 2019/5/8.
 */
public class MinNumberInRotateArray {
    public static int searchSmallest(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            if (nums[left] <= nums[right]) {
                return nums[left];
            }
            int mid = (left + right) / 2;
            if (nums[mid] < nums[right]) {
                right = mid;
            } else {
                left = mid+1;
            }
        }
        return -1;
    }
}
