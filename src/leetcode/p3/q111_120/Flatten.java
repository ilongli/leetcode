package leetcode.p3.q111_120;

import java.util.Stack;

import leetcode.common.TreeNode;

/**
 * 114.二叉树展开为链表
 * 给定一个二叉树，原地将它展开为链表。
 *	例如，给定二叉树
 *	
 *	    1
 *	   / \
 *	  2   5
 *	 / \   \
 *	3   4   6
 *	将其展开为：
 *	
 *	1
 *	 \
 *	  2
 *	   \
 *	    3
 *	     \
 *	      4
 *	       \
 *	        5
 *	         \
 *	          6
 * 
 * @author ilongli
 * @email 351365415@qq.com
 */
public class Flatten {

	public static void main(String[] args) {
		
	}
	
	/**
	 * 迭代
	 */
    public void flatten(TreeNode root) {
    	while (root != null) {
    		// 左子树为null，直接考虑下一个节点
    		if (root.left == null) {
    			root = root.right;
    		} else {
    			// 找左子树最右边的节点
    			TreeNode pre = root.left;
    			while (pre.right != null) {
    				pre = pre.right;
    			}
    			// 将原来的右子树接到左子树的最右边节点
    			pre.right = root.right;
    			// 将左子树插入到右子树的地方
    			root.right = root.left;
    			root.left = null;
    			// 考虑下一个节点
    			root = root.right;
    		}
    	}
    }
    
    /**
     * 递归
     * 倒过来的前序遍历，每遍历一个节点就将当前节点的右指针更新为上一个节点
     * 实际上就是变形的后序遍历，遍历的顺序为：右子树->左子树->根节点
     */
    public void flatten2(TreeNode root) {
    	Stack<TreeNode> toVisit = new Stack<>();
    	TreeNode cur = root;
    	TreeNode pre = null;
    	
    	while (cur != null || !toVisit.isEmpty()) {
    		while (cur != null) {
    			toVisit.push(cur);	// 添加根节点
    			cur = cur.right;	// 递归添加右节点
    		}
    		cur = toVisit.peek();	// 已经访问到最右的节点了
    		// 在不存在左节点或者右节点已经访问过的情况下，访问根节点
    		if (cur.left == null || cur.left == pre) {
    			toVisit.pop();
    			
    			cur.right = pre;
    			cur.left = null;
    			
    			pre = cur;
    			cur = null;
    		} else {
    			cur = cur.left;	// 左节点还没有访问过就先访问左节点
    		}
    	}
    }
    
    /**
     * 递归2
     * 前序遍历，每遍历一个节点，就将上一个节点的右指针更新为当前节点
     * 我们先将右节点保存到栈中，防止右节点丢失
     */
    public void flatten3(TreeNode root) {
    	if (root == null) return;
    	Stack<TreeNode> s = new Stack<>();
    	s.push(root);
    	TreeNode pre = null;
    	while (!s.isEmpty()) {
    		TreeNode temp = s.pop();
    		if (pre != null) {
    			pre.right = temp;
    			pre.left = null;
    		}
    		if (temp.right != null) s.push(temp.right);
    		if (temp.left != null) s.push(temp.left);
    		pre = temp;
    	}
    }
}
