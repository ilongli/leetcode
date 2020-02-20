package leetcode.p2;

import java.util.Stack;

/**
 * 给定一个仅包含 0 和 1 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
 *	示例:
 *	
 *	输入:
 *	[
 *	  ["1","0","1","0","0"],
 *	  ["1","0","1","1","1"],
 *	  ["1","1","1","1","1"],
 *	  ["1","0","0","1","0"]
 *	]
 *	输出: 6
 * @author ilongli
 * @email 351365415@qq.com
 */
public class MaximalRectangle {

	public static void main(String[] args) {
		
	}
	
    /**
     * @param matrix
     * @return
     */
    public static int maximalRectangle(char[][] matrix) {
    	int m = matrix.length;
    	if (m == 0) return 0;
    	int n = matrix[0].length, max = 0;
    	int[] heights = new int[n];
    	for (int i = 0; i < m; i++) {
    		for (int j = 0; j < n; j++) {
    			if (matrix[i][j] == '1') {
    				heights[j]++;
    			} else {
    				heights[j] = 0;
    			}
    		}
    		max = Math.max(max, largestRectangleArea(heights));
    	}
    	return max;
    }
    
    /**
     * @see LargestRectangleArea#largestRectangleArea3(int[])
     * @param heights
     * @return
     */
	public static int largestRectangleArea(int[] heights) {
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
}
