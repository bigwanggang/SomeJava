package com.gustavo.dataStructureAndAlgo.pointOffer;

/**
 * Created by gustaov on 2019/5/19.
 */
public class JumpFloor {
    public static int jumpFloor(int target) {
        if (target == 0) {
            return 0;
        }
        if (target ==1)
            return 1;
        int a = 1;
        int b = 1;
        int i = 2;
        while (i <= target) {
            int tmp = a + b;
            a = b;
            b = tmp;
            i++;
        }
        return b;
    }

    public static void main(String[] args) {
        System.out.println(jumpFloor(4));
    }
}
