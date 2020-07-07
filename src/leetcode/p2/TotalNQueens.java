package leetcode.p2;

/**
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 *
 *  https://leetcode-cn.com/problems/n-queens-ii/
 *  
 *	上图为 8 皇后问题的一种解法。
 *	
 *	给定一个整数 n，返回 n 皇后不同的解决方案的数量。
 *	
 *	示例:
 *	
 *	输入: 4
 *	输出: 2
 *	解释: 4 皇后问题存在如下两个不同的解法。
 *	[
 *	 [".Q..",  // 解法 1
 *	  "...Q",
 *	  "Q...",
 *	  "..Q."],
 *	
 *	 ["..Q.",  // 解法 2
 *	  "Q...",
 *	  "...Q",
 *	  ".Q.."]
 *	] 
 * 
 * @author ilongli
 * @email 351365415@qq.com
 */
public class TotalNQueens {
	
	private static int total = 0;

	public static void main(String[] args) {
		System.out.println(totalNQueens(14));
	}
	
    public static int totalNQueens(int n) {
    	int[] pos = new int[n];
    	for (int i = 0; i < n; i++) pos[i] = -1;
    	dfs(pos, 0);
    	return total;
    }
    
    public static void dfs(int[] pos, int row) {
    	int n = pos.length;
    	if (row == n) {
    		total++;
    	} else {
    		for (int col = 0; col < n; col++) {
    			if (isValid(pos, row, col)) {
    				pos[row] = col;
    				dfs(pos, row + 1);
    				pos[row] = -1;
    			}
    		}
    	}
    }
    
    public static boolean isValid(int[] pos, int row, int col) {
    	for (int i = 0; i < row; i++) {
    		if (col == pos[i] || Math.abs(row - i) == Math.abs(col - pos[i])) {
    			return false;
    		}
    	}
    	return true;
    }

}
