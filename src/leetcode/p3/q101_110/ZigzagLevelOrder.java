package leetcode.p3.q101_110;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import leetcode.common.TreeNode;

/**
 * 103.二叉树的锯齿形层次遍历
 * 给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 *	例如：
 *	给定二叉树 [3,9,20,null,null,15,7],
 *	
 *	    3
 *	   / \
 *	  9  20
 *	    /  \
 *	   15   7
 *	返回锯齿形层次遍历如下：
 *	
 *	[
 *	  [3],
 *	  [20,9],
 *	  [15,7]
 *	]
 * 
 * @author ilongli
 * @email 351365415@qq.com
 */
public class ZigzagLevelOrder {

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		TreeNode sub = new TreeNode(3);
		sub.left = new TreeNode(4);
		sub.right = new TreeNode(5);
		root.right = sub;
		System.out.println(new ZigzagLevelOrder().zigzagLevelOrder2(root));
	}
	
	/**
	 * 迭代
	 */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    	List<List<Integer>> res = new ArrayList<>();
    	if (root == null) return res;
    	
    	Queue<TreeNode> q = new LinkedList<>();
    	q.add(root);
    	int cur = 1;
    	boolean asc = true;
    	while (!q.isEmpty()) {
    		LinkedList<Integer> vals = new LinkedList<>();
    		int next = 0;
    		while (cur-- != 0) {
    			TreeNode node = q.poll();
    			if (asc) {
    				vals.addLast(node.val);
    			} else {
    				vals.addFirst(node.val);
    			}
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
    		asc = !asc;
    	}
    	return res;
    }


    /**
     * 递归
     */
    List<List<Integer>> levels = new ArrayList<>();
    
    public List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
    	if (root == null) return levels;
    	helper(root, 0);
    	return levels;
    }
    
    public void helper(TreeNode node, int level) {
    	// 每层初始化
    	if (levels.size() == level) {
    		levels.add(new LinkedList<Integer>());
    	}
    	
    	LinkedList<Integer> list = (LinkedList<Integer>) levels.get(level);
    	if (level % 2 == 0) {
    		list.addLast(node.val);
    	} else {
    		list.addFirst(node.val);
    	}
    	
    	if (node.left != null) helper(node.left, level + 1);
    	if (node.right != null) helper(node.right, level + 1);
    }
}
