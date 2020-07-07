package leetcode.p3.q111_120;

import java.util.LinkedList;

import leetcode.common.TreeNode;

/**
 * 111.二叉树的最小深度
 * 
 * 给定一个二叉树，找出其最小深度。
 *	最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 *	
 *	说明: 叶子节点是指没有子节点的节点。
 *	
 *	示例:
 *	
 *	给定二叉树 [3,9,20,null,null,15,7],
 *	
 *	    3
 *	   / \
 *	  9  20
 *	    /  \
 *	   15   7
 *	返回它的最小深度  2.
 * 
 * @author ilongli
 * @email 351365415@qq.com
 */
public class MinDepth {

	public static void main(String[] args) {
		TreeNode root = new TreeNode(3);
		root.left = new TreeNode(9);
		TreeNode sub = new TreeNode(20);
		sub.left = new TreeNode(15);
		sub.right = new TreeNode(17);
		root.right = sub;
		System.out.println(new MinDepth().minDepth(root));
	}
	
	
	/**
	 * 递归
	 */
	int minDepth = 0;
	
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        helper(root, 1);
    	return minDepth;
    }
    
    public void helper(TreeNode node, int depth) {
    	// 剪枝
    	if (minDepth != 0 && depth > minDepth) return;
    	
    	if (node.left == null && node.right == null) {
    		if (minDepth == 0) {
    			minDepth = depth;
    		} else {
    			minDepth = Math.min(minDepth, depth);
    		}
    	} else {
    		if (node.left != null) helper(node.left, depth + 1);
    		if (node.right != null) helper(node.right, depth + 1);
    	}
    }
    
    
    /**
     * DFS
     */
    public int minDepth2(TreeNode root) {
//    	LinkedList<Pair<TreeNode, Integer>> stack = new LinkedList<>();
    	
    	
    	
    	return 0;
    }
}
