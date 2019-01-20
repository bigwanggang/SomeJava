package com.gustavo.sort.heapSort;

/**
 * CLR
 */
public class Utils {
    public static void maxHeapify(int[] array, int i) {
        int largest = i;
        int len = array.length;
        int l = left(i);
        int r = right(i);
        if (l < len && array[l] > array[i])
            largest = l;
        if (r < len && array[r] > array[largest])
            largest = r;
        if (largest != i) {
            swap(array, i, largest);
            maxHeapify(array, largest);
        }
    }

    public static int left(int i) {
        return (2 * (i + 1) - 1);
    }

    public static int right(int i) {
        return 2 * (i + 1);
    }

    public static int parent(int i) {
        return (i + 1) / 2 - 1;
    }


    public static void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public static void printArray(int[] array) {
        int len = array.length;
        for (int i = 0; i < len; i++) {
            System.out.print(array[i] + "\t");
        }
        System.out.println();
    }
}
