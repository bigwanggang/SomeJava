package com.gustavo.dataStructureAndAlgo.sort.heapSort;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UtilsTest {

    @Test
    public void left() {
        assertEquals(5, Utils.left(2));
        assertEquals(1, Utils.left(0));
        assertEquals(3, Utils.left(1));
    }

    @Test
    public void right() {
        assertEquals(6, Utils.right(2));
        assertEquals(2, Utils.right(0));
        assertEquals(4, Utils.right(1));
    }

    @Test
    public void testParent() {
        assertEquals(0, Utils.parent(1));
        assertEquals(0, Utils.parent(2));
        assertEquals(1, Utils.parent(3));
        assertEquals(1, Utils.parent(4));
        assertEquals(2, Utils.parent(5));
        assertEquals(2, Utils.parent(6));
    }

    @Test
    public void testMaxHeapify() {
        int[] array = {16, 4, 10, 14, 7, 9, 3, 2, 8, 1};
        Utils.printArray(array);
        Utils.maxHeapify(array, array.length, 1);
        Utils.printArray(array);
    }

    @Test
    public void testBuildMaxHeap() {
        int[] array = {4, 1, 3, 2, 16, 9, 10, 14, 8, 7};
        Utils.buildMaxHeap(array);
        Utils.printArray(array);
    }

    @Test
    public void testBuildMaxHeap1() {
        int[] array = Utils.createArray(12, 100);
        Utils.printArray(array);
        Utils.buildMaxHeap(array);
        Utils.printArray(array);
    }

    @Test
    public void testHeapSort() {
        int[] array = Utils.createArray(12, 100);
        Utils.printArray(array);
        Utils.heapSort(array);
        Utils.printArray(array);
    }
}
