package leetcode.p1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 给定一个没有重复数字的序列，返回其所有可能的全排列。
 *
 *	示例:
 *	
 *	输入: [1,2,3]
 *	输出:
 *	[
 *	  [1,2,3],
 *	  [1,3,2],
 *	  [2,1,3],
 *	  [2,3,1],
 *	  [3,1,2],
 *	  [3,2,1]
 *	]
 */
public class Permute {

	public static void main(String[] args) {
		System.out.println(permute(new int[] {}));
	}
	
    public static List<List<Integer>> permute(int[] nums) {
     
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
    	// 在每个空隙插入下一个数字
    	for (int i = 0; i <= list.size(); i++) {
    		List<Integer> _list = new ArrayList<>(list); 
    		_list.add(i, num);
    		insert(rev, nums, idx, _list);
    	}
    }
}
