package leetcode.p3.q121_130;

import leetcode.common.TreeNode;

/**
 * 129.求根到叶子节点数字之和
 * 给定一个二叉树，它的每个结点都存放一个 0-9 的数字，每条从根到叶子节点的路径都代表一个数字。
 *	例如，从根到叶子节点路径 1->2->3 代表数字 123。
 *	
 *	计算从根到叶子节点生成的所有数字之和。
 *	
 *	说明: 叶子节点是指没有子节点的节点。
 *	
 *	示例 1:
 *	
 *	输入: [1,2,3]
 *	    1
 *	   / \
 *	  2   3
 *	输出: 25
 *	解释:
 *	从根到叶子节点路径 1->2 代表数字 12.
 *	从根到叶子节点路径 1->3 代表数字 13.
 *	因此，数字总和 = 12 + 13 = 25.
 * 
 * @author ilongli
 * @email 351365415@qq.com
 */
public class SumNumbers {

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		System.out.println(new SumNumbers().sumNumbers(null));
	}

	
	/**
	 * 回溯
	 */
	int res = 0;
	
    public int sumNumbers(TreeNode root) {
    	if (root == null) return res;
    	helper(root, "");
    	return res;
    }
    
    private void helper(TreeNode node, String curNum) {
    	
    	curNum = curNum + node.val;
    	
    	if (node.left == null && node.right == null) {
    		res += Integer.parseInt(curNum);
    	} else {
    		if (node.left != null) helper(node.left, curNum);
    		if (node.right != null) helper(node.right, curNum);
    	}
    }
    
    /**
     * 分治
     */
    public int sumNumbers2(TreeNode root) {
    	if (root == null) return 0;
    	return helper2(root, 0);
    }
    
    private int helper2(TreeNode root, int sum) {
    	int curSum = sum * 10 + root.val;
    	if (root.left == null && root.right == null) return curSum;
    	
    	int ans = 0;
    	if (root.left != null) ans += helper2(root.left, curSum);
    	if (root.right != null) ans += helper2(root.right, curSum);
    	
    	return ans;
    }
}
