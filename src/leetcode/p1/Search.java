package leetcode.p1;

/**
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 *
 *	( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 *	
 *	搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 *	
 *	你可以假设数组中不存在重复的元素。
 *	
 *	你的算法时间复杂度必须是 O(log n) 级别。
 *	
 *	示例 1:
 *	
 *	输入: nums = [4,5,6,7,0,1,2], target = 0
 *	输出: 4
 *	示例 2:
 *	
 *	输入: nums = [4,5,6,7,0,1,2], target = 3
 *	输出: -1 
 *
 *  参考：https://leetcode-cn.com/problems/search-in-rotated-sorted-array/comments/
 *  @Rui
 */
public class Search {

	public static void main(String[] args) {
//		System.out.println(new Search().search3(new int[] {4,5,6,7,0,1,2}, 10));
		System.out.println(new Search().search3(new int[] {10}, 5));
	}
	
	
	/**
	 * nums[0] <= target <= nums[mid]
	 * 			  target <= nums[mid] < nums[0]
	 * 						nums[mid] < nums[0] <= target
	 * 以上三种情况任意一种满足时，则前面规约，否则则向后规约
	 * 容易发现，对(nums[0] <= target)，(target <= nums[mid])，(nums[mid] < nums[0])
	 * 进行三项判断即可，这三个条件不可能同时满足，只要有任意两个同时满足则向前规约，只有一个条件满足时则向后规约
	 * 此时，对这三个条件进行异或判断即可
	 */
	public int search3(int[] nums, int target) {
		if (nums.length == 0) return -1;
		int left = 0, right = nums.length - 1;
		while (left < right) {
			int mid = (left + right) / 2;
			if ((nums[0] <= target) ^ (target <= nums[mid]) ^ (nums[mid] < nums[0])) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		return nums[left] == target ? left : -1;
	}
	
	int[] nums;
	int target;
	
	// 找到旋转下标(分割点)
	public int find_rotate_index(int left, int right) {
		// 如果最左边的数字比最右边的数字小，说明旋转下标是0
		if (nums[left] < nums[right]) return 0;
		
		// 二分法找出旋转下标
		while (left <= right) {
			int mid = (left + right) / 2;
			// 当中间坐标的值大于右边的值，说明右边的下标就是旋转下标
			if (nums[mid] > nums[mid + 1]) {
				return mid + 1;
			} else {
				// 将中间的值与最左边的值作比较
				if (nums[mid] < nums[left]) {
					// 如果中间值小于最左值，说明旋转下标在左半部分
					right = mid - 1;
				} else {
					// 如果中间值大于最左值，说明旋转下标在右半部分
					left = mid + 1;
				}
			}
		}
		return 0;
	}
	
	// 二分法找出目标值
	public int search(int left, int right) {
		while (left <= right) {
			int mid = (left + right) / 2;
			if (nums[mid] == target) return mid;
			else {
				if (target < nums[mid]) right = mid - 1;
				else left = mid + 1;
			}
		}
		return -1;
	}
	
	public int search2(int[] nums, int target) {
		this.nums = nums;
		this.target = target;
		
		int n = nums.length;
		if (n == 0) return -1;
		if (n == 1) return this.nums[0] == target ? 0 : -1;
		
		// 获取旋转下标
		int rotate_index = find_rotate_index(0, n - 1);
		
		if (nums[rotate_index] == target) return rotate_index;
		if (rotate_index == 0) return search(0, n - 1);
		if (target < nums[0]) return search(rotate_index, n - 1);
		return search(0, rotate_index);
	}
	
	
	
	@Deprecated
    public static int search(int[] nums, int target) {
    	return divide(nums, 0, nums.length - 1, target);
    }
    
	@Deprecated
    private static int divide(int[] nums, int start, int end, int target) {
    	if (start > end) return -1;
    	
    	// 从中间将数组一分为二
    	int mid = (start + end) / 2;
    	if (nums[mid] == target) return mid;
    	
    	// 如果中间的数字比最右边的小，说明右边是有序数组，否则左边是有序数组
    	if (nums[mid] < nums[end]) {
    		// 如果目标数字落在右边的有序数组内，则对右边继续进行二分查询
    		if (nums[mid] < target && target <= nums[end]) {
    			return divide(nums, mid + 1, end, target);
    		} else {
    			// 否则对左边的数组继续做处理（继续一分为二）
    			return divide(nums, start, mid - 1, target);
    		}
    	} else {
    		// 同理
    		if (nums[start] <= target && target < nums[mid]) {
    			return divide(nums, start, mid - 1, target);
    		} else {
    			return divide(nums, mid + 1, end, target);
    		}
    	}
    }
}
