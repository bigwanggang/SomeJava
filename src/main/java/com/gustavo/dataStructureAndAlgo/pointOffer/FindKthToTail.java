package com.gustavo.dataStructureAndAlgo.pointOffer;


public class FindKthToTail {
    public static ListNode FindKthToTail(ListNode head, int k) {
        ListNode r = head;
        while (k-- >= 1) {
            if(r!=null)
            r = r.next;
            else return null;
        }
        ListNode l = head;
        while (r != null) {
            l = l.next;
            r = r.next;
        }
        return l;
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        n1.next=n2;
        n2.next=n3;
        ListNode n = FindKthToTail(n1, 3);
        System.out.println(n.val);
    }

}
