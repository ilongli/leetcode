package leetcode.p3.q131_140;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 	136.只出现一次的数字
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 *	说明：
 *	
 *	你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 *	
 *	示例 1:
 *	
 *	输入: [2,2,1]
 *	输出: 1
 *	示例 2:
 *	
 *	输入: [4,1,2,1,2]
 *	输出: 4
 * 
 * 
 * 
 * @author ilongli
 * @email 351365415@qq.com
 */
public class SingleNumber {

	public static void main(String[] args) {
		int[] nums = {4,1,2,1,2};
		System.out.println(new SingleNumber().singleNumber(nums));
	}

	
    public int singleNumber(int[] nums) {
        
    	Map<Integer, Integer> numMap = new HashMap<>();
    	
    	for (int num : nums) {
    		numMap.merge(num, 1, (o, n) -> o + n);
		}
    	
    	Set<Map.Entry<Integer, Integer>> entrySet = numMap.entrySet();
    	for (Map.Entry<Integer, Integer> entry : entrySet) {
			if (entry.getValue() == 1) return entry.getKey();
		}
    	
    	return 0;
    }
    
    public int singleNumber2(int[] nums) {
    	Set<Integer> set1 = new HashSet<>(), set2 = new HashSet<>();
    	
    	for (int num : nums) {
    		if (set1.contains(num)) {
    			set2.add(num);
    		}
    		set1.add(num);
    	}
    	
    	for (int num : nums) {
    		if (!set2.contains(num)) return num;
    	}
    	
    	return 0;
    }

    /**
     * 数学推导
     * 例如：出现的数字为：a b a b c c d
     * 出现过的数字就为：a b c d
     * 那么：2 * (a + b + c + d) - (a + b + a + b + c + c + d) = d
     * 即为所求
     */
    public int singleNumber3(int[] nums) {
    	HashSet<Integer> set = new HashSet<>();
    	int sum = 0;
    	for (int i = 0; i < nums.length; i++) {
    		set.add(nums[i]);
    		sum += nums[i];
    	}
    	int sumMul = 0;
    	for (int n : set) sumMul += n;
    	return sumMul * 2 - sum;
    }

    /**
     * 异或
     */
    public int singleNumber4(int[] nums) {
    	int res = 0;
    	for (int num : nums) {
    		res ^= num;
    	}
    	return res;
    }
}
