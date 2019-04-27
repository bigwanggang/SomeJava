package com.gustavo.map;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class MultiThreadMap_Put {
    static Map<String, String> map = new HashMap<String, String>();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            new Thread(new Putting(), "thread###" + i).start();
        }

        TimeUnit.SECONDS.sleep(5);
        System.out.println(map.size());
    }

    static class Putting implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 100000; i++) {
                map.put(Thread.currentThread().getName() + "###" + i, i+"");
            }
        }
    }
}
