package com.gustavo.collection;

import java.util.ArrayList;

/**
 * 多线程往ArrayList中add元素
 * Created by gustaov on 2019/5/11.
 */
public class MulThreadArrayListAdd {
    public static void main(String[] args) {
//        ArrayList<String> list = new ArrayList<>();
        ArrayList<String> list = new ArrayList<>(11);
        new Thread(new AddToArrayList(list)).start();
        new Thread(new AddToArrayList(list)).start();
    }

    static class AddToArrayList implements Runnable {
        private ArrayList<String> list;

        public AddToArrayList(ArrayList<String> list) {
            this.list = list;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                list.add(Thread.currentThread().getName() + "--" + i);
            }
        }
    }
}
