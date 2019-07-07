package com.gustavo.myCollection;

public class MyArrayStack<Type> implements MyStack<Type> {
    int size = 0;
    private Object[] array = new Object[10];

    public int size() {
        return size;
    }

    public boolean push(Type t) {
        if (size == array.length) {
            Object[] newArray = new Object[array.length * 2];
            for (int i = 0; i < array.length; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
        }
        array[size++] = t;
        return true;
    }

    public Type top() {
        if (size == 0)
            return null;
        return (Type) array[size - 1];
    }

    public Type pop() {
        if (size == 0)
            return null;
        Type t = (Type) array[size - 1];
        array[size - 1] = null;
        size--;
        return t;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
