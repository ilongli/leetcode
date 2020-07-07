package leetcode.p1;

import java.util.PriorityQueue;

import leetcode.common.ListNode;

/**
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 * 示例:
 * 
 * 输入:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 输出: 1->1->2->3->4->4->5->6
 *
 */
public class MergeKLists {
	
	public static void main(String[] args) {
		// [[1,4,5],[1,3,4],[2,6]]
		ListNode l1 = new ListNode(1);
		l1.next = new ListNode(4);
		l1.next.next = new ListNode(5);
		ListNode l2 = new ListNode(1);
		l2.next = new ListNode(3);
		l2.next.next = new ListNode(4);
		ListNode l3 = new ListNode(2);
		l3.next = new ListNode(6);
//		ListNode merge = mergeKLists(new ListNode[] {l1, l2, l3});
		ListNode merge = mergeKLists2(new ListNode[] {l1, l2, l3});
		while (merge != null) {
			System.out.print(merge.val + "->");	
			merge = merge.next;
		}
		
	}
	
    public static ListNode mergeKLists(ListNode[] lists) {
		if (lists.length == 0)
			return null;
		int k = lists.length;
		while (k > 1) {
			for (int i = 0; i < k / 2; i++)
				lists[i] = mergeTwoLists(lists[i], lists[i + (k + 1) / 2]);
			k = (k + 1) / 2;
		}
		return lists[0];
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
    
	public static ListNode mergeKLists2(ListNode[] lists) {
		ListNode head = new ListNode(0), tail = head;
		PriorityQueue<ListNode> queue = new PriorityQueue<>((x, y) -> {
			return x.val - y.val;
		});
		for (int i = 0; i < lists.length; i++) {
			if (lists[i] != null)
				queue.add(lists[i]);
		}
		while (!queue.isEmpty()) {
			ListNode node = queue.poll();
			tail.next = node;
			tail = node;
			if (node.next != null)
				queue.add(node.next);
		}

		return head.next;
	}
}
