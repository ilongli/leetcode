package leetcode.p1;

/**
 * 编写一个程序，通过已填充的空格来解决数独问题。
 *
 *	一个数独的解法需遵循如下规则：
 *	
 *	数字 1-9 在每一行只能出现一次。
 *	数字 1-9 在每一列只能出现一次。
 *	数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 *	空白格用 '.' 表示。
 */
public class SolveSudoku {

	public static void main(String[] args) {
        char[][] board = new char[][]{
            {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
            {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
            {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
            {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
            {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
            {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
            {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
            {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
            {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
	    printBoard(board);
	    solveSudoku(board);
	    printBoard(board);
	}
	
    public static void solveSudoku(char[][] board) {
    	// 三个数组分别用于记录行、列和3x3宫内某数字是否已经被摆放
        boolean[][] row = new boolean[9][9];
        boolean[][] col = new boolean[9][9];
        boolean[][] block = new boolean[9][9];
        
        // 先将已经摆放的数放入数组内
        for (int i = 0; i < 9; i++) {
        	for (int j = 0; j < 9; j++) {
        		if (board[i][j] != '.') {
        			int num = board[i][j] - '1';
        			row[i][num] = true;
        			col[j][num] = true;
        			block[i / 3 * 3 + j / 3][num] = true;
        		}
        	}
        }
        solve(board, row, col, block, 0, 0);
    }
    
    private static boolean solve(char[][] board, boolean[][] row, boolean[][] col, boolean[][] block, int i, int j) {
    	// 找出下一个空位置
    	while (board[i][j] != '.') {
    		if (++j == 9) {
    			i++;
    			j = 0;
    		}
    		if (i == 9) return true;
    	}
    	
    	// 1~9尝试填入该位置
    	for (int num = 0; num < 9; num++) {
    		int blockIndex = i / 3 * 3 + j / 3;
    		// 判断在该位置(i,j)填入该数字(num)是否合法
    		if (!row[i][num] && !col[j][num] && !block[blockIndex][num]) {
                board[i][j] = (char) ('1' + num);
                row[i][num] = true;
                col[j][num] = true;
                block[blockIndex][num] = true;
                if (solve(board, row, col, block, i, j)) {
                	return true;
                } else {
                	// 回溯
                    row[i][num] = false;
                    col[j][num] = false;
                    block[blockIndex][num] = false;
                    board[i][j] = '.';
                }
    		}
    	}
    	return false;
    }
    
    private static void printBoard(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

}
