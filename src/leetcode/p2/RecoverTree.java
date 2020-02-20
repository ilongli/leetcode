package leetcode.p2;

import java.util.Stack;

import leetcode.common.TreeNode;

/**
 * 99.恢复二叉搜索树
 * 二叉搜索树中的两个节点被错误地交换。
 *	请在不改变其结构的情况下，恢复这棵树。
 *	
 *	示例 1:
 *	
 *	输入: [1,3,null,null,2]
 *	
 *	   1
 *	  /
 *	 3
 *	  \
 *	   2
 *	
 *	输出: [3,1,null,null,2]
 *	
 *	   3
 *	  /
 *	 1
 *	  \
 *	   2
 *	示例 2:
 *	
 *	输入: [3,1,4,null,null,2]
 *	
 *	  3
 *	 / \
 *	1   4
 *	   /
 *	  2
 *	
 *	输出: [2,1,4,null,null,3]
 *	
 *	  2
 *	 / \
 *	1   4
 *	   /
 *	  3
 *	进阶:
 *	
 *	使用 O(n) 空间复杂度的解法很容易实现。
 *	你能想出一个只使用常数空间的解决方案吗？
 * 
 * @author ilongli
 * @email 351365415@qq.com
 */
public class RecoverTree {

	public static void main(String[] args) {
		TreeNode root = new TreeNode(3);
		root.left = new TreeNode(1);
		TreeNode sub = new TreeNode(4);
		sub.left = new TreeNode(2);
		root.right = sub;
		recoverTree(root);
	}
	
    public static void recoverTree(TreeNode root) {
    	Stack<TreeNode> stack = new Stack<>();
    	TreeNode preNode = new TreeNode(Integer.MIN_VALUE), n1 = null, n2 = null;
    	
    	while (!stack.isEmpty() || root != null) {
    		while (root != null) {
    			stack.push(root);
    			root = root.left;
    		}
    		root = stack.pop();
    		
    		if (n1 == null && root.val < preNode.val) {
    			n1 = preNode;
    		}
    		if (n1 != null && root.val < preNode.val) {
    			n2 = root;
    		}
    		
    		preNode = root;
    		root = root.right;
    	}
    	
    	// 交换
    	int temp = n1.val;
    	n1.val = n2.val;
    	n2.val = temp;
    }
    
    // 3 2 1 
    // 1 2 3
}
