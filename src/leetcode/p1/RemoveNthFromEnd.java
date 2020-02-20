package leetcode.p1;

import leetcode.common.ListNode;

public class RemoveNthFromEnd {

	public static void main(String[] args) {
		ListNode head = new ListNode(1);
//		head.next = new ListNode(2);
//		head.next.next = new ListNode(3);
//		head.next.next.next = new ListNode(4);
//		head.next.next.next.next = new ListNode(5);
//		head.next.next.next.next.next = new ListNode(6);
		ListNode node = removeNthFromEnd(head, 1);
		
		if (node != null) {
			System.out.print(node.val + "->");
			while (node.next != null) {
				node = node.next;
				System.out.print(node.val + "->");
			}
		} else {
			System.out.println(node);
		}
	}
	
    public static ListNode removeNthFromEnd(ListNode head, int n) {
		// 如果只有一个元素，直接返回null
    	if (head.next == null) return null;
        
    	// 当前遍历到的节点
    	ListNode now = head;
    	// 两个临时节点
    	ListNode[] ps = new ListNode[2];
    	ps[0] = head;
    	ps[1] = head;

    	// 临时节点标识，用于交替更新临时节点
    	int index = 1;
    	// 步进
    	int step = 0;
    	
    	// loop util null
    	while (now.next != null) {
    		if (step == n) {
    			step = 0;
    			ps[index] = now;
    			if (++index == 2) index = 0;
    		}
    		
    		// next 
    		now = now.next;
    		step++;
    	}
    	
    	// 边界条件判断(n=1)
    	if (n == 1) {
    		ps[1 - index].next = null;
    		return head;
    	}
    	
    	// 当两个标识相等时，说明ps没有一次更新
    	if (ps[0] == ps[1]) {
    		// 边界条件判断(刚好是第一位)
    		if (step + 1 == n) return head.next;
    		// 否则取step的余值
    		step = n - step;
    	}
    	
    	// 切割
    	ListNode temp = ps[index];
    	for (int i = 0; i < step; i++) {
    		temp = temp.next;
    	}
    	temp.next = temp.next.next;
    	
    	return head;
    }

}
