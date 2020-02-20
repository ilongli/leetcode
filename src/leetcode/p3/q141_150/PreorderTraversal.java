package leetcode.p3.q141_150;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import leetcode.common.TreeNode;

/**
 * 144.二叉树的前序遍历
 * 给定一个二叉树，返回它的 前序 遍历。
 *	 示例:
 *	
 *	输入: [1,null,2,3]  
 *	   1
 *	    \
 *	     2
 *	    /
 *	   3 
 *	
 *	输出: [1,2,3]
 *	进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 * 
 * @author ilongli
 * @email 351365415@qq.com
 */
public class PreorderTraversal {

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		TreeNode right = new TreeNode(2);
		right.left = new TreeNode(3);
		root.right = right;
		System.out.println(new PreorderTraversal().preorderTraversal2(root));
	}
	
	/**
	 * 1.递归
	 */
    public List<Integer> preorderTraversal(TreeNode root) {
    	List<Integer> res = new ArrayList<>();
    	helper(res, root);
    	
    	return res;
    }
    
    private void helper(List<Integer> res, TreeNode node) {
    	if (node != null) {
    		res.add(node.val);
    		helper(res, node.left);
    		helper(res, node.right);
    	}
    }
    
    /**
     * 2.迭代
     */
    public List<Integer> preorderTraversal2(TreeNode root) {
    	List<Integer> res = new ArrayList<>();
    	if (root == null) return res;

    	Stack<TreeNode> stack = new Stack<>();
    	stack.push(root);
    	
    	while (!stack.isEmpty()) {
    		TreeNode node = stack.pop();
    		res.add(node.val);
    		if (node.right != null) stack.push(node.right);
    		if (node.left != null) stack.push(node.left); 
    	}
    	
    	return res;
    }
    
}
