package com.gustavo.collection;

import java.util.ArrayList;
import java.util.List;

/**
 *在subList场景中，高度注意对原集合元素个数的修改，
 * 会导致子列表的遍历、增加、删除均会产生ConcurrentModificationException 异常。
 */
public class SubListFastFailDemo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            list.add("info#"+i);
        }

        List<String> sublist = list.subList(0, 4);
        list.add("hello");
        System.out.println(sublist);
    }
}
