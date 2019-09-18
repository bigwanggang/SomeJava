package com.gustavo.dataStructureAndAlgo.sort;

import java.util.Random;

public class BubbleSort {
    public static final int num = 5;
    public static int[] array = {5,4,3,2,1};

    public static void main(String[] args) {
//        initArray();
        printArray();
        for (int i = 0; i < num -1; i++) {

            for (int j = 0; j < num-  i - 1; j++) {
                if (array[j + 1] < array[j]) {
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }
                printArray();
            }
            System.out.println("--");
        }
    }

    private static void printArray() {
        for (int i = 0; i < num; i++) {
            System.out.print(array[i] + "\t");
        }
        System.out.println();
    }

    public static void initArray() {
        Random random = new Random();
        for (int i = 0; i < num; i++) {
            array[i] = random.nextInt(100);
        }
    }

}
