package com.gustavo.sort;

import java.util.Random;

/**
 * 快速排序, 解决stackoverflowerror
 */
public class QuickSort1 {
    public static void main(String[] args) {
//        int[] array = init(5, 100);
        int[] array = {5, 4, 3, 2, 1};
        printArray(array);
        quickSort(array, 0, array.length - 1);
    }

    public static void quickSort(int[] array, int left, int right) {

        boolean flag = true;
        int i = left;
        for (int j = right; i < j; ) {
            if (array[i] > array[j]) {
                swap(array, i, j);
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
        printArray(array);
        System.out.println("middle: " + array[i]);
        if (i > 1)
            quickSort(array, 0, i - 1);
        if (i < right - 1)
            quickSort(array, i + 1, right - 1);

    }

    public static void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public static int[] init(int num, int limit) {
        Random random = new Random();
        int[] array = new int[num];
        for (int i = 0; i < num; i++) {
            array[i] = random.nextInt(limit);
        }
        return array;
    }

    private static void printArray(int[] array) {
        int len = array.length;
        for (int i = 0; i < len; i++) {
            System.out.print(array[i] + "\t");
        }
        System.out.println();
    }
}
