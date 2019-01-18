package com.gustavo.sort.heapSort;

import com.gustavo.sort.QuickSort;

/**
 * CLR
 */
public class Utils {
    public static void maxHeapify(int[] array, int i) {
        int largest = i;
        int l = left(i);
        int r = right(i);
        if (array[l] > array[i])
            largest = l;
        if (array[r] > largest)
            largest = r;
        if (largest != i) {
            QuickSort.swap(array, i, largest);
        }
        maxHeapify(array, largest);
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


}
