package leetcode.p2;

/**
 * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
 *	每行中的整数从左到右按升序排列。
 *	每行的第一个整数大于前一行的最后一个整数。
 *	示例 1:
 *	
 *	输入:
 *	matrix = [
 *	  [1,   3,  5,  7],
 *	  [10, 11, 16, 20],
 *	  [23, 30, 34, 50]
 *	]
 *	target = 3
 *	输出: true
 *	示例 2:
 *	
 *	输入:
 *	matrix = [
 *	  [1,   3,  5,  7],
 *	  [10, 11, 16, 20],
 *	  [23, 30, 34, 50]
 *	]
 *	target = 13
 *	输出: false
 * @author ilongli
 * @email 351365415@qq.com
 */
public class SearchMatrix {
	
	public static void main(String[] args) {
		int[][]	matrix = {
			{1, 3, 5, 7},
			{10, 11, 16, 20},
			{23, 30, 34, 50},
//			{51, 55, 60, 90},
		};
		int target = 34;
		System.out.println(searchMatrix(matrix, target));
	}
	
    public static boolean searchMatrix(int[][] matrix, int target) {
        int targetRow = 0;
    	int rowNum = matrix.length;
    	if (rowNum == 0) return false;
    	int colNum = matrix[0].length;
    	if (colNum == 0) return false;
    	
    	// 判断行
    	int start = 0, end = rowNum - 1, mid = (start + end) / 2;
    	while (start <= end) {
    		int midNum = matrix[mid][0];
    		if (midNum > target) {
    			end = mid - 1;
    		} else {
    			start = mid + 1;
    		}
    		mid = (start + end) / 2;
    	}
    	targetRow = mid;
    	
    	// 判断列
    	start = 0;
    	end = colNum - 1;
    	mid = (start + end) / 2;
    	while (start <= end) {
    		int midNum = matrix[targetRow][mid];
    		if (midNum == target) return true;
    		if (midNum > target) {
    			end = mid - 1;
    		} else {
    			start = mid + 1;
    		}
    		mid = (start + end) / 2;
    	}
    	
    	return false;
    }
}
