package com.gustavo.sort.heapSort;

import java.util.Random;

/**
 * CLR
 */
public class Utils {

    public static void maxHeapify(int[] array, int heapSize, int i) {
        int largest = i;
        int l = left(i);
        int r = right(i);
        if (l < heapSize && array[l] > array[i])
            largest = l;
        if (r < heapSize && array[r] > array[largest])
            largest = r;
        if (largest != i) {
            swap(array, i, largest);
            maxHeapify(array, heapSize, largest);
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

    public static void heapSort(int[] array) {
        buildMaxHeap(array);
        for (int i = array.length - 1; i > 0; i--) {
            swap(array, 0, i);
            maxHeapify(array, i, 0);
        }
    }

    public static void buildMaxHeap(int[] array) {
        int heapSize = array.length;
        for (int i = array.length / 2; i >= 0; i--) {
            maxHeapify(array, heapSize, i);
        }
    }

    public static int[] createArray(int num, int limit) {
        Random random = new Random();
        int[] array = new int[num];
        for (int i = 0; i < num; i++) {
            array[i] = random.nextInt(limit);
        }
        return array;
    }
}
