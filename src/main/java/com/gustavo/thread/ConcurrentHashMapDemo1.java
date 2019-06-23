package com.gustavo.thread;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ConcurrentHashMap 多线程put测试,key值为String， value为String出现的次数，
 */
public class ConcurrentHashMapDemo1 {
    private static ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<String, Integer>();
    private static Object lock = new Object();

    public static void main(String[] args) {
        int num = 100;
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
            i += map.get(s);
        }
        System.out.println(i);


    }

    static class AddRunning implements Runnable {

        @Override
        public void run() {
            Random r = new Random();
            for (int i = 0; i < 1000; i++) {
                String v = String.valueOf(r.nextInt(100));
                synchronized (lock) {
                    if (map.containsKey(v)) {
                        int t = map.get(v);
                        map.put(v, t + 1);
                    } else {
                        map.put(v, 1);
                    }
                }
            }
        }
    }
}
