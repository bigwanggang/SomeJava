package com.gustavo.dataStructureAndAlgo.pointOffer;

/**
 * Created by gustaov on 2019/5/19.
 */
public class JumpFloorII {
    public static int JumpFloorII(int target) {
        if (target == 1 || target == 2) {
            return target;
        }
        int i = 2;
        int result = 1;
        while (i++ <= target) {
            result = result * 2;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(JumpFloorII(4));
    }
}
