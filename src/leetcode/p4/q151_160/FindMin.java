package leetcode.p4.q151_160;

/**
 * 153.寻找旋转排序数组中的最小值
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * <p>
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * <p>
 * 请找出其中最小的元素。
 * <p>
 * 你可以假设数组中不存在重复元素。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,4,5,1,2]
 * 输出: 1
 * 示例 2:
 * <p>
 * 输入: [4,5,6,7,0,1,2]
 * 输出: 0
 *
 * @author ilongli
 * @date 2020/7/10 11:34
 */
public class FindMin {

    public static void main(String[] args) {
        int[] nums = {9, 7, 4, 3, 2};
        FindMin findMin = new FindMin();
        System.out.println(findMin.findMin(nums));
    }

    public int findMin2(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int middle = (left + right) / 2;
            if (nums[middle] < nums[right]) {
                // middle可能是最小值
                right = middle;
            } else {
                // middle肯定不是最小值
                left = middle + 1;
            }
        }
        return nums[left];
    }

    public int findMin(int[] nums) {

        if (nums.length == 0) return 0;

        int left = 0, right = nums.length - 1, mid = 0;

        while (left < right) {
            // 取中间的位置
            mid = (left + right) / 2;

            // 四种情况
            if (nums[left] >= nums[mid]) {
                if (nums[mid] < nums[right]) {
                    // 降->升
                    // 最小元素在[left, mid]
                    right = mid;
                } else {
                    // 此时[left, right]为降序排序，nums[right]即为最小值
                    return nums[right];
                }
            } else {
                if (nums[mid] > nums[right]) {
                    // 升->降
                    // 最小元素在[mid, right]
                    left = mid;
                } else {
                    // 此时[left, right]为升序排序，nums[left]即为最小值
                    return nums[left];
                }
            }
        }

        return nums[left];
    }

}
