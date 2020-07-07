package leetcode.p3.q141_150;

import leetcode.common.ListNode;

/**
 * 143.重排链表
 * 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
 *	将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
 *	
 *	你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *	
 *	示例 1:
 *	
 *	给定链表 1->2->3->4, 重新排列为 1->4->2->3.
 *	示例 2:
 *	
 *	给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
 * 
 * @author ilongli
 * @email 351365415@qq.com
 */
public class ReorderList {

	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		ListNode h2 = new ListNode(2);
		ListNode h3 = new ListNode(3);
		ListNode h4 = new ListNode(4);
		head.next = h2;
//		h2.next = h3;
//		h3.next = h4;
//		h4.next = new ListNode(5);
		
		new ReorderList().reorderList(head);
		
		while (head != null) {
			System.out.print(head.val + "->");
			head = head.next;
		}
	}

	
	/**
	 * 递归
	 */
	ListNode cur;
	
    public void reorderList(ListNode head) {
    	if (head == null) return;
    	this.cur = head;
    	helper(head);
    }
    
    private void helper(ListNode node) {
    	if (node.next != null) helper(node.next);
    	if (this.cur.next != null && this.cur != node) {
    		if (this.cur.next != node) {
    			node.next = this.cur.next;
    			this.cur.next = node;
    			this.cur = node.next;
    		} else {
    			this.cur = node;
    			this.cur.next = null;
    		}
    	} else {
    		this.cur.next = null;
    	}
    }
    
}
