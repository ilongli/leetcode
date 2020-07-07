package leetcode.p3.q101_110;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import leetcode.common.TreeNode;

/**
 * 108.将有序数组转换为二叉搜索树
 * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
 *	本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 *	
 *	示例:
 *	
 *	给定有序数组: [-10,-3,0,5,9],
 *	
 *	一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
 *	
 *	      0
 *	     / \
 *	   -3   9
 *	   /   /
 *	 -10  5
 * 
 * @author ilongli
 * @email 351365415@qq.com
 */
public class SortedArrayToBST {
	
	public static void main(String[] args) {
		System.out.println(((byte)-8) >> 1);
	}
	
	/**
	 * 递归
	 */
    public TreeNode sortedArrayToBST(int[] nums) {
    	return helper(nums, 0, nums.length);
    }
    
    private TreeNode helper(int[] nums, int start, int end) {
    	if (start == end) return null;
    	
    	int mid = (start + end) >>> 1;
    	TreeNode root = new TreeNode(nums[mid]);
    	root.left = helper(nums, start, mid);
    	root.right = helper(nums, mid + 1, end);
    	return root;
    }

    class MyTreeNode {
    	TreeNode root;
    	int start;
    	int end;
    	public MyTreeNode(TreeNode r, int s, int e) {
    		this.root = r;
    		this.start = s;
    		this.end = e;
		}
    }
    
    /**
     * DFS
     */
    public TreeNode sortedArrayToBST2(int[] nums) {
    	
    	if (nums.length == 0) return null;
    	Stack<MyTreeNode> rootStack = new Stack<>();
    	int start = 0;
    	int end = nums.length;
    	int mid = (start + end) >>> 1;
    	TreeNode root = new TreeNode(nums[mid]);
    	TreeNode curRoot = root;
    	rootStack.push(new MyTreeNode(root, start, end));
    	while ((end - start) > 1 || !rootStack.isEmpty()) {
    		// 左子树
    		while ((end - start) > 1) {
    			mid = (start + end) >>> 2;	// 当前根节点
    			end = mid;	// 左子树的结尾
    			mid = (start + end) >>> 1;	// 左子树的中点
    			curRoot.left = new TreeNode(nums[mid]);
    			curRoot = curRoot.left;
    			rootStack.push(new MyTreeNode(curRoot, start, end));
    		}
    		// 出栈考虑右子树
    		MyTreeNode myNode = rootStack.pop();
    		start = myNode.start;
    		end = myNode.end;
    		mid = (start + end) >>> 1;
    		start = mid + 1;	// 右子树的start
    		curRoot = myNode.root;	// 当前根节点
    		if (start < end) {	// 判断当前范围内是否有数
    			mid = (start + end) >>> 1;
    			curRoot.right = new TreeNode(nums[mid]);
    			curRoot = curRoot.right;
    			rootStack.push(new MyTreeNode(curRoot, start, end));
    		}
    	}
    	return root;
    }
	
    
    /**
     * BFS
     */
    public TreeNode sortedArrayToBST3(int[] nums) {
    	if (nums.length == 0) return null;
    	Queue<MyTreeNode> rootQueue = new LinkedList<>();
    	TreeNode root = new TreeNode(0);
    	rootQueue.offer(new MyTreeNode(root, 0, nums.length));
    	while (!rootQueue.isEmpty()) {
    		MyTreeNode myRoot = rootQueue.poll();
    		int start = myRoot.start;
    		int end = myRoot.end;
    		int mid = (start + end) >>> 1;
    		TreeNode curRoot = myRoot.root;
    		curRoot.val = nums[mid];
    		if (start < mid) {
    			curRoot.left = new TreeNode(0);
    			rootQueue.offer(new MyTreeNode(curRoot, start, mid));
    		}
    		if (mid + 1 < end) {
    			curRoot.right = new TreeNode(0);
    			rootQueue.offer(new MyTreeNode(curRoot.right, mid + 1, end));
    		}
    	}
    	
    	return root;
    }
}
