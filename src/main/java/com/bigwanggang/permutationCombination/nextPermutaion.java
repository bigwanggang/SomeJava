package com.bigwanggang.permutationCombination;

public class nextPermutaion {

    public static void nextpermutation(int[] nums) {
        if (nums.length < 2)
            return;
        int lessThanRightIndex = nums.length - 2;

        while (lessThanRightIndex >= 0 && nums[lessThanRightIndex] >= nums[lessThanRightIndex + 1])
            lessThanRightIndex--;
        int biggerAndClosestToIndex = lessThanRightIndex + 1;

        if (lessThanRightIndex >= 0) {
            int biggerDiff = nums[biggerAndClosestToIndex] - nums[lessThanRightIndex];
            for (int i = biggerAndClosestToIndex; i < nums.length; i++) {
                if (nums[i] > nums[lessThanRightIndex] && nums[i] - nums[lessThanRightIndex] <= biggerDiff) {
                    biggerDiff = nums[i] - nums[lessThanRightIndex];
                    biggerAndClosestToIndex = i;
                }
            }
            int tmp = nums[lessThanRightIndex];
            nums[lessThanRightIndex] = nums[biggerAndClosestToIndex];
            nums[biggerAndClosestToIndex] = tmp;
        }

        int left = lessThanRightIndex + 1;
        int right = nums.length - 1;
        while (left < right) {
            int tmp = nums[left];
            nums[left] = nums[right];
            nums[right] = tmp;
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        int[] a = {2,3,1,3,3};
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
        nextpermutation(a);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }
}
