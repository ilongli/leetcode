package leetcode.p3.q101_110;

import java.util.HashMap;

import leetcode.common.TreeNode;

/**
 * 105.从前序与中序遍历序列构造二叉树
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 *	注意:
 *	你可以假设树中没有重复的元素。
 *	
 *	例如，给出
 *	
 *	前序遍历 preorder = [3,9,20,15,7]
 *	中序遍历 inorder = [9,3,15,20,7]
 *	返回如下的二叉树：
 *	
 *	    3
 *	   / \
 *	  9  20
 *	    /  \
 *	   15   7
 * @author ilongli
 * @email 351365415@qq.com
 */
public class BuildTree {


	
	public static void main(String[] args) {
		
	}
	
	/**
	 *  算法原理：前序遍历的第一个元素一定是树的根，这个根在中序遍历中，又将序列分成了左右两棵子树
	 */
	int pre_idx = 0;
	int[] preorder;
	int[] inorder;
	HashMap<Integer, Integer> idx_map = new HashMap<>();
	
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        this.inorder = inorder;
    	
        int idx = 0;
        // 将中序遍历的数据以val为键，下标为值，放入map内
        for (Integer val : inorder) idx_map.put(val, idx++);
        
    	return helper(0, inorder.length);
    }
    
    public TreeNode helper(int in_left, int in_right) {
    	
    	// 递归结束判断
    	if (in_left == in_right) return null;
    	
    	// 拿出树根
    	int root_val = preorder[pre_idx];
    	TreeNode root = new TreeNode(root_val);
    	
    	// 拿出中序遍历的下标
    	int index = idx_map.get(root_val);
    	
    	// 递归
    	pre_idx++;
    	// 此时index下标左边的为左子树，右边的为右子树，递归处理(先处理左树，再处理右树)
    	root.left = helper(in_left, index);
    	root.right = helper(index + 1, in_right);
    	
    	return root;
    }
}
