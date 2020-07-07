package leetcode.p2;

import java.util.Arrays;

/**
 * 给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。
 *	说明:
 *	
 *	初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
 *	你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 *	示例:
 *	
 *	输入:
 *	nums1 = [1,2,3,0,0,0], m = 3
 *	nums2 = [2,5,6],       n = 3
 *	
 *	输出: [1,2,2,3,5,6]
 * @author ilongli
 * @email 351365415@qq.com
 */
public class Merge2 {

	public static void main(String[] args) {
		int[] nums1 = {2,3,4,0,0,0}, nums2 = {1};
		int m = 1, n = 0;
		merge(nums1, m, nums2, n);
		System.out.println(Arrays.toString(nums1));
	}
	
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
    	int idx = m + n;
    	while (n > 0) {
    		if (m == 0) {
    			nums1[--idx] = nums2[--n];
    		} else if (nums1[m - 1] > nums2[n - 1]) {
    			nums1[--idx] = nums1[--m];
    		} else {
    			nums1[--idx] = nums2[--n];
    		}
    	}
    }
}
