package leetcode.p1;

import java.util.Arrays;

/**
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 *
 *	你的算法时间复杂度必须是 O(log n) 级别。
 *	
 *	如果数组中不存在目标值，返回 [-1, -1]。
 *	
 *	示例 1:
 *	
 *	输入: nums = [5,7,7,8,8,10], target = 8
 *	输出: [3,4]
 *	示例 2:
 *	
 *	输入: nums = [5,7,7,8,8,10], target = 6
 *	输出: [-1,-1]
 *
 *	参考：https://leetcode.windliang.cc/leetCode-34-Find-First-and-Last-Position-of-Element-in-Sorted-Array.html#%E9%A2%98%E7%9B%AE%E6%8F%8F%E8%BF%B0%EF%BC%88%E4%B8%AD%E7%AD%89%E9%9A%BE%E5%BA%A6%EF%BC%89
 */
public class SearchRange {

	public static void main(String[] args) {
		int[] searchRange = searchRange(new int[] {}, 5);
		
		Arrays.stream(searchRange).forEach(System.out::println);
	}
	
	/**
	 * 基于二分法修改
	 * 两次二分法，第一次找出target的开始位置，第二次找出结束位置
	 */
    public static int[] searchRange(int[] nums, int target) {
        
    	int[] rev = { -1, -1};
    	// check null
    	if (nums.length == 0) return rev;
    	
    	int start = 0;
    	int end = nums.length - 1;
    	
    	// 第一次（二分法向左偏移寻找开始位置）
    	while (start <= end) {
    		int mid = (start + end) / 2;
    		if (target == nums[mid]) {
    			// 判断是否正好是就是开始位置
    			// 这里有边界条件处理：如果mid == 0，说明mid就一定正好就是开始位置了
    			boolean isStartTarget = mid > 0 ? (target > nums[mid - 1]) : true;
    			if (isStartTarget) {
    				rev[0] = mid;
    				break;
    			}
    			// 不结束查找，向左偏移继续寻找
    			end = mid - 1;
    		} else if (target < nums[mid]) {
    			end = mid - 1;
    		} else {
    			start = mid + 1;
    		}
    	}
    	
    	start = 0;
    	end = nums.length - 1;
    	
    	// 第二次（二分法向右偏移寻找结束位置）
    	while (start <= end) {
    		int mid = (start + end) / 2;
    		if (target == nums[mid]) {
    			// 判断是否正好是就是结束位置
    			// 这里有边界条件处理：如果mid == nums.length - 1，说明mid就一定正好就是结束位置了
    			boolean isEndTarget = mid < nums.length - 1 ? (target < nums[mid + 1]) : true;
    			if (isEndTarget) {
    				rev[1] = mid;
    				break;
    			}
    			// 不结束查找，向右偏移继续寻找
    			start = mid + 1;
    		} else if (target < nums[mid]) {
    			end = mid - 1;
    		} else {
    			start = mid + 1;
    		}
    	}
    	
    	return rev;
    }
}
