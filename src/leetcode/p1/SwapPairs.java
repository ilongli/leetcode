package leetcode.p1;

import leetcode.common.ListNode;

/**
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *	
 *	示例:
 *	给定 1->2->3->4, 你应该返回 2->1->4->3.
 */
public class SwapPairs {

	public static void main(String[] args) {
		ListNode node = new ListNode(1);
		node.next = new ListNode(2);
		node.next.next = new ListNode(3);
		node.next.next.next = new ListNode(4);
		node.next.next.next.next = new ListNode(5);
//		node.next.next.next.next.next = new ListNode(6);
		ListNode swap = swapPairs(node);
		while (swap != null) {
			System.out.print(swap.val + "->");	
			swap = swap.next;
		}
	}
	
    public static ListNode swapPairs(ListNode head) {
        
    	if (head == null || head.next == null) return head;
    	
    	ListNode result = new ListNode(0), tail = result;
    	ListNode[] swaps = new ListNode[2];
    	int index = 0;
    	
    	while (head != null) {
    		swaps[index++] = head;
    		head = head.next;
    		if (index == 2) {
    			index = 0;
    			for (int i = 1; i >= 0; i--) {
        			tail.next = new ListNode(swaps[i].val);
        			tail = tail.next;
    			}
    		}
    	}
    	
    	if (index == 1) {
    		tail.next = swaps[0];
    	}
    	
    	return result.next;
    }
    
    public static ListNode swapPairs2(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode temp = head;
        head = head.next;
        temp.next = head.next;
        head.next = temp;

        head.next.next = swapPairs(head.next.next);
        return head;
    }
}
