package com.gustavo.dataStructureAndAlgo.sort;

import com.gustavo.dataStructureAndAlgo.sort.heapSort.Utils;

import java.util.Random;

/**
 * 快速排序第一个版本，产生java.lang.StackOverflowError
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] array = init(5, 100);
        Utils.printArray(array);
        quickSort(array, 0, array.length - 1);
    }

    public static void quickSort(int[] array, int left, int right) {

        boolean flag = true;
        int i = left;
        for (int j = right; i < j; ) {
            if (array[i] > array[j]) {
                Utils.swap(array, i, j);
                if (flag) {
                    i++;
                } else {
                    j--;
                }
                flag = !flag;
            } else {
                if (flag)
                    j--;
                else
                    i++;
            }
        }
        Utils.printArray(array);
        System.out.println("middle: " + array[i]);
        if(i>0)
            quickSort(array, 0, i-1);
        if(i<array.length-1)
            quickSort(array, i+1, array.length-1);

    }

    public static int[] init(int num, int limit) {
        Random random = new Random();
        int[] array = new int[num];
        for (int i = 0; i < num; i++) {
            array[i] = random.nextInt(limit);
        }
        return array;
    }

}
