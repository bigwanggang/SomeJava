package com.gustavo.dataStructureAndAlgo.pointOffer;

public class ReverseList {
    public static ListNode ReverseList(ListNode head) {
        if(head==null)
            return null;
        ListNode l = head;
        ListNode r = l.next;
        l.next=null;
        while (r!=null){
            ListNode tmp = r.next;
            r.next = l;
            l = r;
            r = tmp;
        }
        return l;
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        n1.next=n2;
        n2.next=n3;
        ListNode l = ReverseList(n1);
        while (l!=null){
            System.out.println(l.val);
            l = l.next;
        }
    }
}
