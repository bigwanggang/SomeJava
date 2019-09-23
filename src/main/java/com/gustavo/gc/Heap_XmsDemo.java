package com.gustavo.gc;

import java.util.ArrayList;
import java.util.List;

/**对比：-Xmx11M -Xms11M -verbose:gc 和-Xmx11M -Xms4M -verbose:gc
 * 两个参数下， 执行gc的次数和gc的总执行时间
 * Created by gustaov on 2019/9/23.
 */
public class Heap_XmsDemo {
    public static void main(String[] args) {
        List l = new ArrayList();
        for (int i = 1; i < 10; i++) {
            byte[] b = new byte[1024 * 1024];
            l.add(b);
            if (l.size() == 3) {
                l.clear();
            }
        }
    }
}
