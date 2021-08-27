package leetcode.p4.q151_160;

import leetcode.common.TreeNode;

import java.util.Objects;

/**
 * 156. 上下翻转二叉树（DFS）
 * 给定一个二叉树，其中所有的右节点要么是具有兄弟节点（拥有相同父节点的左节点）的叶节点，要么为空
 * 将此二叉树上下翻转并将它变成一棵树， 原来的右节点将转换成左叶节点。返回新的根。
 *
 * 例子:
 *
 * 输入: [1,2,3,4,5]
 *
 *     1
 *    / \
 *   2   3
 *  / \
 * 4   5
 *
 * 输出: 返回二叉树的根 [4,5,2,#,#,3,1]
 *
 *    4
 *   / \
 *  5   2
 *     / \
 *    3   1
 * 说明:
 *
 * 对 [4,5,2,#,#,3,1] 感到困惑? 下面详细介绍请查看 二叉树是如何被序列化的。
 *
 * 二叉树的序列化遵循层次遍历规则，当没有节点存在时，'#' 表示路径终止符。
 *
 * 这里有一个例子:
 *
 *    1
 *   / \
 *  2   3
 *     /
 *    4
 *     \
 *      5
 * 上面的二叉树则被序列化为 [1,2,3,#,#,4,#,#,5].
 *
 * @author ilongli
 * @date 2021/8/25 9:47
 */
public class UpsideDownBinaryTree {

    public static void main(String[] args) {

    }


    public TreeNode upsideDownBinaryTree(TreeNode root) {

        if (Objects.isNull(root) ||
           (Objects.isNull(root.left) && Objects.isNull(root.right))) {
            return root;
        }

        // 递归，一直找到最左边的节点，然后开始翻转
        TreeNode _root = upsideDownBinaryTree(root.left);

        // 翻转的操作只是对root进行操作，每次的递归_root值都是最左边的节点
        root.left.left = root.right;
        root.left.right = root;
        root.left = null;
        root.right = null;

        // 这里返回的一直都是最左边的节点
        return _root;
    }

}
