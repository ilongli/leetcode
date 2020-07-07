package leetcode.p3.q121_130;

import java.util.HashSet;
import java.util.Set;

/**
 * 128.最长连续序列
 * 给定一个未排序的整数数组，找出最长连续序列的长度。
 *	要求算法的时间复杂度为 O(n)。
 *	
 *	示例:
 *	
 *	输入: [100, 4, 200, 1, 3, 2]
 *	输出: 4
 *	解释: 最长连续序列是 [1, 2, 3, 4]。它的长度为 4。
 * @author ilongli
 * @email 351365415@qq.com
 */
public class LongestConsecutive {

	public static void main(String[] args) {
		
	}
	
	// 100, 4, 200, 1, 2, 3, 30, 8, 31, 34, 33, 35, 32
	
    public int longestConsecutive(int[] nums) {
        
    	Set<Integer> num_set = new HashSet<>();
    	for (int num : nums) num_set.add(num);
    	
    	int res = 0;
    	
    	for (int num : num_set) {
    		if (!num_set.contains(num - 1)) {
    			int currentNum = num;
    			int currentStreak = 1;
    			
    			while (num_set.contains(currentNum + 1)) {
    				currentNum++;
    				currentStreak++;
    			}
    			
    			res = Math.max(res, currentStreak);
    		}
    	}
    	return res;
    }
}
