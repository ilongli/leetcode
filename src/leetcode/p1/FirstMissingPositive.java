package leetcode.p1;

import java.util.Arrays;

/**
 * 给定一个未排序的整数数组，找出其中没有出现的最小的正整数。
 *
 *	示例 1:
 *	
 *	输入: [1,2,0]
 *	输出: 3
 *	示例 2:
 *	
 *	输入: [3,4,-1,1]
 *	输出: 2
 *	示例 3:
 *	
 *	输入: [7,8,9,11,12]
 *	输出: 1
 *	说明:
 *	
 *	你的算法的时间复杂度应为O(n)，并且只能使用常数级别的空间。
 */
public class FirstMissingPositive {

	public static void main(String[] args) {
		System.out.println(firstMissingPositive(new int[] {3,4,-1,1}));
	}
	
    public static int firstMissingPositive(int[] nums) {
    	
    	// check null
    	if (nums.length == 0) return 1;
    	
    	int n = nums.length;
    	
    	for (int i = 1; i <= n; i++) {
    		int num = nums[i - 1];
    		if (num == i) continue;
    		nums[i - 1] = -1;
    		if (num > 0 && num <= n) {
    			mark(nums, num, n);
    		}
    	}
    	
    	int rev = n + 1;
    	
    	for (int j = 1; j <= n; j++) {
    		if (nums[j - 1] <= 0) {
    			rev = j;
    			break;
    		}
    	}

    	Arrays.stream(nums).mapToObj(i -> i + " ").forEach(System.out::print);
    	System.out.println();
    	
    	return rev;
    }
    
    public static void mark(int[] nums, int target, int n) {
    	int next = nums[target - 1];
    	nums[target - 1] = target;
    	if (next > 0 && next <= n && next != target) {
    		mark(nums, next, n);
    	}
    }
}
