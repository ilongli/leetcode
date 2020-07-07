package leetcode.p3.q111_120;

import java.util.LinkedList;

import leetcode.common.TreeNode;

/**
 * 112.路径总和
 * 
 * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
 *	说明: 叶子节点是指没有子节点的节点。
 *	
 *	示例: 
 *	给定如下二叉树，以及目标和 sum = 22，
 *	
 *	              5
 *	             / \
 *	            4   8
 *	           /   / \
 *	          11  13  4
 *	         /  \      \
 *	        7    2      1
 *	返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
 * 
 * @author ilongli
 * @email 351365415@qq.com
 */
public class HasPathSum {

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(4);
		root.right = new TreeNode(2);
		System.out.println(new HasPathSum().hasPathSum(root, 4));
	}
	
	
	/**
	 * 递归 
	 */
	boolean hasPathSum = false;
	int sum = 0;
	
    public boolean hasPathSum(TreeNode root, int sum) {
    	if (root == null) return false;
    	this.sum = sum;
    	helper(root, 0);
    	return hasPathSum;
    }
    
    private void helper(TreeNode node, int sum) {
    	// 剪枝
    	if (hasPathSum) return;
    	
    	sum += node.val;
    	
    	if (node.left == null && node.right == null) {
    		if (sum == this.sum) this.hasPathSum = true;
    	} else {
    		if (node.left != null) helper(node.left, sum);		
    		if (node.right != null) helper(node.right, sum);		
    	}
    }
    
    
    /**
     * 迭代
     */
    public boolean hasPathSum2(TreeNode root, int sum) {
    	if (root == null) return false;
    	
    	LinkedList<TreeNode> node_stack = new LinkedList<>();
    	LinkedList<Integer> sum_stack = new LinkedList<>();
    	node_stack.add(root);
    	sum_stack.add(sum - root.val);
    	
    	TreeNode node;
    	int curr_sum;
    	while (!node_stack.isEmpty()) {
    		node = node_stack.pollLast();
    		curr_sum = sum_stack.pollLast();
    		
    		if ((node.right == null) && (node.left == null) && (curr_sum == 0)) return true;
    		
    		if (node.right != null) {
    			node_stack.add(node.right);
    			sum_stack.add(curr_sum - node.right.val);
    		}
    		
    		if (node.left != null) {
    			node_stack.add(node.left);
    			sum_stack.add(curr_sum - node.left.val);
    		}
    	}
    	
    	return false;
    }
    
}
