package com.gustavo.gc;

/**
 * 《Java程序性能优化》P244
 * vm:-XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15 -Xms40M -Xmx40M -Xmn20M
 *
 * Created by gustaov on 2019/9/22.
 */
public class TestHeapGc {
    public static void main(String[] args) {
        byte[] b1 = new byte[1024 * 1024 * 2];
        byte[] b2 = new byte[1024 * 1024 * 8];
        b2 = null;
        b2 = new byte[1024 * 1024 * 8];

    }
}
