package leetcode.p1;

/**
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 *
 *	你可以假设数组中无重复元素。
 *	
 *	示例 1:
 *	
 *	输入: [1,3,5,6], 5
 *	输出: 2
 *	示例 2:
 *	
 *	输入: [1,3,5,6], 2
 *	输出: 1
 *	示例 3:
 *	
 *	输入: [1,3,5,6], 7
 *	输出: 4
 *	示例 4:
 *	
 *	输入: [1,3,5,6], 0
 *	输出: 0
 */
public class SearchInsert {

	public static void main(String[] args) {
		System.out.println(searchInsert(new int[] {1,3,5}, 2));
	}
	
    public static int searchInsert(int[] nums, int target) {
        // check null
    	if (nums.length == 0) return 0;
    	
    	int start = 0, end = nums.length - 1, mid = 0;
    	
    	// 二分法查找，找到直接返回
    	while (start <= end) {
    		mid = (start + end) >>> 1;
    		if (target == nums[mid]) {
    			return mid;
    		} else if (target < nums[mid]) {
    			end = mid - 1;
    		} else {
    			start = mid + 1;
    		}
    	}
    	
    	// 如果没有找到，代码就会运行到这里
    	// 判断最后mid位置上的值的大小
    	// 如果该值比target小，说明target要插入mid的右边
    	// 如果该值比target大，说明target要插在mid这里
    	if (nums[mid] < target) {
    		return mid + 1;
    	} else {
    		return mid;
    	}
    }
}

