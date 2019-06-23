package com.gustavo.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 */
public class ConcurrentHashMapDemo {

    private static ConcurrentMap<String, List<String>> map = new ConcurrentHashMap<String, List<String>>();
    private static Object lock = new Object();
    private static int num =10;
    public static void main(String[] args) {
        Thread[] threads = new Thread[num];
        for (int i = 0; i < num; i++) {
            threads[i] = new Thread(new AddRunning());
        }
        for (int i = 0; i < num; i++) {
            threads[i].start();
        }
        for (int i = 0; i < num; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int i = 0;
        for (String s : map.keySet()) {
            i += map.get(s).size();
        }
        System.out.println(i);

    }

    static class AddRunning implements Runnable {

        @Override
        public void run() {
            Random r = new Random();
            for (int i = 0; i < 5000; i++) {
                String s = String.valueOf(r.nextInt(100));
//                synchronized (lock) {
                if (map.containsKey(s)) {
                    List<String> l = map.get(s);
                    l.add(String.valueOf(r.nextInt(10000)));
                } else {
                    List<String> l = new ArrayList<String>();
                    l.add(String.valueOf(r.nextInt(10000)));
                    map.put(s, l);
                }
//                }
            }
        }
    }
}
