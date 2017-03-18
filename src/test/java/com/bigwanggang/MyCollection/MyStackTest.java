package com.bigwanggang.MyCollection;

import junit.framework.Assert;
import org.junit.Test;

public class MyStackTest {
    @Test
    public void testMyArrayStack() {
        MyStack<Integer> stack = new MyArrayStack<Integer>();
        for (int i = 0; i < 20; i++) {
            stack.push(i);
        }
        Assert.assertEquals(19, (int) stack.top());
        Assert.assertEquals(19, (int) stack.top());
        Assert.assertEquals(20, stack.size());
        for (int i = 19; i >= 0; i--) {
            Assert.assertEquals(i, (int) stack.pop());
        }
    }

    @Test
    public void testMyLinkedStack() {
        MyStack<Integer> stack = new MyLinkedStack<Integer>();
        for (int i = 0; i < 20; i++) {
            stack.push(i);
        }
        Assert.assertEquals(19, (int) stack.top());
        Assert.assertEquals(19, (int) stack.top());
        Assert.assertEquals(20, stack.size());
        for (int i = 19; i >= 0; i--) {
            Assert.assertEquals(i, (int) stack.pop());
        }
    }

    @Test
    public void testPushAndPopSpeed() {
        int num = 3000000;
//        MyStack<String> stack = new MyArrayStack<String>();
        MyStack<String> stack = new MyLinkedStack<String>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < num; i++) {
            stack.push(i + ":");
        }
        long tmp = System.currentTimeMillis();
        while(!stack.isEmpty())
            stack.pop();
        long end = System.currentTimeMillis();
        System.out.println("push time: " + (tmp-start));
        System.out.println("pop time : " + (end - tmp));
    }
}
