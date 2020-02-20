package leetcode.p3.q111_120;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import leetcode.common.TreeNode;

/**
 * 113.路径综合II
 * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
 *	说明: 叶子节点是指没有子节点的节点。
 *	
 *	示例:
 *	给定如下二叉树，以及目标和 sum = 22，
 *	
 *	              5
 *	             / \
 *	            4   8
 *	           /   / \
 *	          11  13  4
 *	         /  \    / \
 *	        7    2  5   1
 *	返回:
 *	
 *	[
 *	   [5,4,11,2],
 *	   [5,8,4,5]
 *	]
 * 
 * 
 * @author ilongli
 * @email 351365415@qq.com
 */
public class PathSum {
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(5);
		TreeNode right_sub = new TreeNode(3);
		right_sub.left = new TreeNode(2);
		right_sub.right = new TreeNode(4);
		root.right = right_sub;
		System.out.println(new PathSum().pathSum(root, 1));
	}
	
	/**
	 * 递归
	 */
	List<List<Integer>> res = new ArrayList<>();
	LinkedList<Integer> res_stack = new LinkedList<>();
	
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if (root == null) return res;
        
        helper(root, sum);
    	
    	return res;
    }
    
    private void helper(TreeNode node, int num) {
    	
    	res_stack.add(node.val);
    	num -= node.val;
    	
    	if (node.left == null && node.right == null) {
    		if (num == 0) res.add(new LinkedList<Integer>(res_stack));
    	} else {
    		if (node.left != null) {
    			helper(node.left, num);
    			res_stack.removeLast();
    		}
    		if (node.right != null) {
    			helper(node.right, num);
    			res_stack.removeLast();
    		}
    	}
    }
    
    /**
     * 迭代
     */
    public List<List<Integer>> pathSum2(TreeNode root, int sum) {
    	List<List<Integer>> res = new ArrayList<>();
    	if (root == null) return res;
    	
    	List<Integer> subList = new ArrayList<>();	// path
     	
    	Stack<TreeNode> node_stack = new Stack<>();
    	Stack<Integer> num_stack = new Stack<>();
    	Stack<List<Integer>> path_stack = new Stack<>();
    	
    	int total = 0;
    	while (root != null || !node_stack.isEmpty()) {
    		if (root != null) {
    			total += root.val;
    			subList.add(root.val);
    			if (total == sum && root.left == null && root.right == null) {
    				res.add(new ArrayList<>(subList));
    			}
    			node_stack.push(root.right);
    			num_stack.push(total);
    			path_stack.push(new ArrayList<>(subList));
    			root = root.left;
    		} else {
    			root = node_stack.pop();
    			total = num_stack.pop();
    			subList = path_stack.pop();
    		}
    	}
    	
    	return res;
    }
}
