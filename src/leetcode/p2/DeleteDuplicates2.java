package leetcode.p2;

import leetcode.common.ListNode;

/**
 * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。 示例 1:
 * 
 * 输入: 1->1->2 输出: 1->2 示例 2:
 * 
 * 输入: 1->1->2->3->3 输出: 1->2->3
 * 
 * @author ilongli
 * @email 351365415@qq.com
 */
public class DeleteDuplicates2 {

	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		head.next = new ListNode(1);
		head.next.next = new ListNode(2);
		head.next.next.next = new ListNode(3);
		head.next.next.next.next = new ListNode(3);
//		head.next.next.next.next.next = new ListNode(9);
		ListNode _head = deleteDuplicates(head);
		while (_head != null) {
			System.out.print(_head.val + "->");
			_head = _head.next;
		}
	}

	// 1->1->1->2->3->3
	public static ListNode deleteDuplicates(ListNode head) {
		ListNode preNode = head, curNode = head;
		while (curNode != null) {
			if (curNode.next == null || preNode.val != curNode.next.val) {
				preNode.next = curNode.next;
				preNode = curNode.next;
			}
			System.out.println(curNode.next == null);
			curNode = curNode.next;
		}
		return head;
	}
}
