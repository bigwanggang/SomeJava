package com.gustavo.sort;

public class QuickSort_CLR {
    public static void main(String[] args) {
        int[] array = {5, 4, 3, 2, 1};
        quicksort(array, 0, array.length - 1);
    }

    public static void quicksort(int[] array, int left, int right) {
        if (left < right) {
            int q = partition(array, left, right);
            quicksort(array, left, q - 1);
            quicksort(array, q + 1, right);
        }
    }

    public static int partition(int[] array, int left, int right) {
        int tmp = array[right];
        int i = left - 1;
        for (int j = left; j < right ; j++) {
            if (array[j] <= tmp) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, right);
        printArray(array);
        return i + 1;
    }

    public static void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    private static void printArray(int[] array) {
        int len = array.length;
        for (int i = 0; i < len; i++) {
            System.out.print(array[i] + "\t");
        }
        System.out.println();
    }
}
