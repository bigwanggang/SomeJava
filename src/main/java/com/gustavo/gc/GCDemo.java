package com.gustavo.gc;

/**
 * VM options: -XX:+PrintGCDetails
 */
public class GCDemo {
    public static void main(String[] args) {
        byte[] allocation1, allocation2;
        allocation1 = new byte[40900*1024];
//        allocation2 = new byte[9000*1024];
    }
}
