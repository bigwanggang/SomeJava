package com.gustavo.jvm;

/**
 * -XX:+PrintGCDetails
 * test1和test2方法执行的gc打印信息输出分别为：
 * [Full GC (System.gc()) [Tenured: 6144K->6882K(10944K), 0.0025795 secs] 7704K->6882K(15872K), [Metaspace: 112K->112K(4480K)], 0.0026517 secs] [Times: user=0.02 sys=0.00, real=0.00 secs]

 [Full GC (System.gc()) [Tenured: 6144K->745K(10944K), 0.0024043 secs] 7704K->745K(15872K), [Metaspace: 114K->114K(4480K)], 0.0024790 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
 * Created by gustaov on 2019/9/22.
 */
public class LocalVariableGcDemo {
    public static void main(String[] args) {
        test2();
    }

    public static void test1() {
        {
            byte[] b = new byte[6 * 1024 * 1024];
        }
        System.gc();
        System.out.println("gc over");
    }

    public static void test2() {
        {
            byte[] b = new byte[6 * 1024 * 1024];
            b = null;
        }
        System.gc();
        System.out.println("gc over");
    }
}
