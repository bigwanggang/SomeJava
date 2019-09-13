package com.gustavo.gc;

import java.util.ArrayList;
import java.util.List;

/**
 * subString 容易产生内存溢出的问题
 * 增加-XX:+PrintGCDetails 查看详细GC打印信息
 * Created by gustaov on 2019/9/13.
 */
public class SubStringMemoryOverflowDemo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 1000; i++) {
//            HugeStr h = new HugeStr();
            ImprovedStr h = new ImprovedStr();
            list.add(h.subString(0, 5));
        }
        System.out.println(list.size());
    }

    static class HugeStr {
        private String s = new String(new char[1000000]);

        public String subString(int beging, int end) {
            return s.substring(beging, end);
        }
    }

    static class ImprovedStr {
        private String s = new String(new char[100000]);

        public String subString(int beging, int end) {
            return new String(s.substring(beging, end));
        }
    }


}
