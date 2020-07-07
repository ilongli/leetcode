package leetcode.p2;

/**
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 *	( 例如，数组 [0,0,1,2,2,5,6] 可能变为 [2,5,6,0,0,1,2] )。
 *	
 *	编写一个函数来判断给定的目标值是否存在于数组中。若存在返回 true，否则返回 false。
 *	
 *	示例 1:
 *	
 *	输入: nums = [2,5,6,0,0,1,2], target = 0
 *	输出: true
 *	示例 2:
 *	
 *	输入: nums = [2,5,6,0,0,1,2], target = 3
 *	输出: false
 *	进阶:
 *	
 *	这是 搜索旋转排序数组 的延伸题目，本题中的 nums  可能包含重复元素。
 *	这会影响到程序的时间复杂度吗？会有怎样的影响，为什么？
 * @author ilongli
 * @email 351365415@qq.com
 */
public class Search {

	public static void main(String[] args) {
		int[] nums = {2,5,6,0,0,1,2};
		int target = 10;
		System.out.println(search(nums, target));
	}
	
    public static boolean search(int[] nums, int target) {
		if (nums.length == 0) return false;
		int left = 0, right = nums.length - 1;
		while (left < right) {
			int mid = (left + right) / 2;
			if (nums[mid] == nums[right]) {
				--right;
			} else if ((nums[0] <= target) ^ (target <= nums[mid]) ^ (nums[mid] < nums[0])) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		return nums[left] == target;
    }
}
