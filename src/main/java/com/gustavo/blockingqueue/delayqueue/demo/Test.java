package com.gustavo.blockingqueue.delayqueue.demo;

import java.util.concurrent.TimeUnit;

/**
 * Created by gustaov on 2019/4/18.
 */
public class Test {
    public static void main(String[] args) {

        System.out.println(TimeUnit.MICROSECONDS.convert(1, TimeUnit.MILLISECONDS));
        System.out.println(TimeUnit.MICROSECONDS.convert(1, TimeUnit.SECONDS));
        System.out.println(TimeUnit.NANOSECONDS.convert(1, TimeUnit.MICROSECONDS));
        System.out.println(TimeUnit.NANOSECONDS.convert(1, TimeUnit.MILLISECONDS));
//        System.currentTimeMillis()
    }
}
