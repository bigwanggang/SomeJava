package com.gustavo.basic;

import java.util.Iterator;

/**
 * Created by gustaov on 2019/2/18.
 */
public class StringIter implements Iterable<String> {
    private String str;
    private String[] strs;

    public StringIter(String str) {
        this.str = str;
        this.strs = str.split(" ");
    }


    @Override
    public Iterator<String> iterator() {
        return new Iterator<String>() {
            private int cursor;

            @Override
            public boolean hasNext() {
                return cursor != strs.length;
            }

            @Override
            public String next() {
                return strs[cursor++];
            }

            @Override
            public void remove() {

            }
        };
    }

    public static void main(String[] args) {
        StringIter s = new StringIter("Abd this is how we know the Earth to be banna-shaped");
        for (String str : s) {
            System.out.println(str);
        }
    }
}
