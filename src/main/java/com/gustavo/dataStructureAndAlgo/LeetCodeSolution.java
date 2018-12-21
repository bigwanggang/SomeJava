package com.gustavo.dataStructureAndAlgo;

public class LeetCodeSolution {
    public int firstMissingPositive(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            if (nums[i] <= nums.length && nums[i] > 0 && nums[i] != i + 1 && nums[nums[i] - 1] != nums[i]) {
                int tmp = nums[i];
                nums[i] = nums[tmp - 1];
                nums[tmp - 1] = tmp;
            } else
                i++;
        }
        for (i = 0; i < nums.length; i++)
            if (nums[i] != i + 1)
                break;
        return i + 1;
    }

    public int trap(int[] height) {
        int curHight = 0;
        int total = 0;
        int l = 0;
        int r = height.length - 1;
        while (l < r) {
            if (Math.min(height[l], height[r]) > curHight) {
                curHight = Math.min(height[l], height[r]);
                if (height[l] < height[r])
                    l++;
                else
                    r--;
                continue;
            }
            if (curHight >= height[l]) {
                total += (curHight - height[l]);
                l++;
            }
            if (curHight >= height[r]) {
                total += (curHight - height[r]);
                r--;
            }
        }
        return total+curHight-height[l];
    }

    public static void main(String[] args) {
        LeetCodeSolution solution = new LeetCodeSolution();
        int[] a = {2,1,0,2};
        System.out.println(solution.trap(a));
    }
}
