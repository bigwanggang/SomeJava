package com.gustavo.dataStructureAndAlgo;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 剑指offer 第5题
 * Created by gustaov on 2019/5/5.
 */
public class PrintListFromEndToHead {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void print(ListNode listNode) {
        if (listNode.next != null) {
            print(listNode.next);
        }
        System.out.println(listNode.val);
    }

    public static void print1(ListNode listNode) {
        Stack<Integer> stack = new Stack<>();

        ListNode point = listNode;
        while (point != null) {
            stack.push(point.val);
            point = point.next;
        }
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(5);
        ListNode l2 = new ListNode(4);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(2);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;

        print1(l1);

    }
}
