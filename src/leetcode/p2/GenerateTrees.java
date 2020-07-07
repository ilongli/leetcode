package leetcode.p2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import leetcode.common.TreeNode;

/**
 * 95. 不同的二叉搜索树 II
 * 给定一个整数 n，生成所有由 1 ... n 为节点所组成的二叉搜索树。
 *	示例:
 *	
 *	输入: 3
 *	输出:
 *	[
 *	  [1,null,3,2],
 *	  [3,2,null,1],
 *	  [3,1,null,null,2],
 *	  [2,1,3],
 *	  [1,null,2,null,3]
 *	]
 *	解释:
 *	以上的输出对应以下 5 种不同结构的二叉搜索树：
 *	
 *	   1         3     3      2      1
 *	    \       /     /      / \      \
 *	     3     2     1      1   3      2
 *	    /     /       \                 \
 *	   2     1         2                 3
 * @author ilongli
 * @email 351365415@qq.com
 */
public class GenerateTrees {
	
	public static List<Integer> printTrees(TreeNode root) {
		List<Integer> res = new ArrayList<>();
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		while (!queue.isEmpty()) {
			TreeNode poll = queue.poll();
			if (poll == null) {
				res.add(null);
				continue;
			}
			res.add(poll.val);
			queue.add(poll.left);
			queue.add(poll.right);
//			if (poll.left == null) {
//				res.add(null);
//			} else {
//				queue.add(poll.left);
//			}
//			if (poll.right == null) {
//				res.add(null);	
//			} else {
//				queue.add(poll.right);
//			}
		}
		return res;
	}

	public static void main(String[] args) {
		List<TreeNode> trees = new GenerateTrees().generateTrees(3);
		trees.stream().map(GenerateTrees::printTrees).forEach(System.out::println);
	}
	
    public List<TreeNode> generateTrees(int n) {
    	if (n == 0) return Collections.emptyList();
    	return generate_trees(1, n);
    }
    
    public List<TreeNode> generate_trees(int start, int end) {
    	List<TreeNode> all_trees = new ArrayList<TreeNode>();
    	
    	if (start == end) {
    		TreeNode node = new TreeNode(start);
    		all_trees.add(node);
    		return all_trees;
    	}
    	
    	if (start > end) {
    		all_trees.add(null);
    		return all_trees;
    	}
    	
    	// 取出树根
    	for (int i = start; i <= end; i++) {
    		// 所有的左子树列表
    		List<TreeNode> left_trees = generate_trees(start, i - 1);
    		// 所有的右子树列表
    		List<TreeNode> right_trees = generate_trees(i + 1, end);
    		
    		// 把左右子树接到树根i上
    		for (TreeNode l : left_trees) {
    			for (TreeNode r : right_trees) {
    				TreeNode current_tree = new TreeNode(i);
    				current_tree.left = l;
    				current_tree.right = r;
    				all_trees.add(current_tree);
    			}
    		}
    	}
    	return all_trees;
    }
}
