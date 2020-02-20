package leetcode.p2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 *	说明：解集不能包含重复的子集。
 *	
 *	示例:
 *	
 *	输入: [1,2,2]
 *	输出:
 *	[
 *	  [2],
 *	  [1],
 *	  [1,2,2],
 *	  [2,2],
 *	  [1,2],
 *	  []
 *	]
 * @author ilongli
 * @email 351365415@qq.com
 */
public class SubsetsWithDup {

	public static void main(String[] args) {
		int[] nums = {1,2,2};
		System.out.println(new SubsetsWithDup().subsetsWithDup3(nums));
	}
	
	
	/**
	 * 二进制算法
	 */
    public List<List<Integer>> subsetsWithDup3(int[] nums) {
    	Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        for (int i = 0; i < (1 << nums.length); i++) {
            List<Integer> sub = new ArrayList<Integer>();
            boolean legal = true;
            for (int j = 0; j < nums.length; j++) {
            	if (((i >> j) & 1) == 1) {
            		// 当出现重复数字的时候，判断上一位是否为0,
            		if(j > 0 && nums[j] == nums[j-1] && (i >> (j - 1) & 1) == 0) {
            			legal = false;
            			break;
            		}
            		sub.add(nums[j]);
            	}
            }
            if (legal) res.add(sub);
        }
        return res;
    }
	
	/**
	 * 递归(枚举)
	 */
	public List<List<Integer>> subsetsWithDup2(int[] nums) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		res.add(new ArrayList<Integer>());
		// 排序
		Arrays.sort(nums);
		// 保存上次新解的开始位置
		int pre_start = 0;
		for (int i = 0; i < nums.length; i++) {
			int size = res.size();
			for (int j = 0; j < size; j++) {
				// 如果出现重复数字，就跳过所有旧解
				if (i > 0 && nums[i] == nums[i - 1] && j < pre_start) continue;
				List<Integer> newSub = new ArrayList<Integer>(res.get(j));
				newSub.add(nums[i]);
				res.add(newSub);
			}
			pre_start = size;
		}
		return res;
	}
	
	
	/**
	 * 回溯
	 */
	int n, k;
	List<List<Integer>> output = new LinkedList<>();
	
    public List<List<Integer>> subsetsWithDup(int[] nums) {
    	
    	n = nums.length;
    	
    	// 排序
    	Arrays.sort(nums);
    	
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
    		// 加入这个判断条件
    		// 判断当前数字和上一个数字是否重复
    		if (i > first && nums[i] == nums[i - 1]) {
    			continue;
    		}
    		cur.add(nums[i]);
    		backtrack(i + 1, cur, nums);
    		cur.removeLast();
    	}
    }
    
}
