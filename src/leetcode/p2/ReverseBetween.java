package leetcode.p2;

import leetcode.common.ListNode;

/**
 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
 *	说明:
 *	1 ≤ m ≤ n ≤ 链表长度。
 *	
 *	示例:
 *	
 *	输入: 1->2->3->4->5->NULL, m = 2, n = 4
 *	输出: 1->4->3->2->5->NULL
 * @author ilongli
 * @email 351365415@qq.com
 */
public class ReverseBetween {

	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(4);
		head.next.next.next.next = new ListNode(5);
		ListNode _head = reverseBetween(head, 4, 5);
		while (_head != null) {
			System.out.print(_head.val + "->");
			_head = _head.next;
		}
		
	}
	
	// 输入: 1->2->3->4->5->NULL, m = 2, n = 4
	// 输出: 1->4->3->2->5->NULL
    public static ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null) return null;
        if (m == n) return head;
    	
    	ListNode cut_start = null, start = null, end = null, cur = head, tmp, pre = null;
    	int idx = 1;
    	
    	while (idx <= n) {
    		if (idx < m) {
    			cut_start = cur;
    		} else if (idx == m) {
    			start = cur;
    			pre = cur;
    		} else {
    			if (idx == n) end = cur;
    			tmp = cur.next;
    			cur.next = pre;
    			pre = cur;
    			cur = tmp;
    			idx++;
    			continue;
    		}
    		
    		idx++;
    		cur = cur.next;
    	}
    	
    	start.next = cur;
    	
    	// 如果是从头部开始反转，则不用把头连到尾
    	if (m == 1) return end;
    	
    	cut_start.next = end;
    	
    	return head;
    }
}
