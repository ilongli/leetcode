package leetcode.p2;

import leetcode.common.ListNode;

/**
 * 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
 *	示例 1:
 *	
 *	输入: 1->2->3->3->4->4->5
 *	输出: 1->2->5
 *	示例 2:
 *	
 *	输入: 1->1->1->2->3
 *	输出: 2->3
 * @author ilongli
 * @email 351365415@qq.com
 */
public class DeleteDuplicates {

	public static void main(String[] args) {
		ListNode head = new ListNode(1);
//		head.next = new ListNode(1);
//		head.next.next = new ListNode(1);
//		head.next.next.next = new ListNode(2);
//		head.next.next.next.next = new ListNode(2);
//		head.next.next.next.next.next = new ListNode(3);
//		head.next.next.next.next.next.next = new ListNode(4);
//		head.next.next.next.next.next.next.next = new ListNode(6);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(3);
		head.next.next.next.next = new ListNode(4);
		head.next.next.next.next.next = new ListNode(4);
		head.next.next.next.next.next.next = new ListNode(5);
		head.next.next.next.next.next.next.next = new ListNode(6);
		ListNode _head = deleteDuplicates(head);
		while (_head != null) {
			System.out.print(_head.val + "->");
			_head = _head.next;
		}
	}
	// 1->1->1->2->2->3->4->6
    public static ListNode deleteDuplicates2(ListNode head) {
        if (head == null) return head;
        ListNode dummy = new ListNode(-1000);
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = dummy.next;
        while (fast != null) {
            while (fast.next != null && fast.val == fast.next.val) fast = fast.next;
            if (slow.next == fast) slow = slow.next;
            else slow.next = fast.next;
            fast = fast.next;
        }
        return dummy.next; 
    }
	
	//1->2->3->3->4->4->5->6
    public static ListNode deleteDuplicates(ListNode head) {
    	if (head == null) return head;
        ListNode res = new ListNode(-1);
        res.next = head;
        ListNode preNode = res, curNode = head;
        int count = 1;
        while (curNode.next != null) {
        	if (curNode.val != curNode.next.val) {
        		if (count != 1) {
        			// 截断
        			preNode.next = curNode.next;
        		} else {
        			preNode = curNode;
        		} 
        		count = 1;
        	} else {
        		count++;
        	}
        	curNode = curNode.next;
        }
    	return res.next;
    }
}
