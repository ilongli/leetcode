package leetcode.p3.q101_110;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import leetcode.common.TreeNode;

/**
 * 102.二叉树的层次遍历
 * 给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。
 *	例如:
 *	给定二叉树: [3,9,20,null,null,15,7],
 *	
 *	    3
 *	   / \
 *	  9  20
 *	    /  \
 *	   15   7
 *	返回其层次遍历结果：
 *	
 *	[
 *	  [3],
 *	  [9,20],
 *	  [15,7]
 *	]
 * 
 * 
 * @author ilongli
 * @email 351365415@qq.com
 */
public class LevelOrder {

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		TreeNode sub = new TreeNode(3);
		sub.left = new TreeNode(3);
		sub.right = new TreeNode(4);
		root.right = sub;
		System.out.println(new LevelOrder().levelOrder(root));
	}
	
	/**
	 * 迭代 
	 */
    public List<List<Integer>> levelOrder(TreeNode root) {
    	List<List<Integer>> res = new ArrayList<>();
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
    		res.add(vals);
    	}
    	return res;
    }

    /**
     * 递归
     */
    List<List<Integer>> levels = new ArrayList<>();
    
    public List<List<Integer>> levelOrder2(TreeNode root) {
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
