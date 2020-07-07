package leetcode.p3.q131_140;

import java.util.HashMap;
import java.util.Map;

/**
 * 138.复制带随机指针的链表
 * 给定一个链表，每个节点包含一个额外增加的随机指针，该指针可以指向链表中的任何节点或空节点。
 *	要求返回这个链表的深拷贝。 
 *	
 *	示例：https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/02/23/1470150906153-2yxeznm.png
 *	
 *	输入：
 *	{"$id":"1","next":{"$id":"2","next":null,"random":{"$ref":"2"},"val":2},"random":{"$ref":"2"},"val":1}
 *	
 *	解释：
 *	节点 1 的值是 1，它的下一个指针和随机指针都指向节点 2 。
 *	节点 2 的值是 2，它的下一个指针指向 null，随机指针指向它自己。
 *	 
 *	
 *	提示：
 *	
 *	你必须返回给定头的拷贝作为对克隆列表的引用。
 * 
 * @author ilongli
 * @email 351365415@qq.com
 */
public class CopyRandomList {

	public static void main(String[] args) {
		CopyRandomList cr = new CopyRandomList();
		Node n1 = cr.new Node();
		n1.val = 1;
		Node n2 = cr.new Node();
		n2.val = 2;
		n1.next = n2;
		n1.random = n2;
		n2.random = n2;
		
		Node res = cr.copyRandomList(n1);
		System.out.println(res);
	}
	
	
	/**
	 * 回溯 
	 */
    public Node copyRandomList(Node head) {
        if (head == null) return null;
    	Map<Integer, Node> map = new HashMap<>();
    	
    	return helper(head, map);
    }
    
    private Node helper(Node node, Map<Integer, Node> map) {
    	if (node == null) return null;
    	if (map.containsKey(node.val)) {
    		return map.get(node.val);
    	}
    	
    	Node n = new Node();
    	n.val = node.val;
    	map.put(node.val, n);
    	
    	n.next = helper(node.next, map);
    	n.random = helper(node.random, map);
    	
    	return n;
    }
    
    
    /**
     * 迭代 
     */
    Map<Node, Node> visited = new HashMap<>();
    public Node copyRandomList2(Node head) {
    	if (head == null) return null;
    	
    	Node oldNode = head;
    	
    	Node newNode = new Node(oldNode.val, null, null);
    	this.visited.put(oldNode, newNode);
    	
    	while (oldNode != null) {
    		newNode.random = this.getCloneNode(oldNode.random);
    		newNode.next = this.getCloneNode(oldNode.next);
    		
    		oldNode = oldNode.next;
    		newNode = newNode.next;
    	}
    	
    	return this.visited.get(head);
    }
    
    public Node getCloneNode(Node node) {
    	if (node != null) {
    		if (this.visited.containsKey(node)) {
    			return this.visited.get(node);
    		} else {
    			this.visited.put(node, new Node(node.val, null, null));
    			return this.visited.get(node);
    		}
    	}
    	return null;
    }
    
    
    /**
     * O(1)空间复杂度的迭代
     */
    public Node copyRandomList3(Node head) {
    	
    	if (head == null) return null;
    	
    	// 克隆每一个节点到其.next
    	Node ptr = head;
    	while (ptr != null) {
    		Node newNode = new Node(ptr.val, null, null);
    		
    		newNode.next = ptr.next;
    		ptr.next = newNode;
    		ptr = newNode.next;
    	}
    	
    	ptr = head;
    	
    	// 处理random
    	while (ptr != null) {
    		ptr.next.random = (ptr.random == null) ? null : ptr.random.next;
    		ptr = ptr.next.next;
    	}
    	
    	// 处理next
    	Node ptr_old_list = head;
    	Node ptr_new_list = head.next;
    	Node head_new = head.next;
    	while (ptr_old_list != null) {
    		ptr_old_list.next = ptr_old_list.next.next;
    		ptr_new_list.next = (ptr_new_list.next == null) ? null : ptr_new_list.next.next;
    		ptr_old_list = ptr_old_list.next;
    		ptr_new_list = ptr_new_list.next;
    	}
    	
    	return head_new;
    }
    
    
    
    
    class Node {
    	public int val;
    	public Node next;
    	public Node random;
    	
    	public Node() {}
    	
    	public Node(int _val,Node _next,Node _random) {
    		val = _val;
    		next = _next;
    		random = _random;
    	}
    };
}

