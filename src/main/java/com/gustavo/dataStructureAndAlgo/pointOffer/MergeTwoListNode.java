package com.gustavo.dataStructureAndAlgo.pointOffer;

public class MergeTwoListNode {
    public static ListNode Merge(ListNode list1, ListNode list2) {
        if (list1 == null)
            return list2;
        if (list2 == null)
            return list1;
        if (list1.val <= list2.val) {
            return merge(list1, list2);
        } else {
            return merge(list2, list1);
        }
    }

    public static ListNode merge(ListNode list1, ListNode list2) {
        ListNode left = list1, right = list1.next, j = list2;
        while (right != null && j != null) {
            if (right.val > j.val) {
                left.next = j;
                ListNode tmp = j.next;
                j.next = right;
                left = j;
                j = tmp;
            } else {
                left = right;
                right = right.next;
            }
        }
        if (right == null) {
            left.next = j;
        }
        return list1;
    }


    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n3 = new ListNode(3);
        ListNode n5 = new ListNode(4);
        n1.next = n3;
        n3.next = n5;

        ListNode n2 = new ListNode(2);
        ListNode n4 = new ListNode(5);
        ListNode n6 = new ListNode(6);
        n2.next = n4;
        n4.next = n6;

        ListNode l = Merge(n1, n2);
        while (l != null) {
            System.out.println(l.val);
            l = l.next;
        }
    }
}
