package com.gustavo.leetcodesolutions;

public class _031_NextPermutation {
    public static void nextPermutation(int[] nums) {
        if (nums.length < 2)
            return;
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        if (i >= 0) {
            int k = i + 1;
            int mark = k;
            int diff = nums[k] - nums[i];
            while (k < nums.length) {
                if (nums[k] > nums[i] && (nums[k] - nums[i]) <= diff) {
                    mark = k;
                }
                k++;
            }
            swap(nums, i, mark);
        }
        reverse(nums, i + 1, nums.length - 1);
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }


    private static void reverse(int[] nums, int left, int right) {
        for (int i = left, j = right; i < j; i++, j--) {
            swap(nums, i, j);
        }
    }

    public static void main(String[] args) {
        int[] nums = {2,3,1,3,3};
        for (int i = 0; i < 10; i++) {
            print(nums);
            nextPermutation(nums);
        }

    }

    private static void print(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();
    }
}
