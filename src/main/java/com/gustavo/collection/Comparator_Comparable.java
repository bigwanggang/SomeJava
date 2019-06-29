package com.gustavo.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Comparable和Comparator 的对比， 可见Comparator的作用是，可以自定义比较器，例如本栗子中，Student类自定义的排序规则是通过name
 * 但是如果有人想根据其他规则排序，例如通过score排序，就要自定义Comparator了
 */
public class Comparator_Comparable {
    public static void main(String[] args) {
        List<Student> list = new ArrayList<Student>();
        list.add(new Student("abby", "00000001", 77));
        list.add(new Student("xiaoliang", "00000002", 55));
        list.add(new Student("james", "00000003", 66));
        System.out.println("original list: " + list);
        Collections.sort(list);
        System.out.println("Comparable sort: " + list);


        Collections.sort(list, new ScoreComparator());
        System.out.println("comparator sort: " + list);
    }

    static class ScoreComparator implements Comparator<Student> {

        @Override
        public int compare(Student o1, Student o2) {

            return o1.getScore() - o2.getScore();
        }
    }
}
