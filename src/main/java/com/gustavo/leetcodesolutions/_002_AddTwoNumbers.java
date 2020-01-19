package com.gustavo.leetcodesolutions;

/**
 * Created by gustaov on 2018/9/2.
 */
public class _002_AddTwoNumbers {
    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        ListNode val = new ListNode(0);
        ListNode res = val;
        int toUp = 0;
        for (; l1 != null || l2 != null; ) {
            int cur = toUp;
            if (l1 != null) {
                cur += l1.val;
                l1=l1.next;
            }
            if (l2 != null) {
                cur += l2.val;
                l2=l2.next;
            }
            val.next = new ListNode(cur % 10);
            toUp = cur / 10;
            val = val.next;
        }
        if(toUp!=0){
            val.next=new ListNode(toUp);
        }
        return res.next;
    }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;
        ListNode head = new ListNode(0);
        int value = l1.val + l2.val;
        int toUp = value / 10;
        value = value % 10;
        ListNode v = new ListNode(value);
        head.next = v;
        l1 = l1.next;
        l2 = l2.next;
        while (l1 != null || l2 != null) {
            int v1 = toUp;
            if (l1 != null) {
                v1 += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                v1 += l2.val;
                l2 = l2.next;
            }
            toUp = v1 / 10;
            v1 = v1 % 10;
            ListNode ln = new ListNode(v1);
            v.next = ln;
            v = ln;

        }
        return head.next;
    }

    public static void main(String[] args) {

        _002_AddTwoNumbers addTwoNumbers = new _002_AddTwoNumbers();
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(4);
        ListNode node3 = new ListNode(3);
        node1.next = node2;
        node2.next = node3;

        ListNode node4 = new ListNode(5);
        ListNode node5 = new ListNode(6);
//        ListNode node6 = new ListNode(4);
        node4.next = node5;
//        node5.next = node6;

        ListNode result = addTwoNumbers.addTwoNumbers(node1, node4);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}
