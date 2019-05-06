package com.gustavo.dataStructureAndAlgo;

import java.util.Stack;

/**
 * 剑指offer 用两个栈实现队列
 */
public class QueueWith2Stack<E> {
    private Stack<E> in = new Stack<E>();
    private Stack<E> out = new Stack<E>();

    public void put(E e) {
        in.push(e);
    }

    public E pop() {
        if (!out.empty()) {
            return out.pop();
        } else if (!in.empty()) {
            while (!in.empty()) {
                out.push(in.pop());
            }
            return out.pop();
        } else
            throw new IndexOutOfBoundsException();
    }
    public boolean isEmpty(){
        return in.isEmpty() && out.isEmpty();
    }

}
