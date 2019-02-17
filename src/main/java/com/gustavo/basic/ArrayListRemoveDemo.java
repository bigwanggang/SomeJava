package com.gustavo.basic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by gustaov on 2019/2/16.
 */
public class ArrayListRemoveDemo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add("" + i);
        }

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        for (String s : list) {
            System.out.println(s);
        }

        for (Iterator<String> iter = list.iterator(); iter.hasNext(); ) {
            System.out.println(iter.next());
        }
        System.out.println(list.size());
    }
}
