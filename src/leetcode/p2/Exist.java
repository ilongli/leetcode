package leetcode.p2;

/**
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 *	单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 *	
 *	示例:
 *	
 *	board =
 *	[
 *	  ['A','B','C','E'],
 *	  ['S','F','C','S'],
 *	  ['A','D','E','E']
 *	]
 *	
 *	给定 word = "ABCCED", 返回 true.
 *	给定 word = "SEE", 返回 true.
 *	给定 word = "ABCB", 返回 false.
 * @author ilongli
 * @email 351365415@qq.com
 */
public class Exist {

	public static void main(String[] args) {
		/*		char[][] board = {
					{'A','B','C','E'},
					{'S','F','C','S'},
					{'A','D','E','E'},
				};*/
		char[][] board = {
			{'C','A', 'A'},
			{'A','A', 'A'},
			{'B','C', 'D'},
		};
		String word = "AAB";
		System.out.println(new Exist().exist(board, word));
	}
	
	
	private char[] wordArray;
	private char[][] board;
	private boolean[][] marked;
	private int rL = 0;
	private int cL = 0;
	
    public boolean exist(char[][] board, String word) {
    	
    	if (word == "") return false;
    	
    	char startChar = word.charAt(0);
    	this.rL = board.length;
    	this.cL = board[0].length;
    	this.wordArray = word.toCharArray();
    	this.board = board;
    	// 标记数组
    	
    	
    	// 找出开始点
    	for (int i = 0; i < rL; i++) {
    		for (int j = 0; j < cL; j++) {
    			if (board[i][j] == startChar) {
    				// 重置标记数组
    				this.marked = new boolean[rL][cL];
    				// 开始回溯
    				marked[i][j] = true;
    				if (backtrack(i, j, 0)) {
    					return true;
    				}
    			}
    		}
    	}
    	return false;
    }
    
    public boolean backtrack(int i, int j, int idx) {
    	if (idx == wordArray.length - 1 ) {
    		return true;
    	}
    	// 上:[i-1][j] 下[i+1][j-1] 左[i][j-1] 右[i][j+1]
    	// 上
    	if (i > 0 && board[i-1][j] == wordArray[idx + 1] && !marked[i-1][j]) {
    		marked[i-1][j] = true;
    		if (backtrack(i-1, j, ++idx)) {
    			return true;
    		} else {
    			marked[i-1][j] = false;
    			idx--;
    		}
    	}
    	// 下
    	if (i < (rL - 1) && board[i+1][j] == wordArray[idx + 1] && !marked[i+1][j]) {
    		marked[i+1][j] = true;
    		if (backtrack(i+1, j, ++idx)) {
    			return true;
    		} else {
    			marked[i+1][j] = false;
    			idx--;
    		}
    	}
    	// 左
    	if (j > 0 && board[i][j-1] == wordArray[idx + 1] && !marked[i][j-1]) {
    		marked[i][j-1] = true;
    		if (backtrack(i, j-1, ++idx)) {
    			return true;
    		} else {
    			marked[i][j-1] = false;
    			idx--;
    		}
    	}
    	// 右
    	if (j < (cL - 1) && board[i][j+1] == wordArray[idx + 1] && !marked[i][j+1]) {
    		marked[i][j+1] = true;
    		if (backtrack(i, j+1, ++idx)) {
    			return true;
    		} else {
    			marked[i][j+1] = false;
    			idx--;
    		}
    	}
    	return false;
    }
}
