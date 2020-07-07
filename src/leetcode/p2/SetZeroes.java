package leetcode.p2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个 m x n 的矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。请使用原地算法。
 *	示例 1:
 *	
 *	输入: 
 *	[
 *	  [1,1,1],
 *	  [1,0,1],
 *	  [1,1,1]
 *	]
 *	输出: 
 *	[
 *	  [1,0,1],
 *	  [0,0,0],
 *	  [1,0,1]
 *	]
 *	示例 2:
 *	
 *	输入: 
 *	[
 *	  [0,1,2,0],
 *	  [3,4,5,2],
 *	  [1,3,1,5]
 *	]
 *	输出: 
 *	[
 *	  [0,0,0,0],
 *	  [0,4,5,0],
 *	  [0,3,1,0]
 *	]
 *	进阶:
 *	
 *	一个直接的解决方案是使用  O(mn) 的额外空间，但这并不是一个好的解决方案。
 *	一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
 *	你能想出一个常数空间的解决方案吗？
 * @author ilongli
 * @email 351365415@qq.com
 */
public class SetZeroes {

	public static void main(String[] args) {
		int[][] matrix = {
			{0,1,2,0},
			{3,4,5,2},
			{1,3,1,5},
		};
		setZeroes2(matrix);
		Arrays.stream(matrix).map(Arrays::toString).forEach(System.out::println);
	}
	
	public static void setZeroes2(int[][] matrix) {
		boolean isCol = false;
    	int rowNum = matrix.length;
    	int colNum = matrix[0].length;
    	
    	// 检查第一列是否要置0
    	for (int i = 0; i < rowNum; i++) {
    		if (matrix[i][0] == 0) {
    			isCol = true;
    			break;
    		}
    	}
    	
    	// 检查其他行列
    	for (int i = 0; i < rowNum; i++) {
    		for (int j = 1; j < colNum; j++) {
    			if (matrix[i][j] == 0) {
    				matrix[0][j] = 0;
    				matrix[i][0] = 0;
    			}
    		}
    	}
    	
    	// 除第一行和第一列，将其他需要置0的行列置0
    	for (int i = 1; i < rowNum; i++) {
    		for (int j = 1; j < colNum; j++) {
    			if (matrix[i][0] == 0 || matrix[0][j] == 0) {
    				matrix[i][j] = 0;
    			}
    		}
    	}
    	
    	// 第一行置0
    	if (matrix[0][0] == 0) 
    		for (int j = 0; j < colNum; j++) matrix[0][j] = 0;
    	
    	// 第一列置0
    	if (isCol)
    		for (int i = 0; i < rowNum; i++) matrix[i][0] = 0;
	}
	
    public static void setZeroes(int[][] matrix) {
    	
    	Set<Integer> zeroRow = new HashSet<>();
    	Set<Integer> zeroCol = new HashSet<>();
    	int rowNum = matrix.length;
    	int colNum = matrix[0].length;
    	
    	for (int i = 0; i < rowNum; i++) {
    		for (int j = 0; j < colNum; j++) {
    			if (matrix[i][j] == 0) {
    				zeroRow.add(i);
    				zeroCol.add(j);
    			}
    		}
    	}
    	
    	for (int row : zeroRow) 
    		for (int j = 0; j < colNum; j++) matrix[row][j] = 0;
    	
    	for (int col : zeroCol) 
    		for (int i = 0; i < rowNum; i++) matrix[i][col] = 0;
    }
}
