package com.gustavo.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by gustaov on 2019/1/12.
 */
public class ListToArray {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("one");
        list.add("two");
        list.add("three");

        String[] arrya1 = new String[2];
        list.toArray(arrya1);
        System.out.println(Arrays.asList(arrya1));

        String[] array2 = new String[3];
        list.toArray(array2);
        System.out.println(Arrays.asList(array2));

    }
}
