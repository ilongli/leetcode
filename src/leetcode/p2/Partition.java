package leetcode.p2;

import leetcode.common.ListNode;

/**
 * 给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。
 *	你应当保留两个分区中每个节点的初始相对位置。
 *	
 *	示例:
 *	
 *	输入: head = 1->4->3->2->5->2, x = 3
 *	输出: 1->2->2->4->3->5
 * @author ilongli
 * @email 351365415@qq.com
 */
public class Partition {

	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		head.next = new ListNode(4);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(2);
		head.next.next.next.next = new ListNode(5);
		head.next.next.next.next.next = new ListNode(2);
		ListNode _head = partition(head, 3);
		while (_head != null) {
			System.out.print(_head.val + "->");
			_head = _head.next;
		}
	}
	
	// 输入: head = 1->4->3->2->5->2, x = 3
	// 输出: 1->2->2->4->3->5
    public static ListNode partition(ListNode head, int x) {
    	ListNode ltNode = new ListNode(-1), gtNode = new ListNode(-1), 
    			ltNode_head = ltNode, gtNode_head = gtNode;
    	
    	while (head != null) {
    		int val = head.val;
    		if (val < x) {
    			ltNode.next = head;
    			ltNode = ltNode.next;
    		} else {
    			gtNode.next = head;
    			gtNode = gtNode.next;
    		}
    		head = head.next;
    	}
    	
    	ltNode.next = gtNode_head.next;
    	gtNode.next = null;
    	
    	return ltNode_head.next;
    }
}
