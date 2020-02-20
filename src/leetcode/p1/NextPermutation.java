package leetcode.p1;

import java.util.Arrays;

public class NextPermutation {
	
	public static void main(String[] args) {
		int[] nums = new int[] {1,1,5};
		nextPermutation(nums);
		Arrays.stream(nums).mapToObj(n -> n + "->").forEach(System.out::print);
	}
	
    public static void nextPermutation(int[] nums) {
    	// 从右边起找到第一对nums[i+1] > nums[i]
    	int i = nums.length - 2;
    	while (i >= 0 && nums[i + 1] <= nums[i]) i--;
    	
    	// 如果能找到，则从该位置右边的找出第一个大于该值的数进行交换
    	if (i >= 0) {
    		int j = nums.length - 1;
    		while (j >= 0 && nums[j] <= nums[i]) j--;
    		swap(nums, i,  j);
    	}
    	
    	// 重新排列右边的所有数字，按升序排序
    	reverse(nums, i + 1);
    }
    
    private static void reverse(int[] nums, int start) {
        int i = start, j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }
    
    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
