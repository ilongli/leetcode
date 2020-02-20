package leetcode.p2;

import java.util.Arrays;

/**
 * 给定一个正整数 n，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。
 *
 * 示例:
 *
 * 输入: 3
 * 输出:
 * [
 *  [ 1, 2, 3 ],
 *  [ 8, 9, 4 ],
 *  [ 7, 6, 5 ]
 * ]
 * @author ilongli
 * @email 351365415@qq.com
 */
public class GenerateMatrix {

	public static void main(String[] args) {
		
		int[][] res = generateMatrix(6);
		Arrays.stream(res).map(Arrays::toString).forEach(System.out::println);
	}
	
    public static int[][] generateMatrix(int n) {
        
    	int[][] res = new int[n][n];
    	
    	int curRow = 0, endRow = n / 2 + n % 2, curCol = 0, count = 1;
    	
    	for (; curRow <= endRow; curRow++, curCol++) {
    		int lastRow = n - curRow - 1;
    		int lastRol = n - curCol - 1;
    		// 上
    		for (int i = curCol; i <= lastRol; i++) {
    			res[curRow][i] = count++;
    		}
    		
    		// 如果只剩一行，则直接跳出
    		if (curRow == lastRow) continue;
    		
    		// 右
    		if (lastRow - curRow >= 2) {
    			for (int i = curRow + 1; i <= lastRow - 1; i++) {
    				res[i][lastRol] = count++;
    			}
    		}
    		
    		// 下
    		for (int i = lastRol; i >= curCol; i--) {
    			res[lastRow][i] = count++;
    		}
    		
    		// 左
    		if (lastRow - curRow >= 2) {
    			for (int i = lastRow - 1; i >= curRow + 1; i--) {
    				res[i][curCol] = count++;
    			}
    		}
    	}
    	
    	return res;
    }
    
    public int[][] generateMatrix2(int n) {
        int[][] arr = new int[n][n];
        int c = 1, j = 0;
        while (c <= n * n) {
        
            for (int i = j; i < n - j; i++)
                arr[j][i] = c++;
            for (int i = j + 1; i < n - j; i++)
                arr[i][n - j - 1] = c++;
            for (int i = n - j - 2; i >= j; i--)
                arr[n - j - 1][i] = c++;
            for (int i = n -j - 2; i > j; i--)
                arr[i][j] = c++;

            j++;
        }

        return arr;
    }
}
