package com.gustavo.distribute.oneProcessIdGeneratorSuccess;

/**
 * Created by gustaov on 2019/8/12.
 */
public class IdGenerator {
    private int i = 1;

    public synchronized int nextId() {
        return i++;

    }

}
