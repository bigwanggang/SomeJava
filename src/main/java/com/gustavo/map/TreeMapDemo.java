package com.gustavo.map;

import java.util.TreeMap;

/**
 * Created by gustaov on 2019/1/13.
 */
public class TreeMapDemo {
    public static void main(String[] args) {
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(55,"fifty-five");
        treeMap.put(56,"fifty-six");
        treeMap.put(57,"fifty-seven");
        treeMap.put(58,"fifty-eight");
        treeMap.put(83,"eighty-three");
        treeMap.remove(57);
        treeMap.put(59,"fifty-nine");

    }
}
