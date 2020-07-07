package leetcode.p2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
 *
 *	示例 1:
 *	
 *	输入:
 *	[
 *	 [ 1, 2, 3 ],
 *	 [ 4, 5, 6 ],
 *	 [ 7, 8, 9 ]
 *	]
 *	输出: [1,2,3,6,9,8,7,4,5]
 *	示例 2:
 *	
 *	输入:
 *	[
 *	  [1, 2, 3, 4],
 *	  [5, 6, 7, 8],
 *	  [9,10,11,12]
 *	]
 *	输出: [1,2,3,4,8,12,11,10,9,5,6,7]
 * 
 * @author ilongli
 * @email 351365415@qq.com
 */
public class SpiralOrder {

	public static void main(String[] args) {
		/*		int[][] matrix = new int[][] {
					{1, 2, 3, 4},
					{5, 6, 7, 8},
					{9,10,11,12},
					{13,14,15,16},
					{17,18,19,20}
				};*/
		/*		int[][] matrix = new int[][] {
					{1},
					{2},
					{3},
					{4},
					{5}
				};*/
		int[][] matrix = new int[][] {
			{1, 2, 3},
			{4, 5, 6},
			{7, 8, 9}
		};
		
		System.out.println(spiralOrder(matrix));
	}
	
    public static List<Integer> spiralOrder(int[][] matrix) {
    	if (matrix.length == 0) return Collections.emptyList();
        List<Integer> res = new ArrayList<>();
        int m = matrix.length, n = matrix[0].length;
        int x = 0, y = 0;
    	
        while (m > 0 && n > 0) {
        	// 上
        	for (int i = x; i < (x + n); i++) {
        		res.add(matrix[y][i]);
        	}
        	
        	if (m > 1) {
        		int right = x + n - 1;
        		int bottom = y + m - 1;
        		// 右
        		for (int i = (y + 1); i < bottom; i++) {
        			res.add(matrix[i][right]);
        		}
        		// 下
        		for (int i = right; i >= x; i--) {
        			res.add(matrix[bottom][i]);
        		}
        		if (n > 1) {
        			// 左
        			for (int i = (bottom - 1); i > y; i--) {
        				res.add(matrix[i][x]);
        			}
        		}
        	}
        	x++;
        	y++;
        	m -= 2;
        	n -= 2;
        }
    	return res;
    }

}
