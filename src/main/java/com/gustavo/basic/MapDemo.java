package com.gustavo.basic;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by gustaov on 2019/1/13.
 */
public class MapDemo {
    public static void main(String[] args) {
        // key and value of HashMap can be set null
        Map<String, String> map = new HashMap<>();
        map.put(null, "hello");
        System.out.println(map.get(null));
        map.put("hello", null);
        System.out.println(map.get("hello"));

        // treemap key cannot be null, value can
        Map<String, String> treemap = new TreeMap<>();
//        treemap.put(null, "hello");
//        System.out.println(treemap.get(null));
        treemap.put("hello", null);
        System.out.println(treemap.get("hello"));
    }
}
