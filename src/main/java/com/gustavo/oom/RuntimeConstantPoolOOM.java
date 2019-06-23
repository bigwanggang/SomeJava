package com.gustavo.oom;

import java.util.ArrayList;
import java.util.List;

/**VM Args: -XX:PermSize=10M -XX:MaxPermSize=10m
 * JDK1.6 通过-XX:PermSize=10M -XX:MaxPermSize=10m 参数就可以得到OOM异常
 * 并提示PermGen space, 但是JDK1.7J就不能得到相同的结果，while会一直循环下去
 * Created by gustaov on 2019/6/23.
 */
public class RuntimeConstantPoolOOM {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        int i = 0;
        while (true){
            list.add(String.valueOf(i++).intern());
        }
    }
}
