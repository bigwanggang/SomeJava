package com.gustavo.dataStructureAndAlgo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * n个数的和的问题,本例子：在一个数组中找出3个数的和为0的组合
 * 该问题可以发散很多，结果中重复不重复的、4个数的和，5个数的和、n个数的和等于target、n个数的和最接近target
 */
public class NSumProblem {
    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        int[] nums1 = {-1, 0, -2, -2, -3, 1, 2, -3, -3, -1, -4};
        NSumProblem solution = new NSumProblem();
        System.out.println(solution.threeSum(nums));
        int[] num2 = {1, 1, 1, 1, 1, 1};
        System.out.println(solution.twoSum(num2, 0));
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> ans = new ArrayList<>();
        helper(result, ans, nums, 0, 0, 0);
        return result;
    }

    private void helper(List<List<Integer>> result, List<Integer> ans, int[] nums, int num, int sum, int begin) {
        if (num == 3 && sum == 0) {
            List<Integer> tmp = new ArrayList<>(ans);
            result.add(tmp);
            return;
        }
        for (int j = begin; j < nums.length; j++) {
            ans.add(nums[j]);
            sum += nums[j];
            num++;

            helper(result, ans, nums, num, sum, j + 1);
            num--;
            ans.remove(ans.size() - 1);
            sum -= nums[j];
        }
    }

    public List<List<Integer>> twoSum(int[] nums, int target) {
        List<List<Integer>> answer = new ArrayList<>();
        Arrays.sort(nums);
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == target) {
                List<Integer> result = new ArrayList<>();
                result.add(nums[left]);
                result.add(nums[right]);
                answer.add(result);
                left++;
                right--;
                while (left < right && nums[left] == nums[left - 1])
                    left++;
                while (left < right && nums[right] == nums[right + 1])
                    right--;
            } else if (sum > target) {
                right--;
                while (left < right && nums[right] == nums[right + 1])
                    right--;
            } else {
                left++;
                while (left < right && nums[left] == nums[left - 1])
                    left++;
            }
        }
        return answer;
    }
}
