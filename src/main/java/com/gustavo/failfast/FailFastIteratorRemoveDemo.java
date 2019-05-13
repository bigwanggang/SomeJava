package com.gustavo.failfast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FailFastIteratorRemoveDemo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(Thread.currentThread().getName() + "-" + i);
        }
        Iterator<String> iter = list.iterator();
        while (iter.hasNext()) {
            String s = iter.next();
            if ("main-5".equals(s))
                iter.remove();
        }
        System.out.println(list);

    }
}
