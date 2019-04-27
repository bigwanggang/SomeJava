package com.gustavo.basic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gustaov on 2019/2/17.
 */
public class SubListFailFast {
    public static void main(String[] args) {
        List<String> mastetList = new ArrayList<String>();
        mastetList.add("one");
        mastetList.add("tow");
        mastetList.add("three");
        mastetList.add("four");
        mastetList.add("five");

        List<String> subList = mastetList.subList(0, 3);

//        mastetList.remove(0);
//        mastetList.add("ten");
//        mastetList.clear();

        subList.clear();
        subList.add("six");
        subList.add("seven");
        subList.remove(0);

        for (String s : subList) {
            System.out.println(s);
        }
        System.out.println(mastetList);
    }
}
