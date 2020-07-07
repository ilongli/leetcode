package leetcode.p2;

import java.util.LinkedList;
import java.util.Stack;

import leetcode.common.TreeNode;

/**
 * 98. 验证二叉搜索树
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 *	假设一个二叉搜索树具有如下特征：
 *	
 *	节点的左子树只包含小于当前节点的数。
 *	节点的右子树只包含大于当前节点的数。
 *	所有左子树和右子树自身必须也是二叉搜索树。
 *	示例 1:
 *	
 *	输入:
 *	    2
 *	   / \
 *	  1   3
 *	输出: true
 *	示例 2:
 *	
 *	输入:
 *	    5
 *	   / \
 *	  1   4
 *	     / \
 *	    3   6
 *	输出: false
 *	解释: 输入为: [5,1,4,null,null,3,6]。
 *	     根节点的值为 5 ，但是其右子节点值为 4 。
 * 
 * @author ilongli
 * @email 351365415@qq.com
 */
public class IsValidBST {
	
	public static void main(String[] args) {
		
	}
	
	/**
	 * 1.递归
	 * 时间复杂度:O(N)。每个结点访问一次。
	 * 空间复杂度:O(N)。我们跟进了整棵树。
	 */
    public boolean isValidBST1(TreeNode root) {
    	return checkTree(root, null, null);
    }
    
    public boolean checkTree(TreeNode node, Integer lower, Integer upper) {
    	if (node == null) return true;
    	
    	int val = node.val;
    	if (lower != null && val <= lower) return false;
    	if (upper != null && val >= upper) return false;
    	
    	if (!checkTree(node.left, lower, val)) return false;
    	if (!checkTree(node.right, val, upper)) return false;
    	
    	return true;
    }
    
    /**
     * 2.迭代
     * 时间复杂度:O(N)。每个结点访问一次。
     * 空间复杂度:O(N)。我们跟进了整棵树。
     */
    LinkedList<TreeNode> stack = new LinkedList<>();
    LinkedList<Integer> uppers = new LinkedList<>(),
    					lowers = new LinkedList<>();
    
    public void update(TreeNode root, Integer lower, Integer upper) {
    	stack.add(root);
    	lowers.add(lower);
    	uppers.add(upper);
    }
    
    public boolean isValidBST2(TreeNode root) {
    	Integer lower = null, upper = null, val;
    	update(root, lower, upper);
    	
    	while (!stack.isEmpty()) {
    		root =stack.poll();
    		lower = lowers.poll();
    		upper = uppers.poll();
    		
    		if (root == null) continue;
    		val = root.val;
    		if (lower != null && val <= lower) return false;
    		if (upper != null && val >= upper) return false;
    		update(root.right, val, upper);
    		update(root.left, lower, val);
    	}
    	return true;
    }
    
    /**
     * 中序遍历
     */
    public boolean isValidBST3(TreeNode root) {
    	Stack<TreeNode> stack = new Stack<>();
    	double inorder = - Double.MAX_VALUE;
    	
    	while (!stack.isEmpty() || root != null) {
    		while (root != null) {
    			stack.push(root);
    			root = root.left;
    		}
    		root = stack.pop();
    		if (root.val <= inorder) return false;
    		inorder = root.val;
    		root = root.right;
    	}
    	return true;
    }
}
