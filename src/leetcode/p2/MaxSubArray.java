package leetcode.p2;

/**
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 *	示例:
 *	
 *	输入: [-2,1,-3,4,-1,2,1,-5,4],
 *	输出: 6
 *	解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 *	进阶:
 *	
 *	如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 * @author ilongli
 * @email 351365415@qq.com
 */
public class MaxSubArray {

	public static void main(String[] args) {
		System.out.println(maxSubArray(new int[] {-2,1,-3,4,-1,2,1,-5,4}));
	}

	// O(n)解法
    public static int maxSubArray(int[] nums) {
    	
    	int res = nums[0];
    	int sum = 0;
    	for (int num : nums) {
    		if (sum > 0) sum += num;
    		else sum = num;
    		res = Math.max(res,  sum);
    	}
    	return res;
    }
    
    // 分治法
    public static int maxSubArray2(int[] nums) {
    	
    	
    	
    	return 0;
    }
	
}
