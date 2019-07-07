package com.gustavo.myCollection;

public class Node<MyType> {
    private MyType object;

    private Node next;
    public Node(MyType o, Node next) {
        this.object = o;
        this.next = next;
    }

    public MyType getObject() {
        return object;
    }

    public void setObject(MyType object) {
        this.object = object;
    }

    public Node<MyType> getNext() {
        return next;
    }

    public void setNext(Node<MyType> next) {
        this.next = next;
    }
}
