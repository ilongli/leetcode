package leetcode.p2;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import leetcode.common.TreeNode;


/**
 * 给定一个二叉树，返回它的中序 遍历。
 *	示例:
 *	
 *	输入: [1,null,2,3]
 *	   1
 *	    \
 *	     2
 *	    /
 *	   3
 *	
 *	输出: [1,3,2]
 *	进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 * @author ilongli
 * @email 351365415@qq.com
 */
public class InorderTraversal {

	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = null;
		root.right = new TreeNode(2);
		root.right.left = new TreeNode(3);
		System.out.println(new InorderTraversal().inorderTraversal(root));
	}
	
	
	/**
	 * 迭代(莫里斯遍历)
	 */
	public List<Integer> inorderTraversal3(TreeNode root) {
		List<Integer> res = new ArrayList<>();
		TreeNode cur = root, pre;
		while (cur != null) {
			if (cur.left == null) {
				res.add(cur.val);
				cur = cur.right;
			} else {
				pre = cur.left;
				// 找出左子树的最右的节点
				while (pre.right != null) {
					pre = pre.right;
				}
				// 拼接
				pre.right = cur;
				TreeNode temp = cur;
				cur = cur.left;
				temp.left = null;
			}
		}
		return res;
	}
	
	
	/**
	 * 迭代(栈)
	 */
	public List<Integer> inorderTraversal2(TreeNode root) {
		List<Integer> res = new ArrayList<>();
		Stack<TreeNode> statck = new Stack<>();
		TreeNode cur = root;
		while (cur != null || !statck.isEmpty()) {
			while (cur != null) {
				statck.push(cur);
				cur = cur.left;
			}
			cur = statck.pop();
			res.add(cur.val);
			cur = cur.right;
		}
		return res;
	}
	
	/**
	 * 递归
	 */
    public List<Integer> inorderTraversal(TreeNode root) {
    	List<Integer> res = new ArrayList<>();
    	minOrder(root, res);
    	return res;
    }
    
    public void minOrder(TreeNode node, List<Integer> res) {
    	if (node != null) {
    		minOrder(node.left, res);
    		res.add(node.val);
    		minOrder(node.right, res);
    	}
    }
    
}
