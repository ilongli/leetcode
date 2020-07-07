package leetcode.p1;

import java.util.Arrays;

/**
 * 给定一个 n × n 的二维矩阵表示一个图像。
 *
 *	将图像顺时针旋转 90 度。
 *	
 *	说明：
 *	
 *	你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。
 *	
 *	示例 1:
 *	
 *	给定 matrix = 
 *	[
 *	  [1,2,3],
 *	  [4,5,6],
 *	  [7,8,9]
 *	],
 *	
 *	原地旋转输入矩阵，使其变为:
 *	[
 *	  [7,4,1],
 *	  [8,5,2],
 *	  [9,6,3]
 *	]
 *	示例 2:
 *	
 *	给定 matrix =
 *	[
 *	  [ 5, 1, 9,11],
 *	  [ 2, 4, 8,10],
 *	  [13, 3, 6, 7],
 *	  [15,14,12,16]
 *	], 
 *	
 *	原地旋转输入矩阵，使其变为:
 *	[
 *	  [15,13, 2, 5],
 *	  [14, 3, 4, 1],
 *	  [12, 6, 8, 9],
 *	  [16, 7,10,11]
 *	]
 */
public class Rotate {

	public static void main(String[] args) {

		/**
		 * 1 2 3
		 * 4 5 6
		 * 7 8 9
		 * 
		 * 7 4 1
		 * 8 5 2
		 * 9 6 3
		 */
		/*		int[][] matrix = {
		        	  {1, 2, 3, 4, 5, 6},
		        	  {7, 8, 9, 10, 11 ,12},
		        	  {13, 14, 15, 16, 17, 18},
		        	  {19, 20, 21, 22, 23, 24},
		        	  {25, 26, 27, 28, 29, 30},
		        	  {31, 32, 33, 34, 35, 36}
							};*/
		int[][] matrix = {
				{1, 2, 3},
				{4, 5, 6},
				{7, 8, 9}
		};
		rotate(matrix);
		
		Arrays.stream(matrix).forEach(m -> {
			System.out.println(Arrays.toString(m));
		});
	}
	
    public static void rotate(int[][] matrix) {
    	
    	int n = matrix.length - 1;
    	
    	// check null
    	if (n < 1) return;
        
    	// 从外往内一圈一圈地旋转每个数字
    	for (int i = 0; i < (n + 1) / 2; i++) {
        	// 开始旋转数字
        	for (int j = i; j < n - i; j++) {
        		repos(matrix, matrix[i][j], j, n - i, i, j, n);
        	}
    	}
    }
    
    public static void repos(int[][] matrix, int num, int x, int y, int endx, int endy, int n) {
    	
    	// 拿出下一个要旋转的数字
    	int nextnum = matrix[x][y];
    	// 旋转数字
    	matrix[x][y] = num;
    	// 旋转结束
    	if (x != endx || y != endy) {
    		// 旋转下一个数字
    		repos(matrix, nextnum, y, n - x, endx, endy, n);
    	}
    }

    
}
