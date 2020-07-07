package leetcode.p3.q101_110;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import leetcode.common.TreeNode;

/**
 * 107.二叉树的层次遍历II
 * 给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 *	例如：
 *	给定二叉树 [3,9,20,null,null,15,7],
 *	
 *	    3
 *	   / \
 *	  9  20
 *	    /  \
 *	   15   7
 *	返回其自底向上的层次遍历为：
 *	
 *	[
 *	  [15,7],
 *	  [9,20],
 *	  [3]
 *	]
 * 
 * 
 * @author ilongli
 * @email 351365415@qq.com
 */
public class LevelOrderBottom {

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		TreeNode sub = new TreeNode(3);
		sub.left = new TreeNode(3);
		sub.right = new TreeNode(4);
		root.right = sub;
		System.out.println(new LevelOrderBottom().levelOrderBottom(root));
	}
	
	/**
	 * 迭代 
	 */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
    	LinkedList<List<Integer>> res = new LinkedList<>();
    	if (root == null) return res;
    	
    	Queue<TreeNode> q = new LinkedList<>();
    	q.add(root);
    	int cur = 1;
    	while (!q.isEmpty()) {
    		List<Integer> vals = new ArrayList<>();
    		int next = 0;
    		while (cur-- != 0) {
    			TreeNode node = q.poll();
    			vals.add(node.val);
    			if (node.left != null) {
    				q.add(node.left);
    				next++;
    			}
    			if (node.right != null) {
    				q.add(node.right);
    				next++;
    			}
    		}
    		cur = next;
    		res.addFirst(vals);
    	}
    	return res;
    }
    
    /**
     * 递归
     */
    LinkedList<List<Integer>> levels = new LinkedList<>();
    
    public List<List<Integer>> levelOrderBottom2(TreeNode root) {
    	if (root == null) return levels;
    	helper(root, 0);
    	return levels;
    }
    
    public void helper(TreeNode node, int level) {
    	// 每层初始化
    	if (levels.size() == level) {
    		levels.add(new ArrayList<Integer>());
    	}
    	
    	levels.get(level).add(node.val);
    	
    	if (node.left != null) helper(node.left, level + 1);
    	if (node.right != null) helper(node.right, level + 1);
    }
}
