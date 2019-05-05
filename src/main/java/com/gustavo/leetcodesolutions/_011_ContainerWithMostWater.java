package com.gustavo.leetcodesolutions;

/**
 * Created by gustaov on 2019/5/5.
 */
public class _011_ContainerWithMostWater {
    public static int maxArea(int[] height) {
        int max = 0;
        for (int i = 0, j = height.length - 1; i < j; ) {
            max = Math.max(max, (j-i) * Math.min(height[i], height[j]));
            if(height[i]<height[j]){
                i++;
            }else{
                j--;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = {1,8,6,2,5,4,8,3,7};
        System.out.println(maxArea(arr));
    }
}
