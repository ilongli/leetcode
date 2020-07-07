package leetcode.p2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 *	说明：解集不能包含重复的子集。
 *	
 *	示例:
 *	
 *	输入: nums = [1,2,3]
 *	输出:
 *	[
 *	  [3],
 *	  [1],
 *	  [2],
 *	  [1,2,3],
 *	  [1,3],
 *	  [2,3],
 *	  [1,2],
 *	  []
 *	]
 * @author ilongli
 * @email 351365415@qq.com
 */
public class Subsets {

	public static void main(String[] args) {
		
		int[] nums = {1,2,3};
		
		System.out.println(new Subsets().subsets2(nums));
		
	}
	
	/**
	 * 循环枚举
	 * 一开始只有一个空集
	 * 遍历nums，将之前的子集都追加这个num元素，就是新增的子集
	 */
	public List<List<Integer>> subsets3(int[] nums) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		res.add(new ArrayList<Integer>());
		for (Integer n : nums) {
			int size = res.size();
			for (int i = 0; i < size; i++) {
				List<Integer> newSub = new ArrayList<Integer>(res.get(i));
				newSub.add(n);
				res.add(newSub);
			}
		}
		return res;
	}
	
	
	/**
	 * 二进制算法 
	 * 集合的每个元素都有选和不选，可以用二进制数表示
	 * 如：[1,2,3]
	 * 000	->  []
	 * 001	-> 	[1]
	 * 010	->	[2]
	 * 011	->	[1,2]
	 * 100	->	[3]
	 * 101	->	[1,3]
	 * 110	->	[2,3]
	 * 111	-> 	[1,2,3]
	 */
    public List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        for (int i = 0; i < (1 << nums.length); i++) {
            List<Integer> sub = new ArrayList<Integer>();
            for (int j = 0; j < nums.length; j++)
                if (((i >> j) & 1) == 1) sub.add(nums[j]);
            res.add(sub);
        }
        return res;
    }
	
    
    /**
     * 回溯算法
     */
	int n, k;
	List<List<Integer>> output = new LinkedList<>();
	
    public List<List<Integer>> subsets(int[] nums) {
    	
    	n = nums.length;
    	
    	while (k <= n) {
    		backtrack(0, new LinkedList<Integer>(), nums);
    		k++;
    	}
    	
    	return output;
    }
    
    
    public void backtrack(int first, LinkedList<Integer> cur, int[] nums) {
    	if (cur.size() == k) {
    		output.add(new LinkedList<Integer>(cur));
    	}
    	
    	for (int i = first; i < n && cur.size() < k; i++) {
    		cur.add(nums[i]);
    		backtrack(i + 1, cur, nums);
    		cur.removeLast();
    	}
    }
}
