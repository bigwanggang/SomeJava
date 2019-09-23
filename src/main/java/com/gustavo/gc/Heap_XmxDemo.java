package com.gustavo.gc;

import java.util.ArrayList;
import java.util.List;

/**
 * 如果 -Xmx4M 就出现.OutOfMemoryError: Java heap space
 * 如果 -Xmx11M 程序执行成功， 正常打印
 * Created by gustaov on 2019/9/23.
 */
public class Heap_XmxDemo {
    public static void main(String[] args) {
        List l = new ArrayList();
        for (int i = 1; i < 10; i++) {
            byte[] b = new byte[1024 * 1024];
            l.add(b);
            System.out.println(i + "M is allocated");
        }
        System.out.println("Max memory: " + Runtime.getRuntime().maxMemory() / 1024 / 1024 + "M");
    }
}
