package leetcode.p1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 *	candidates 中的每个数字在每个组合中只能使用一次。
 *	
 *	说明：
 *	
 *	所有数字（包括目标数）都是正整数。
 *	解集不能包含重复的组合。 
 *	示例 1:
 *	
 *	输入: candidates = [10,1,2,7,6,1,5], target = 8,
 *	所求解集为:
 *	[
 *	  [1, 7],
 *	  [1, 2, 5],
 *	  [2, 6],
 *	  [1, 1, 6]
 *	]
 *	示例 2:
 *	
 *	输入: candidates = [2,5,2,1,2], target = 5,
 *	所求解集为:
 *	[
 *	  [1,2,2],
 *	  [5]
 *	]
 */
public class CombinationSum2 {

	public static void main(String[] args) {
		System.out.println(combinationSum2(new int[] {2,5,2,1,2}, 5));
	}

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
		List<List<Integer>> listAll = new ArrayList<List<Integer>>();
		List<Integer> list = new ArrayList<Integer>();
		// 排序
		Arrays.sort(candidates);
		find(listAll, list, candidates, target, 0);
		return listAll;
    }
    
	public static void find(List<List<Integer>> listAll, List<Integer> tmp, int[] candidates, int target, int num) {
		if (target == 0) {
			listAll.add(tmp);
			return;
		}
		
		if(num >= candidates.length || target < candidates[num]) return;

		for (int i = num; i < candidates.length && candidates[i] <= target; i++) {
			// 跳过重复数字
			if (i > num && candidates[i] == candidates[i - 1]) continue;
			List<Integer> list = new ArrayList<>(tmp);
			list.add(candidates[i]);
			find(listAll, list, candidates, target - candidates[i], i + 1);
		}
	}
}
