package com.gustavo.dataStructureAndAlgo;

/**
 * Created by gustaov on 2017/7/23.
 */
public class mergeTwoSortedArray {
    public static void merge2SortedArray(int[] a, int[] b, int[] c) {
        int i = 0, j = 0, k = 0;
        while (i < a.length && j < b.length) {
            if (a[i] < b[j]) {
                c[k++] = a[i++];
            } else
                c[k++] = b[j++];
        }

        while (i < a.length)
            c[k++] = a[i++];

        while (j < b.length)
            c[k++] = b[j++];

    }

    public static void main(String[] args) {
        int[] a = {1, 3, 6, 9};
        int[] b = {2, 4, 6, 8};
        int[] c = new int[a.length + b.length];
        merge2SortedArray(a, b, c);

        for(int i=0;i<c.length;i++) {
            System.out.print(c[i] + " ");
        }

    }
}
