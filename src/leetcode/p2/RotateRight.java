package leetcode.p2;

import leetcode.common.ListNode;

/**
 * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
 *	示例 1:
 *	
 *	输入: 1->2->3->4->5->NULL, k = 2
 *	输出: 4->5->1->2->3->NULL
 *	解释:
 *	向右旋转 1 步: 5->1->2->3->4->NULL
 *	向右旋转 2 步: 4->5->1->2->3->NULL
 *	示例 2:
 *	
 *	输入: 0->1->2->NULL, k = 4
 *	输出: 2->0->1->NULL
 *	解释:
 *	向右旋转 1 步: 2->0->1->NULL
 *	向右旋转 2 步: 1->2->0->NULL
 *	向右旋转 3 步: 0->1->2->NULL
 *	向右旋转 4 步: 2->0->1->NULL
 * @author ilongli
 * @email 351365415@qq.com
 */
public class RotateRight {
	
	public static void main(String[] args) {
		ListNode head = new ListNode(0);
		head.next = new ListNode(1);
//		head.next.next = new ListNode(2);
//		head.next.next.next = new ListNode(4);
//		head.next.next.next.next = new ListNode(5);
		
		
		ListNode rotate = rotateRight(head, 4);
		
		while (rotate != null) {
			System.out.print(rotate.val + "->");	
			rotate = rotate.next;
		}
	}
	
    public static ListNode rotateRight(ListNode head, int k) {
        
    	// check null
    	if (head == null || k == 0) return head;
    	
    	// 开始结束节点，用于定位
    	ListNode start = head, end = head;
    	// 计数器
    	int count = 0;
    	
    	// 遍历链表
    	while (end.next != null) {
    		if (count >= k) {
    			start = start.next;
    			end = end.next;
    		} else {
    			end = end.next;
    		}
    		count++;
    	}
    	
    	// 当右移次数比链表长度还大的时候，以链表长度取余，重新调用一次函数
    	if (count < k) {
    		return rotateRight(head, k % ++count);
    	}
    	
    	// rotate
    	ListNode rotate = start.next;
    	start.next = null;
    	end.next = head;
    	
    	return rotate;
    }
}
