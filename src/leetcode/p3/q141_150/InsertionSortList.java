package leetcode.p3.q141_150;

import leetcode.common.ListNode;

/**
 * 147.对链表进行插入排序
 * 插入排序的动画演示如上。从第一个元素开始，该链表可以被认为已经部分排序（用黑色表示）。
 *	对链表进行插入排序。
 *	https://upload.wikimedia.org/wikipedia/commons/0/0f/Insertion-sort-example-300px.gif
 *	每次迭代时，从输入数据中移除一个元素（用红色表示），并原地将其插入到已排好序的链表中。
 *	
 *	插入排序算法：
 *	插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
 *	每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
 *	重复直到所有输入数据插入完为止。
 *	 
 *	示例 1：
 *	输入: 4->2->1->3
 *	输出: 1->2->3->4
 *	
 *	示例 2：
 *	输入: -1->5->3->4->0
 *	输出: -1->0->3->4->5
 *	
 * @author ilongli
 * @email 351365415@qq.com
 */
public class InsertionSortList {

	public static void main(String[] args) {
		ListNode head = new ListNode(6);
		ListNode sub = new ListNode(5);
		ListNode sub_sub = new ListNode(4);
		ListNode sub_sub_sub = new ListNode(2);
		sub_sub_sub.next = new ListNode(1);
		sub_sub.next = sub_sub_sub;
		sub.next = sub_sub;
		head.next = sub;
		
		ListNode res = insertionSortList(head);
		
		while (null != res) {
			System.out.print(res.val + "->");
			res = res.next;
		}
		
	}
	
	// 4 -> 2 -> 1 -> 3
    public static ListNode insertionSortList(ListNode head) {
    	if (null == head) return head;
    	ListNode cur = head, res = new ListNode(Integer.MIN_VALUE);
    	res.next = head;
    	int end = 0;
    	
    	while (null != cur.next) {
    		cur = insert(res, cur, ++end);
    	}
    	
    	return res.next;
    }
    
    public static ListNode insert(ListNode head, ListNode cur, int end) {
    	ListNode target = cur.next;
    	// 这里加了尾部判断，在部分场景能加快速度
    	if (cur.val < target.val) {
    		return target;
    	}
    	for (int i = 0; i < end; i++) {
    		if (target.val < head.next.val) {
    			cur.next = target.next;
    			ListNode temp = head.next;
    			head.next = target;
    			target.next = temp;
    			return cur;
    		}
    		head = head.next;
    	}
    	return target;
    }
    
    
}
