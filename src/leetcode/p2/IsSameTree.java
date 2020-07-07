package leetcode.p2;

import leetcode.common.TreeNode;

/**
 * 100.相同的树
 * 给定两个二叉树，编写一个函数来检验它们是否相同。
 *	如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 *	
 *	示例 1:
 *	
 *	输入:       1         1
 *	     / \       / \
 *	    2   3     2   3
 *	
 *	    [1,2,3],   [1,2,3]
 *	
 *	输出: true
 *	示例 2:
 *	
 *	输入:      1          1
 *	     /           \
 *	    2             2
 *	
 *	    [1,2],     [1,null,2]
 *	
 *	输出: false
 *	示例 3:
 *	
 *	输入:       1         1
 *	     / \       / \
 *	    2   1     1   2
 *	
 *	    [1,2,1],   [1,1,2]
 *	
 *	输出: false
 * 
 * @author ilongli
 * @email 351365415@qq.com
 */
public class IsSameTree {

	public static void main(String[] args) {
		TreeNode p = new TreeNode(3);
		p.left = new TreeNode(1);
		p.right = new TreeNode(4);
		TreeNode q = new TreeNode(3);
		q.left = new TreeNode(1);
		TreeNode sub = new TreeNode(4);
		sub.right = new TreeNode(6);
		q.right = sub;
		
		System.out.println(new IsSameTree().isSameTree(p, q));
	}
	
    public boolean isSameTree(TreeNode p, TreeNode q) {
    	return checkSame(p, q);
    }
    
    public boolean checkSame(TreeNode p, TreeNode q) {
    	if (p == null && q == null) return true;
    	if (p == null || q == null) return false;
    	if (p.val != q.val) return false;
    	boolean isSame = true;
    	isSame &= checkSame(p.left, q.left);
    	if (!isSame) return false;
    	isSame &= checkSame(p.right, q.right);
    	return isSame;
    }
}
