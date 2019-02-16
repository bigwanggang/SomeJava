package com.gustavo.basic;

import java.util.ArrayList;
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

        for (String s : list) {
            if(s.equals("50")){
                list.remove(s);
            }
        }
        System.out.println(list.size());
    }
}
