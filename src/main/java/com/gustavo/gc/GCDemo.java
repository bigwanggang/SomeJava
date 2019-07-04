package com.gustavo.gc;

/**
 * VM options: -XX:+PrintGCDetails
 * 可以通过该栗子修改申请内存的大小，来了解gc相关知识
 */
public class GCDemo {
    public static void main(String[] args) {
        byte[] allocation1, allocation2;
        allocation1 = new byte[40900*1024];
//        allocation2 = new byte[9000*1024];
    }
}
