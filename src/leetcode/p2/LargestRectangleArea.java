package leetcode.p2;

import java.util.Stack;

/**
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 *	求在该柱状图中，能够勾勒出来的矩形的最大面积。
 *	
 *	以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。
 *	
 *	图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。
 *	
 *	示例:
 *	输入: [2,1,5,6,2,3]
 *	输出: 10
 * https://leetcode-cn.com/problems/largest-rectangle-in-histogram/
 * @author ilongli
 * @email 351365415@qq.com
 */
public class LargestRectangleArea {

	public static void main(String[] args) {
		int[] heights = {2,1,5,6,2,3};
		System.out.println(largestRectangleArea3(heights));
	}

	/**
	 * 栈
	 * https://leetcode-cn.com/problems/largest-rectangle-in-histogram/solution/zhu-zhuang-tu-zhong-zui-da-de-ju-xing-by-leetcode/
	 * @param heights
	 * @return
	 */
	public static int largestRectangleArea3(int[] heights) {
		Stack<Integer> stack = new Stack<>();
		stack.push(-1);
		int max = 0;
		for (int i = 0 ; i < heights.length; i++) {
			while (stack.peek() != -1 && heights[stack.peek()] >= heights[i]) {
				max = Math.max(max, heights[stack.pop()] * (i - stack.peek() - 1));
			}
			stack.push(i);
		}
		while (stack.peek() != -1) {
			max = Math.max(max, heights[stack.pop()] * (heights.length - stack.peek() - 1));
		}
		return max;
	}
	
	/**
	 * 分治
	 * 最大面积的矩形存在以下三种情况：
	 * (1)从最低的柱子，两边尽可能地往两边延伸。
	 * (2)在最矮柱子左边的最大面积矩形（子问题）。
	 * (3)在最矮柱子右边的最大面积矩形（子问题）。
	 * @param heights
	 * @return
	 */
	public static int largestRectangleArea2(int[] heights) {
		return findLargest(heights, 0, heights.length - 1);
	}
	public static int findLargest(int[] heights, int start, int end) {
		if (start > end) return 0;
		int minIdx = start;
		// 找出最小值下标
		for (int i = start; i <= end; i++) {
			if (heights[minIdx] > heights[i]) minIdx = i;
		}
		return Math.max(heights[minIdx] * (end - start + 1), 
						Math.max(findLargest(heights, start, minIdx - 1), 
								 findLargest(heights, minIdx + 1, end)));
	}
	
	/**
	 * 暴力法
	 * @param heights
	 * @return
	 */
    public static int largestRectangleArea1(int[] heights) {
    	if (heights.length == 0) return 0;
        int max = 0, curMin;
        for (int i = 0; i < heights.length; i++) {
        	curMin = heights[i];
        	max = Math.max(max, curMin);
        	for (int j = i + 1; j < heights.length; j++) {
        		curMin = Math.min(curMin, heights[j]);
        		max = Math.max(max, curMin * (j + 1 - i));
        	}
        }
    	return max;
    }
}
