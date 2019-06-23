package com.gustavo.failfast;

import java.util.ArrayList;
import java.util.List;

public class FailFastForEachRemoveDemo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            list.add(Thread.currentThread().getName() + "-" + i);
        }
        for (String s : list) {
            if ("main-5".equals(s))
                list.remove(s);
        }

        System.out.println(list);
    }
}
