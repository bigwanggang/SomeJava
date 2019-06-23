package com.gustavo.oom;

import java.util.ArrayList;
import java.util.List;

/**VM args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * Created by gustaov on 2019/6/23.
 */
public class HeapOOM {
    static class OOMObject{}
    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<OOMObject>();
        while (true){
            list.add(new OOMObject());
        }
    }
}
