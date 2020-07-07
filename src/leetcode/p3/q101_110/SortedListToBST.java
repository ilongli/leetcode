package leetcode.p3.q101_110;

import java.util.ArrayList;
import java.util.List;

import leetcode.common.ListNode;
import leetcode.common.TreeNode;

/**
 * 109.有序链表转换二叉搜索树
 * 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
 *	本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 *	
 *	示例:
 *	
 *	给定的有序链表： [-10, -3, 0, 5, 9],
 *	
 *	一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：
 *	
 *	      0
 *	     / \
 *	   -3   9
 *	   /   /
 *	 -10  5
 * 
 * @author ilongli
 * @email 351365415@qq.com
 */
public class SortedListToBST {
	
	public static void main(String[] args) {
		
	}

	/**
	 * 1.递归 
	 * 时间复杂度: O(N*log(N))
	 * 空间复杂度: O(N)
	 */
	private ListNode findMiddleElement(ListNode head) {
		
		ListNode prevPtr = null, slowPtr = head, fastPtr = head;
		
		while (fastPtr != null && fastPtr.next != null) {
			prevPtr = slowPtr;
			slowPtr = slowPtr.next;
			fastPtr = fastPtr.next.next;
		}
		
		// 
		if (prevPtr != null) prevPtr.next = null;
		
		return slowPtr;
	}
	
    public TreeNode sortedListToBST(ListNode head) {
    	
    	if (head == null) return null;
    	
    	ListNode mid = this.findMiddleElement(head);
    	
    	TreeNode node = new TreeNode(mid.val);
    	
    	if (head == mid) return node;
    	
    	node.left = this.sortedListToBST(head);
    	node.right = this.sortedListToBST(mid.next);
    	
    	return node;
    }
    
    
    /**
     * 2.转成数组再递归(牺牲空间换时间)
     * 时间复杂度: O(N)
     * 空间复杂度: O(N)
     */
    private List<Integer> values;
    
    public SortedListToBST() {
    	this.values = new ArrayList<>();
    }
    
    private void mapListToValues(ListNode head) {
    	while (head != null) {
    		this.values.add(head.val);
    		head = head.next;
    	}
    }
    
    private TreeNode convertListToBST(int left, int right) {
    	if (left > right) return null;
    	
    	int mid = (left + right) >>> 1;
    	TreeNode node = new TreeNode(this.values.get(mid));
    	
    	if (left == right) return node;
    	
    	node.left = convertListToBST(left, mid - 1);
    	node.right = convertListToBST(mid + 1, right);
    	
    	return node;
    }
    
    public TreeNode sortedListToBST2(ListNode head) {
    	this.mapListToValues(head);
    	
    	return convertListToBST(0, this.values.size() - 1);
    }
    
    
    /**
     * 3.中序遍历模拟
     */
    private ListNode head;
    
    private int findSize(ListNode head) {
    	ListNode ptr = head;
    	int c = 0;
    	while (ptr != null) {
    		ptr = ptr.next;
    		c++;
    	}
    	return c;
    }
    
    private TreeNode convertListToBST3(int l, int r) {
    	if (l > r) return null;
    	
    	int mid = (l + r) / 2;
    	
    	TreeNode left = this.convertListToBST3(l, mid - 1);
    	
    	TreeNode node = new TreeNode(this.head.val);
    	node.left = left;
    	
    	this.head = this.head.next;
    	
    	node.right = this.convertListToBST3(mid + 1, r);
    	return node;
    }
    
    
    public TreeNode sortedListToBST3(ListNode head) {
    	
    	int size = this.findSize(head);
    	
    	this.head = head;
    	
    	return convertListToBST3(0, size - 1);
    }
}
