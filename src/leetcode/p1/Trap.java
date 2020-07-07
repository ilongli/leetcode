package leetcode.p1;

/**
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 *	上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 感谢 Marcos 贡献此图。
 *	
 *	示例:
 *	
 *	输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 *	输出: 6
 */
public class Trap {

	public static void main(String[] args) {
		System.out.println(trap(new int[] {0,1,0,2,1,0,1,3,2,1,2,1}));
	}
	
	/**
	 * 时间复杂度：O(2n) 
	 */
    public static int trap(int[] height) {
        
    	// check null
    	if (height.length <= 1) return 0;
    	
    	int mark = 0, sum = 0, tem = 0, index = 0;
    	
    	// 左遍历（不包括边界条件）
    	// 从左边开始找出第一个大于0的数值，记为mark
    	while (index < height.length) {
    		if (height[index] > 0) {
    			mark = height[index++];
    			break;
    		}
    		index++;
    	}
    	// 判空（如果此时的mark为0，说明全部的高度都为0，数据无效）
    	if (mark == 0) return 0;
    	for (int i = index; i < height.length; i++) {
    		// 边界条件cur > mark
    		if (mark >= height[i]) {
    			tem += (mark - height[i]);
    		} else {
    			sum += tem;
    			tem = 0;
    			mark = height[i];
    		}
    	}
    	
    	
    	// 右遍历（包括边界条件）
    	index = height.length - 1;
    	tem = 0;
    	// 从右边开始找出第一个大于0的数值，记为mark
    	while (index >= 0) {
    		if (height[index] > 0) {
    			mark = height[index--];
    			break;
    		} 
    		index--;
    	}
    	for (int i = index; i >= 0; i--) {
    		// 边界条件cur >= mark
    		if (mark > height[i]) {
    			tem += (mark - height[i]);
    		} else {
    			sum += tem;
    			tem = 0;
    			mark = height[i];
    		}
    	}
    	
    	return sum;
    }

}
