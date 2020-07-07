package leetcode.p1;

import leetcode.common.ListNode;

public class MergeTwoLists {
	
	public static void main(String[] args) {
		
	}
	
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    	ListNode head = new ListNode(0);
    	ListNode temp = head;

    	while (l1 != null && l2 != null) {
    		if (l1.val > l2.val) {
    			temp.next = l2;
    			l2 = l2.next;
    		} else {
    			temp.next = l1;
    			l1 = l1.next;
    		}
    		temp = temp.next;
    	}
    	
    	if (l1 == null) temp.next = l2;
    	if (l2 == null) temp.next = l1;
    	
        return head.next;
    }
}
