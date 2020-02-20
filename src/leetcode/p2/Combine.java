package leetcode.p2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 *	示例:
 *	
 *	输入: n = 4, k = 2
 *	输出:
 *	[
 *	  [2,4],
 *	  [3,4],
 *	  [2,3],
 *	  [1,2],
 *	  [1,3],
 *	  [1,4],
 *	]
 * @author ilongli
 * @email 351365415@qq.com
 */
public class Combine {
	
	public static void main(String[] args) {
		System.out.println(new Combine().combine(4, 3));
	}

	public List<List<Integer>> combine2(int n, int k) {
		
		LinkedList<Integer> nums = new LinkedList<Integer>();
		for (int i = 1; i < k + 1; i++) {
			nums.add(i);
		}
		nums.add(n + 1);
		
		List<List<Integer>> output = new ArrayList<List<Integer>>();
		
		int j = 0;
		while (j < k) {
			output.add(new LinkedList<Integer>(nums.subList(0, k)));
			j = 0;
			while ((j < k) && (nums.get(j + 1) == nums.get(j) + 1)) {
				nums.set(j, j++ + 1);
			}
			nums.set(j, nums.get(j) + 1);
		}
		return output;
	}
	
	List<List<Integer>> output = new LinkedList<>();
	int n, k;
	
    public List<List<Integer>> combine(int n, int k) {
        this.n = n;
        this.k = k;
    	backtrack(1, new LinkedList<Integer>());
    	return output;
    }
    
    public void backtrack(int first, LinkedList<Integer> cur) {
    	if (cur.size() == k) output.add(new LinkedList<Integer>(cur));
    	
    	for (int i = first; i <= n && cur.size() < k; i++) {
    		cur.add(i);
    		backtrack(i + 1, cur);
    		cur.removeLast();
    	}
    }
    
}
