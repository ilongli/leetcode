package leetcode.p2;

/**
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 *	
 *	机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 *	
 *	问总共有多少条不同的路径？
 *
 *	说明：m 和 n 的值均不超过 100。
 *	示例 1:
 *	
 *	输入: m = 3, n = 2
 *	输出: 3
 *	解释:
 *	从左上角开始，总共有 3 条路径可以到达右下角。
 *	1. 向右 -> 向右 -> 向下
 *	2. 向右 -> 向下 -> 向右
 *	3. 向下 -> 向右 -> 向右
 *	示例 2:
 *	
 *	输入: m = 7, n = 3
 *	输出: 28
 * @author ilongli
 * @email 351365415@qq.com
 */
public class UniquePaths {

	public static void main(String[] args) {
		
		System.out.println(uniquePaths2(3, 3));
	}
	
	
	// 动态规划
	public static int uniquePaths2(int m, int n) {
		int[][] dp = new int[m][n];
		
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (i == 0 || j == 0) {
					dp[i][j] = 1;
				} else {
					dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
				}
			}
		}
		return dp[m - 1][n - 1];
	}
	
	
	// 递归(超时)
    public static int uniquePaths(int m, int n) {
    	// x为当前列，y为当前行
    	int x = 1, y = 1;
    	return go(x, y, m, n);
    }
    
    public static int go(int x, int y, int m, int n) {
    	
    	if (y == n || x == m) {
    		return 1;
    	} else {
    		return go(x + 1, y, m, n) + go(x, y + 1, m, n);
    	}
    }
}
