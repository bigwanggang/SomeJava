package com.gustavo.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * subList获取的list是原list的一个视图，sublist的修改会体现到原list上
 */
public class ArrayListSubListDemo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            list.add("info#"+i);
        }

        List<String> sublist = list.subList(0, 4);
        sublist.remove(0);
        sublist.add("hello");
        sublist.add("world");
        System.out.println(list);
    }
}
