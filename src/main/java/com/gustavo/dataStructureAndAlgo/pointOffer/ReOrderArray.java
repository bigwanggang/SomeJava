package com.gustavo.dataStructureAndAlgo.pointOffer;

import java.util.Arrays;

/**
 * Created by gustaov on 2019/5/19.
 */
public class ReOrderArray {
    public static void reOrderArray(int[] array) {
        int i = 0,j=0;
        while (i < array.length && j<array.length) {
            if ((array[i] & 1) == 0) {
                j = i + 1;
                while (j < array.length) {
                    if ((array[j] & 1) == 1) {
                        int tmp = array[j];
                        System.arraycopy(array, i, array, i + 1, j - i);
                        array[i] = tmp;
                        i=i+1;
                        break;
                    } else {
                        j++;
                    }
                }
            } else {
                i++;
            }
        }
    }

    public static void main(String[] args) {
        int[] array={1,3,5,7,2,4,6,8};
        reOrderArray(array);
        System.out.println(Arrays.toString(array));
    }
}
