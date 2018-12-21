package com.gustavo.leetcodesolutions;

import java.util.HashMap;

/**
 * Created by gustaov on 2018/9/2.
 */
public class _001_twoSum {

    /**
     * o(n^2)
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum_1(int[] nums, int target) {
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return result;
    }

    public int[] twoSum_2(int[] nums, int target) {
        int [] result = new int[2];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            if(map.keySet().contains(target-nums[i]) && i!=map.get(target-nums[i])){
                int index = map.get(target - nums[i]);
                result[0] = i;
                result[1] = index;
                return result;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int [] nums= {2, 7, 11, 15};
        _001_twoSum twoSum = new _001_twoSum();
        int [] result = twoSum.twoSum_1(nums,13);
        System.out.println(result[0]);
        System.out.println(result[1]);
    }
}
