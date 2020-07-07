package leetcode.p3.q141_150;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import leetcode.common.TreeNode;

/**
 * 145.二叉树的后序遍历
 * 
 * 给定一个二叉树，返回它的 后序 遍历。
 *	示例:
 *	
 *	输入: [1,null,2,3]  
 *	   1
 *	    \
 *	     2
 *	    /
 *	   3 
 *	
 *	输出: [3,2,1]
 *	进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 * @author ilongli
 * @email 351365415@qq.com
 */
public class PostorderTraversal {

	public static void main(String[] args) {
		TreeNode root = new TreeNode(3);
		TreeNode right = new TreeNode(4);
		right.left = new TreeNode(1);
//		right.right = new TreeNode(4);
		root.right = right;
		root.left = new TreeNode(2);
		System.out.println(new PostorderTraversal().postorderTraversal2(root));
	}
	
	/**
	 * 递归
	 * @param root
	 * @return
	 */
    public List<Integer> postorderTraversal(TreeNode root) {
    	List<Integer> res = new ArrayList<>();
    	helper(res, root);
    	
    	return res;
    }
    
    private void helper(List<Integer> res, TreeNode node) {
    	if (node != null) {
    		helper(res, node.left);
    		helper(res, node.right);
    		res.add(node.val);
    	}
    }
    
    /**
     * 迭代
     */
    public List<Integer> postorderTraversal2(TreeNode root) {
    	List<Integer> res = new ArrayList<>();
    	if (root == null) return res;

    	Stack<TreeNode> stack = new Stack<>();
    	Stack<Integer> resStack = new Stack<>();
    	stack.push(root);
    	
    	while (!stack.isEmpty()) {
    		TreeNode node = stack.pop();
    		resStack.push(node.val);
    		if (node.left != null) stack.push(node.left); 
    		if (node.right != null) stack.push(node.right);
    	}
    	
    	while (!resStack.isEmpty()) {
    		res.add(resStack.pop());
    	}
    	
    	return res;
    }
}
