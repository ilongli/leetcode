package leetcode.p1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的数字可以无限制重复被选取。
 * 
 * 说明：
 * 
 * 所有数字（包括 target）都是正整数。 解集不能包含重复的组合。 示例 1:
 * 
 * 输入: candidates = [2,3,6,7], target = 7, 所求解集为: [ [7], [2,2,3] ] 示例 2:
 * 
 * 输入: candidates = [2,3,5], target = 8, 所求解集为: [ [2,2,2,2], [2,3,3], [3,5] ]
 * 
 * 参考：https://leetcode-cn.com/problems/combination-sum/comments/
 */
public class CombinationSum {

	public static void main(String[] args) {
		System.out.println(combinationSum(new int[] {2,3,5}, 8));
	}

	public static List<List<Integer>> combinationSum(int[] candidates, int target) {
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
		
		if (target < candidates[num]) return;
		
		for (int i = num; i < candidates.length && candidates[i] <= target; i++) {
			List<Integer> list = new ArrayList<>(tmp);
			list.add(candidates[i]);
			find(listAll, list, candidates, target - candidates[i], i);
		}
	}
}
