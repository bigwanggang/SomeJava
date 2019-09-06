package com.gustavo.collection;

import java.util.Arrays;
import java.util.List;

/**
 * Arrays.asList 是把数组转成List
 * 但是该list是Arrays的内部ArrayList，并不是常用的那个ArrayList
 * 该ArrayList没有实现add和remove方法，因此不能执行add和remove
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
        System.out.println(stringArray);
        stringList.add("four");
        stringList.remove(2);
        stringList.clear();
    }
}
