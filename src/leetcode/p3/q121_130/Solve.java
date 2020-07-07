package leetcode.p3.q121_130;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 130.被围绕的区域
 * 
 * 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 * 
 * 示例:
 * 
 * X X X X 
 * X O O X 
 * X X O X 
 * X O X X 
 * 运行你的函数后，矩阵变为：
 * 
 * X X X X 
 * X X X X 
 * X X X X 
 * X O X X 
 * 解释:
 * 
 * 被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。
 * 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 * 
 * @author ilongli
 * @email 351365415@qq.com
 */
public class Solve {

	public static void main(String[] args) {
		
//		char[][] board = {
//					{'O','O','O','O','X','X'},
//					{'O','O','O','O','O','O'},
//					{'O','X','O','X','O','O'},
//					{'O','X','O','O','X','O'},
//					{'O','X','O','X','O','O'},
//					{'O','X','O','O','O','O'}
//				};
//		char[][] board = {
//				{'X','X','X','X','X','X'},
//				{'X','O','O','X','O','O'},
//				{'X','O','O','X','O','X'},
//				{'X','X','O','X','O','O'},
//				{'X','O','X','X','X','X'}
//		};
		char[][] board = {
				{'X','X','X','X'},
				{'X','X','O','O'},
				{'O','O','X','X'},
				{'O','O','X','X'}
		};
		new Solve().solve3(board);
		Arrays.stream(board).map(Arrays::toString).forEach(System.out::println);
	}

	boolean[][] isChecked;
//	int N, M;
	boolean isOk;
	List<Coordinate> xy = new ArrayList<>();
	
	class Coordinate {
		int x;
		int y;
		public Coordinate(int x, int y) {
			this.x = x;
			this.y = y;
		}
		public int getX() {
			return x;
		}
		public int getY() {
			return y;
		}
	}
	
	public void solve(char[][] board) {

		N = board.length;
		if (N <= 2) return;
		M = board[0].length;
		if (M <= 2) return;
		
		isChecked = new boolean[N][M];
		
		for (int i = 1; i <= N - 2; i++) {
			for (int j = 1; j <= M - 2; j++) {
				if (board[i][j] == 'O' && !isChecked[i][j]) {
					isOk = true;
					xy.clear();
					checkAndFill(board, i, j);
					if (isOk) for (Coordinate c : xy) board[c.getX()][c.getY()] = 'X';
				}
			}
		}
	}
	
	private boolean checkAndFill(char[][] board, int i, int j) {
		
		if (board[i][j] == 'X' || isChecked[i][j]) return true;
		// 与边界上的'O'相连的都不能填充为'X'
		if (i == 0 || j == 0 || i == N - 1 || j == M - 1) return false;
		
		isChecked[i][j] = true;
		xy.add(new Coordinate(i, j));
		
		// 上
		if (!checkAndFill(board, i - 1, j)) isOk = false;
		// 右
		if (!checkAndFill(board, i, j + 1)) isOk = false;
		// 下
		if (!checkAndFill(board, i + 1, j)) isOk = false;
		// 左
		if (!checkAndFill(board, i, j - 1)) isOk = false;

		return true;
	}
	
	
	/**
	 * 思路：
	 * 检查所有边界上的'O'，把与其相连的元素标记起来
	 * 之后遍历除边界外的所有元素，将没有被标记的'O'填充为'X'
	 */
	int N, M;
	boolean[][] isSkip;
	public void solve2(char[][] board) {
		N = board.length;
		if (N <= 2) return;
		M = board[0].length;
		if (M <= 2) return;
		
		isSkip = new boolean[N][M];
		
		for (int idx = 0; idx < M; idx++) {
			checkSkip(board, 0, idx);
			checkSkip(board, N - 1, idx);
		}
		
		for (int idx = 1; idx < N - 1; idx++) {
			checkSkip(board, idx, 0);
			checkSkip(board, idx, M - 1);
		}
		
		for (int x = 1; x <= N - 2; x++) {
			for (int y = 1; y <= M - 2; y++) {
				if (board[x][y] == 'O' && !isSkip[x][y]) board[x][y] = 'X';
			}
		}
	}
	
	private void checkSkip(char[][] board, int x, int y) {
		if (board[x][y] == 'X' || isSkip[x][y]) return;
		
		isSkip[x][y] = true;
		
		// 上
		if (x > 0) checkSkip(board, x - 1, y);
		// 下
		if (x < N - 1) checkSkip(board, x + 1, y);
		// 左
		if (y > 0) checkSkip(board, x, y - 1);
		// 右
		if (y < M - 1) checkSkip(board, x, y + 1);
	}
	
	/**
	 * 3.并查集
	 */
	int rows, cols;
	
	public void solve3(char[][] board) {
		
		if (board == null || board.length == 0) return;
		
		rows = board.length;
		cols = board[0].length;
			
		UnionFind uf = new UnionFind(rows * cols + 1);
		int dummyNode = rows * cols;
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (board[i][j] == 'O') {
					if (i == 0 || i == rows - 1 || j == 0 || j == cols - 1) {
						uf.union(dummyNode, node(i, j));
					} else {
						if (board[i - 1][j] == 'O') uf.union(node(i, j), node(i - 1, j));
						if (board[i + 1][j] == 'O') uf.union(node(i, j), node(i + 1, j));
						if (board[i][j - 1] == 'O') uf.union(node(i, j), node(i, j - 1));
						if (board[i][j + 1] == 'O') uf.union(node(i, j), node(i, j + 1));
					}
				}
			}
		}
		
		System.out.println(Arrays.toString(uf.parents));
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j< cols; j ++) {
				if (uf.isConnected(node(i, j), dummyNode)) {
					board[i][j] = 'O';
				} else {
					board[i][j] = 'X';
				}
			}
		}
		System.out.println(Arrays.toString(uf.parents));
	}
	
	int node (int i, int j) {
		return i * cols + j;
	}
}

class UnionFind {
	int[] parents;
	public UnionFind(int totalNodes) {
		parents = new int[totalNodes];
		for (int i = 0; i < totalNodes; i++) {
			parents[i] = i;
		}
	}
	
	void union(int node1, int node2) {
		int root1 = find(node1);
		int root2 = find(node2);
		if (root1 != root2) {
			parents[root2] = root1;
		}
	}
	
	int find(int node) {
		while (parents[node] != node) {
			parents[node] = parents[parents[node]];
			node = parents[node];
		}
		return node;
	}
	
	boolean isConnected(int node1, int node2) {
		return find(node1) == find(node2);
	}
}
