package leetcode.p1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 *
 *	示例:
 *	
 *	输入: [1,1,2]
 *	输出:
 *	[
 *	  [1,1,2],
 *	  [1,2,1],
 *	  [2,1,1]
 *	]
 */
public class PermuteUnique {

	public static void main(String[] args) {
		// 1 2 2 
		// 1
		// 12 21
		// 212 122 221


		
		
		System.out.println(permuteUnique(new int[] {1,2,1,2}));
	}
	
    public static List<List<Integer>> permuteUnique(int[] nums) {
    	
    	// check null 
    	if (nums.length == 0) return Collections.emptyList();
    	
    	List<List<Integer>> rev = new ArrayList<>();
    	
    	List<Integer> list = new ArrayList<>();
    	list.add(nums[0]);
    	insert(rev, nums, 1, list);
    	
    	return rev;
    }
    
    public static void insert(List<List<Integer>> rev, int[] nums, int idx, List<Integer> list) {
    	if (idx == nums.length) {
    		rev.add(list);
    		return;
    	}

    	// 拿出下一个数字
    	int num = nums[idx++];
    	// 在每个空隙中插入下一个数字
    	for (int i = 0; i <= list.size(); i++) {
    		// 跳过重复数字
    		if (i > 0 && num == list.get(i-1)) break;
    		
    		List<Integer> _list = new ArrayList<>(list); 
    		_list.add(i, num);
    		insert(rev, nums, idx, _list);
    	}
    }

}
