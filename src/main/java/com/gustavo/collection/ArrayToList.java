package com.gustavo.collection;

import java.util.Arrays;
import java.util.List;

/**
 * Created by gustaov on 2019/1/12.
 */
public class ArrayToList {
    public static void main(String[] args) {
        String[] stringArray = new String[3];
        stringArray[0] = "one";
        stringArray[1] = "two";
        stringArray[2] = "three";
        List<String> stringList = Arrays.asList(stringArray);
        stringList.set(0, "oneList");
        System.out.println(stringArray[0]);
        stringList.add("four");
        stringList.remove(2);
        stringList.clear();
    }
}
