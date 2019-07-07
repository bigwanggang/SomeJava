package com.gustavo.myCollection;

public class MyLinkedStack<Type> implements MyStack<Type> {
    Node top = null;
    int size = 0;

    public boolean push(Type t) {
        Node n = new Node(t, top);
        top = n;
        size++;
        return true;
    }

    public Type pop() {
        if (size == 0)
            return null;
        Type t = (Type) top.getObject();
        top = top.getNext();
        size--;
        return t;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Type top() {
        if (size == 0)
            return null;
        Type t = (Type) top.getObject();
        return t;
    }

    public int size() {
        return size;
    }
}
