package leetcode.p4.q171_180;

import leetcode.common.TreeNode;

import java.util.Stack;

/**
 * 173. 二叉搜索树迭代器
 *
 * 解法二
 *
 * @author ilongli
 * @date 2021/9/15 14:29
 */
public class BSTIterator_2 {

    Stack<TreeNode> stack;
    TreeNode curNode = null;

    public BSTIterator_2(TreeNode root) {
        stack = new Stack<>();
        curNode = root;
    }

    public int next() {
        if (curNode == null) {
            TreeNode temp = stack.pop();
            curNode = temp.right;
            return temp.val;
        }

        if (curNode.left != null) {
            stack.push(curNode);
            curNode = curNode.left;
            return next();
        }

        int res = curNode.val;

        curNode = curNode.right;

        return res;
    }

    public boolean hasNext() {
        return !stack.isEmpty() || curNode != null;
    }

}
