package leetcode.p3.q141_150;

import java.util.HashSet;
import java.util.Set;

import leetcode.common.ListNode;

/**
 * 141.环形链表
 * 给定一个链表，判断链表中是否有环。
 *	为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 *	
 *	示例 1：
 *	输入：head = [3,2,0,-4], pos = 1
 *	输出：true
 *	解释：链表中有一个环，其尾部连接到第二个节点。
 *	https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/07/circularlinkedlist.png
 *	
 *	示例 2：
 *	输入：head = [1,2], pos = 0
 *	输出：true
 *	解释：链表中有一个环，其尾部连接到第一个节点。
 *	https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/07/circularlinkedlist_test2.png
 *	
 *	示例 3：
 *	输入：head = [1], pos = -1
 *	输出：false
 *	解释：链表中没有环。
 *	https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/07/circularlinkedlist_test3.png
 *	
 *	进阶：
 *	你能用 O(1)（即，常量）内存解决此问题吗？
 * 
 * @author ilongli
 * @email 351365415@qq.com
 */
public class HasCycle {

	public static void main(String[] args) {
		
		
	}
	
	/**
	 * 1.暴力法 
	 */
    public boolean hasCycle(ListNode head) {
        Set<ListNode> set =  new HashSet<>();
        while (head != null) {
        	set.add(head);
        	head = head.next;
        	if (set.contains(head)) return true;
        }
    	
    	return false;
    }
    
    /**
     * 2.快慢指针
     */
    public boolean hasCycle2(ListNode head) {
    	ListNode slow = head, fast = head;
    	while (fast != null) {
    		if (fast.next == null) return false;
    		slow = slow.next;
    		fast = fast.next.next;
    		if (fast == slow) return true;
    	}
    	return false;
    }
    
}
