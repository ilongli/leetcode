package leetcode.p3.q131_140;

/**
 * 137.只出现一次的数字 II
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现了三次。找出那个只出现了一次的元素。
 *	说明：
 *	
 *	你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 *	
 *	示例 1:
 *	
 *	输入: [2,2,3,2]
 *	输出: 3
 *	示例 2:
 *	
 *	输入: [0,1,0,1,0,1,99]
 *	输出: 99
 * 
 * @author ilongli
 * @email 351365415@qq.com
 */
public class SingleNumber2 {

	public static void main(String[] args) {
		
	}

	/**
	 * 位操作
	 * 参考：https://leetcode.wang/leetcode-137-Single-NumberII.html#%E8%A7%A3%E6%B3%95%E4%B8%89-%E4%BD%8D%E6%93%8D%E4%BD%9C
	 */
    public int singleNumber(int[] nums) {
    	int res = 0;
    	// 考虑每一位
    	for (int i = 0; i < 32; i++) {
    		int count = 0;
    		// 考虑每一个数
    		for (int j = 0; j < nums.length; j++) {
    			// 当前位是否是1
    			if ((nums[j] >>> i & 1) == 1) count++;
    		}
    		// 判断个数是否是3的倍数
    		if (count % 3 != 0) res = res | 1 << i;
    	}
    	
    	return res;
    }

    
    /**
     * 通用方法
     * 参考：https://leetcode.wang/leetcode-137-Single-NumberII.html#%E8%A7%A3%E6%B3%95%E5%9B%9B-%E9%80%9A%E7%94%A8%E6%96%B9%E6%B3%95
     */
    public int singleNumber2(int[] nums) {
    	int x1 = 0, x2 = 0, mask = 0;
    	for (int num : nums) {
    		x2 ^= x1 & num;
    		x1 ^= num;
    		mask = ~(x1 & x2);
    		x2 &= mask;
    		x1 &= mask;
    	}
    	return x1;
    }
    
}
