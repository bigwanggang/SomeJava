package com.gustavo.myCollection;

public interface MyStack<T> {
    int size();
    boolean push(T t);
    T top();
    T pop();
    boolean isEmpty();
}
