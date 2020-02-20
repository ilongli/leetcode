package leetcode.p3.q131_140;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * 133.克隆图
 * 给定无向连通图中一个节点的引用，返回该图的深拷贝（克隆）。图中的每个节点都包含它的值 val（Int） 和其邻居的列表（list[Node]）。
 *	示例：https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/02/23/113_sample.png
 *
 *	输入：
 *	{"$id":"1","neighbors":[{"$id":"2","neighbors":[{"$ref":"1"},{"$id":"3","neighbors":[{"$ref":"2"},{"$id":"4","neighbors":[{"$ref":"3"},{"$ref":"1"}],"val":4}],"val":3}],"val":2},{"$ref":"4"}],"val":1}
 *	
 *	解释：
 *	节点 1 的值是 1，它有两个邻居：节点 2 和 4 。
 *	节点 2 的值是 2，它有两个邻居：节点 1 和 3 。
 *	节点 3 的值是 3，它有两个邻居：节点 2 和 4 。
 *	节点 4 的值是 4，它有两个邻居：节点 1 和 3 。
 *	 
 *	
 *	提示：
 *	
 *	节点数介于 1 到 100 之间。
 *	无向图是一个简单图，这意味着图中没有重复的边，也没有自环。
 *	由于图是无向的，如果节点 p 是节点 q 的邻居，那么节点 q 也必须是节点 p 的邻居。
 *	必须将给定节点的拷贝作为对克隆图的引用返回。
 * @author ilongli
 * @email 351365415@qq.com
 */
public class CloneGraph {

	public static void main(String[] args) {

	}

	
	/**
	 * 1.BFS
	 */
    public Node cloneGraph(Node node) {
        if (node == null) return null;
    	Queue<Node> queue = new LinkedList<>();
        Map<Integer, Node> map = new HashMap<>();
        queue.offer(node);
    	
        Node n = new Node();
        n.val = node.val;
        n.neighbors = new ArrayList<Node>();
    	map.put(n.val, n);
    	
    	while (!queue.isEmpty()) {
    		Node cur = queue.poll();
    		for (Node temp : cur.neighbors) {
    			if (!map.containsKey(temp.val)) {
    				n = new Node();
    				n.val = temp.val;
    				n.neighbors = new ArrayList<Node>();
    				map.put(n.val, n);
    				queue.offer(temp);
    			}
    			map.get(cur.val).neighbors.add(map.get(temp.val));
    		}
    	}
    	return map.get(node.val);
    }
    
    /**
     * 2.DFS
     */
    public Node cloneGraph2(Node node) {
    	if (node == null) return null;
    	Map<Integer, Node> map = new HashMap<>();
    	
    	return cloneGrapthHepler(node, map);
    }
    
    private Node cloneGrapthHepler(Node node, Map<Integer, Node> map) {
    	if (map.containsKey(node.val)) {
    		return map.get(node.val);
    	}
    	
    	Node n = new Node();
    	n.val = node.val;
    	n.neighbors = new ArrayList<Node>();
    	map.put(node.val, n);
    	for (Node temp : node.neighbors) {
    		n.neighbors.add(cloneGrapthHepler(temp, map));
    	}
    	
    	return n;
    }
}

class Node {
	public int val;
	public List<Node> neighbors;

	public Node() {
	}

	public Node(int _val, List<Node> _neighbors) {
		val = _val;
		neighbors = _neighbors;
	}
}

