package leetcode.p3.q111_120;

import leetcode.common.Node;

/**
 * 117.填充每个节点的下一个右侧节点指针 II
 * 给定一个二叉树
 * 	struct Node {
 * 	  int val;
 * 	  Node *left;
 * 	  Node *right;
 * 	  Node *next;
 * 	}
 * 	填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 * 	
 * 	初始状态下，所有 next 指针都被设置为 NULL。
 * 	
 * 	示例：
 * 	https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/02/15/117_sample.png
 * 	
 * 	输入：{"$id":"1","left":{"$id":"2","left":{"$id":"3","left":null,"next":null,"right":null,"val":4},"next":null,"right":{"$id":"4","left":null,"next":null,"right":null,"val":5},"val":2},"next":null,"right":{"$id":"5","left":null,"next":null,"right":{"$id":"6","left":null,"next":null,"right":null,"val":7},"val":3},"val":1}
 * 	
 * 	输出：{"$id":"1","left":{"$id":"2","left":{"$id":"3","left":null,"next":{"$id":"4","left":null,"next":{"$id":"5","left":null,"next":null,"right":null,"val":7},"right":null,"val":5},"right":null,"val":4},"next":{"$id":"6","left":null,"next":null,"right":{"$ref":"5"},"val":3},"right":{"$ref":"4"},"val":2},"next":null,"right":{"$ref":"6"},"val":1}
 * 	
 * 	解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。
 * 	
 * 	提示：
 * 	你只能使用常量级额外空间。
 * 	使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
 * @author ilongli
 * @email 351365415@qq.com
 */
public class Connect2 {

	public static void main(String[] args) {
		Node root = new Node(1);
		Node sub = new Node(2);
		sub.left = new Node(3);
		sub.right = new Node(4);
		root.left = sub;
//		root.right = new Node(3);
		new Connect2().connect2(root);
		System.out.println(root.left.left.next.val);
	}

	/**
	 * 迭代
	 */
    public Node connect(Node root) {
        Node next = null, node = root, cur = null;
        while (node != null) {
            while (node != null) {
            	if (node.left != null || node.right != null) {
            		if (node.right == null) {
            			// 仅右为null
                		if (next == null) next = node.left;
                		if (cur != null) cur.next = node.left;
                		cur = node.left;
            		} else if (node.left == null) {
                		// 仅左为null
                		if (next == null) next = node.right;
                		if (cur != null) cur.next = node.right;
                		cur = node.right;
            		} else {
                		// 左右都不为null
                		if (next == null) next = node.left;
                		if (cur != null) cur.next = node.left;
                		node.left.next = node.right;
                		cur = node.right;
            		}
            	} 
            	node = node.next;
            }
            
            node = next;
            next = null;
        	cur = null;
        }
        
    	return root;
    }

    
    /**
     * 递归
     */
    public Node connect2(Node root) {
    	if (root == null) return root;
    	
    	if (root.left != null && root.right != null) {
    		root.left.next = root.right;
    	}
    	
    	if (root.right != null) {
    		helper(root.next, root.right);
    	} else if (root.left != null) {
    		helper(root.next, root.left);
    	}
    	
    	if (root.right != null) connect2(root.right);
    	if (root.left != null) connect2(root.left);
    	
    	return root;
    }
    
    private void helper(Node node, Node cur) {
    	if (node == null) return;
    	if (node.left != null) {
    		cur.next = node.left;
    	} else if (node.right != null) {
    		cur.next = node.right;
    	} else {
    		helper(node.next, cur);
    	}
    }
}
