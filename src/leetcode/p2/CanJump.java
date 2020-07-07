package leetcode.p2;

/**
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 *
 *	数组中的每个元素代表你在该位置可以跳跃的最大长度。
 *	
 *	判断你是否能够到达最后一个位置。
 *	
 *	示例 1:
 *	
 *	输入: [2,3,1,1,4]
 *	输出: true
 *	解释: 从位置 0 到 1 跳 1 步, 然后跳 3 步到达最后一个位置。
 *	示例 2:
 *	
 *	输入: [3,2,1,0,4]
 *	输出: false
 *	解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
 * 
 * @author ilongli
 * @email 351365415@qq.com
 */
public class CanJump {

	public static void main(String[] args) {
		System.out.println(canJump(new int[] {3,2,1,0,4}));
	}
	
    public static boolean canJump(int[] nums) {
        int n = nums.length - 1;
    	if (n < 0) return false;
    	
    	int idx = 0;
    	while (nums[idx] + idx < n) {
    		int num = nums[idx];
    		if (num == 0) return false;
    		int next = 0, maxJump = 0;
    		for (int i = idx + 1; i <= idx + num; i++) {
    			if (nums[i] + i >= maxJump) {
    				maxJump = nums[i] + i;
    				next = i;
    			}
    		}
    		idx = next;
    	}
    	return true;
    }

    public static boolean canJump2(int[] nums) {
    	// 0, 1
    	// index = 0, min = 1
    	int index = nums.length - 2, min = nums.length - 1;
    	while (index >= 0) {
    		if (index + nums[index] >= min) min = index;
    		index--;
    	}
    	return min == 0;
    }
}
