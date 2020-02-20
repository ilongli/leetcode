package leetcode.p3.q101_110;

import java.util.LinkedList;
import java.util.Queue;

import leetcode.common.TreeNode;

/**
 * 101.对称二叉树
 * 给定一个二叉树，检查它是否是镜像对称的。
 *	例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 *	
 *	    1
 *	   / \
 *	  2   2
 *	 / \ / \
 *	3  4 4  3
 *	但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 *	
 *	    1
 *	   / \
 *	  2   2
 *	   \   \
 *	   3    3
 *	说明:
 *	
 *	如果你可以运用递归和迭代两种方法解决这个问题，会很加分。
 * 
 * 
 * @author ilongli
 * @email 351365415@qq.com
 */
public class IsSymmetric {

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		System.out.println(new IsSymmetric().isSymmetric2(root));
	}
	
	/**
	 * 递归
	 */
    public boolean isSymmetric(TreeNode root) {
    	if (root == null) return true;
    	return checkSymmetric(root.left, root.right);
    }
    
    public boolean checkSymmetric(TreeNode p, TreeNode q) {
    	if (p == null && q == null) return true;
    	if (p == null || q == null) return false;
    	if (p.val != q.val) return false;
    	return checkSymmetric(p.left, q.right) && checkSymmetric(p.right, q.left);
    }
    
    
    /**
     * 迭代
     */
    public boolean isSymmetric2(TreeNode root) {
    	Queue<TreeNode> q = new LinkedList<>();
    	q.add(root);
    	q.add(root);
    	while (!q.isEmpty()) {
    		TreeNode t1 = q.poll(), t2 = q.poll();
    		if (t1 == null && t2 == null) continue;
    		if (t1 == null || t2 == null) return false;
    		if (t1.val != t2.val) return false;
    		q.add(t1.left);
    		q.add(t2.right);
    		q.add(t1.right);
    		q.add(t2.left);
    	}
    	return true;
    }
}
