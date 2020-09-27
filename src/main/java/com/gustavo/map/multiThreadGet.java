package com.gustavo.map;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class multiThreadGet {
    static Map<Integer, Integer> map = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            map.put(i, i);
        }

        for (int i = 0; i < 10; i++) {
            executorService.execute(new Getter(i));
        }
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                for (int i = 10; i < 1000000; i++) {
                    map.put(i, i);
                }
            }
        });
        executorService.shutdown();
    }

    static class Getter implements Runnable {

        private int i;

        public Getter(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            for (int j = 0; j <1000000 ; j++) {
                Integer integer = map.get(i);
                if (integer == null) {

                    System.out.println("***********************" + i);
                }
            }
        }
    }

}
