package leetcode.p1;

import leetcode.common.ListNode;

/**
 * 给出一个链表，每 k 个节点一组进行翻转，并返回翻转后的链表。
 * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么将最后剩余节点保持原有顺序。
 * 
 *	 示例 :
 *	 给定这个链表：1->2->3->4->5
 *	 当 k = 2 时，应当返回: 2->1->4->3->5
 *	 当 k = 3 时，应当返回: 3->2->1->4->5
 *	 
 *	 说明 :
 *	 你的算法只能使用常数的额外空间。
 *	 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 */
public class ReverseKGroup {

	public static void main(String[] args) {
		ListNode node = new ListNode(1);
		node.next = new ListNode(2);
		node.next.next = new ListNode(3);
		node.next.next.next = new ListNode(4);
		node.next.next.next.next = new ListNode(5);
//		node.next.next.next.next.next = new ListNode(6);
		ListNode swap = reverseKGroup(node, 3);
		while (swap != null) {
			System.out.print(swap.val + "->");	
			swap = swap.next;
		}
	}
	
    public static ListNode reverseKGroup(ListNode head, int k) {
    	if (head == null || head.next == null || k == 1) return head;
    	
    	ListNode result = new ListNode(0), tail = result;
    	ListNode[] swaps = new ListNode[k];
    	int index = 0;
    	
    	while (head != null) {
    		swaps[index++] = head;
    		head = head.next;
    		if (index == k) {
    			index = 0;
    			for (int i = (k - 1); i >= 0; i--) {
        			tail.next = new ListNode(swaps[i].val);
        			tail = tail.next;
    			}
    		}
    	}
    	
    	if (index != 0 && index < k) {
    		tail.next = swaps[0];
    	}
    	
    	return result.next;
    }
    
    public static ListNode reverseKGroup2(ListNode head, int k) {
        ListNode prev = null;
        ListNode cur = head;
        ListNode next = null;
        ListNode check = head;
        int canProceed = 0;
        int count = 0;
        // 检查链表长度是否满足翻转
        while (canProceed < k && check != null) {
            check = check.next;
            canProceed++;
        }
        // 满足条件，进行翻转
        if (canProceed == k) {
            while (count < k && cur != null) {
                next = cur.next;
                cur.next = prev;
                prev = cur;
                cur = next;
                count++;
            }
            if (next != null) {
                // head 为链表翻转后的尾节点
                head.next = reverseKGroup(next, k);
            }
            // prev 为链表翻转后的头结点
            return prev;
        } else {
            // 不满住翻转条件，直接返回 head 即可
            return head;
        }
    }
}
