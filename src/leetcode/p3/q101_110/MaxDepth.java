package leetcode.p3.q101_110;

import java.util.LinkedList;
import java.util.Queue;

import leetcode.common.TreeNode;

/**
 * 104.二叉树的最大深度
 * 给定一个二叉树，找出其最大深度。
 *	二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 *	
 *	说明: 叶子节点是指没有子节点的节点。
 *	
 *	示例：
 *	给定二叉树 [3,9,20,null,null,15,7]，
 *	
 *	    3
 *	   / \
 *	  9  20
 *	    /  \
 *	   15   7
 *	返回它的最大深度 3 。
 * 
 * @author ilongli
 * @email 351365415@qq.com
 */
public class MaxDepth {
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		TreeNode sub = new TreeNode(3);
		sub.left = new TreeNode(4);
		root.right = sub;
		System.out.println(new MaxDepth().maxDepth2(root));
	}
	
	
	/**
	 * 递归
	 */
	int maxDepth = 0;
	
    public int maxDepth(TreeNode root) {
        if (root == null) return maxDepth;
        helper(root, 1);
    	return maxDepth;
    }
    
    public void helper(TreeNode node, int depth) {
    	maxDepth = Math.max(depth, maxDepth);
    	
    	if (node.left != null) helper(node.left, depth + 1);
    	if (node.right != null) helper(node.right, depth + 1);
    }
    
    
    /**
     * 迭代
     */
    public int maxDepth2(TreeNode root) {
    	if (root == null) return 0;
    	
    	Queue<TreeNode> q = new LinkedList<>();
    	q.add(root);
    	int cur = 1, level = 0;
    	while (!q.isEmpty()) {
    		int next = 0;
    		level++;
    		while (cur-- != 0) {
    			TreeNode node = q.poll();
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
    	}
    	
    	return level;
    }
}
