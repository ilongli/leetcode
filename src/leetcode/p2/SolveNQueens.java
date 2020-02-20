package leetcode.p2;

import java.util.ArrayList;
import java.util.List;

/**
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 *
 *	https://leetcode-cn.com/problems/n-queens/
 *
 *	上图为 8 皇后问题的一种解法。
 *	
 *	给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
 *	
 *	每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 *	
 *	示例:
 *	
 *	输入: 4
 *	输出: [
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
 *	解释: 4 皇后问题存在两个不同的解法。 
 * @author ilongli
 * @email 351365415@qq.com
 */
public class SolveNQueens {

	public static void main(String[] args) {
		System.out.println(solveNQueens(4));
	}
	
    public static List<List<String>> solveNQueens(int n) {
        
    	List<List<String>> res = new ArrayList<>(); 
    	
    	// pos[i]表示第i行皇后的位置，初始化为-1
    	int[] pos = new int[n];
    	for (int i = 0; i < n; i++) pos[i] = -1;
    	dfs(pos, res, 0);
    	return res;
    }
    
    public static void dfs(int[] pos, List<List<String>> res, int row) {
    	
    	int n = pos.length;
    	
    	if (row == n) {
    		// 生成一种解法
    		List<String> list = new ArrayList<>();
    		for (int i = 0; i < n; i++) {
    			char[] out = new char[n];
    			for (int j = 0; j < n; j++)	out[j] = '.';
    			out[pos[i]] = 'Q';
    			list.add(new String(out));
    		}
    		// 添加到结果中
    		res.add(list);
    	} else {
    		// 逐列检查
    		for (int col = 0; col < n; col++) {
    			if (isValid(pos, row, col)) {
    				// 设置改行皇后的位置
    				pos[row] = col;
    				// 下一行
    				dfs(pos, res, row + 1);
    				// 这里重置该行皇后的位置，以查询出所有的解
    				pos[row] = -1;
    			}
    		}
    	}
    }
    
    // 检查是否有效
    public static boolean isValid(int[] pos, int row, int col) {
    	// 逐行检查到row
    	for (int i = 0; i < row; i++) {
    		// 无效条件：
    		// 1. 改列已经有皇后：col == pos[i]
    		// 2. 对角线上已经有皇后：Math.abs(row - i) == Math.abs(col - pos[i])
    		if (col == pos[i] || Math.abs(row - i) == Math.abs(col - pos[i])) {
    			return false;
    		}
    	}
    	return true;
    }
}
