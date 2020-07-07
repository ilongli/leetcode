package leetcode.p2;

/**
 * 97.交错字符串
 * 给定三个字符串 s1, s2, s3, 验证 s3 是否是由 s1 和 s2 交错组成的。
 *	示例 1:
 *	
 *	输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 *	输出: true
 *	示例 2:
 *	
 *	输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 *	输出: false
 * @author ilongli
 * @email 351365415@qq.com
 */
public class IsInterleave {

	public static void main(String[] args) {
		System.out.println(new IsInterleave().isInterleave4("abc", "bcd", "abcbdc"));
	}
	
	/**
	 * 1.暴力法
	 * 回溯
	 * 时间复杂度：O(2^(m+n))。m、n分别是s1和s2的长度。
	 * 空间复杂度：O(m+n)。递归栈的深度最多为m+n。
	 */
	public boolean isInterleave1(String s1, String s2, String s3) {
		return is_Interleave1(s1, 0, s2, 0, "", s3);
	}
	
	public boolean is_Interleave1(String s1, int i, String s2, int j, String res, String s3) {
		if (res.equals(s3) && i == s1.length() && j == s2.length()) return true;
		
		boolean ans = false;
		if (i < s1.length()) {
			ans |= is_Interleave1(s1, i + 1, s2, j, res + s1.charAt(i), s3);
		}
		if (j < s2.length()) {
			ans |= is_Interleave1(s1, i, s2, j + 1, res + s2.charAt(j), s3);
		}
		return ans;
	}
	
	/**
	 * 2.记忆化回溯
	 */
	public boolean isInterleave2(String s1, String s2, String s3) {
		int memo[][] = new int[s1.length()][s2.length()];
		for (int i = 0; i < s1.length(); i++) {
			for (int j = 0; j < s2.length(); j++) {
				memo[i][j] = -1;
			}
		}
		
		return is_Interleave2(s1, 0, s2, 0, s3, 0, memo);
	}
	
	public boolean is_Interleave2(String s1, int i, String s2, int j, String s3, int k, int[][] memo) {
		if (i == s1.length()) {
			return s2.substring(j).equals(s3.substring(k));
		}
		if (j == s2.length()) {
			return s1.substring(i).equals(s3.substring(k));
		}
		if (memo[i][j] >= 0) {
			return memo[i][j] == 1 ? true : false;
		}
		boolean ans = false;
		if (s3.charAt(k) == s1.charAt(i) && is_Interleave2(s1, i + 1, s2, j, s3, k + 1, memo)
				|| s3.charAt(k) == s2.charAt(j) && is_Interleave2(s1, i, s2, j + 1, s3, k + 1, memo)) {
			ans = true;
		}
		memo[i][j] = ans ? 1 : 0;
		return ans;
	}
	
	/**
	 * 3.二维动态规划
	 * 时间复杂度:O(m*n)。计算dp数组需要m*n的时间。
	 * 空间复杂度:O(m*n)。2维的dp数组需要(m+1)*(n+1)的空间。m和n分别是s1和s2字符串的长度。
	 */
	public boolean isInterleave3(String s1, String s2, String s3) {
		if (s3.length() != s1.length() + s2.length()) return false;
		
		boolean dp[][] = new boolean[s1.length() + 1][s2.length() + 1];
		for (int i = 0; i <= s1.length(); i++) {
			for (int j = 0; j <= s2.length(); j++) {
				if (i == 0 && j == 0) {
					dp[i][j] = true;
				} else if (i == 0) {
					dp[i][j] = dp[i][j - 1] && s2.charAt(j - 1) ==  s3.charAt(i + j - 1);
				} else if (j == 0) {
					dp[i][j] = dp[i - 1][j] && s1.charAt(i - 1) ==  s3.charAt(i + j - 1);
				} else {
					dp[i][j] = (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)) || 
							   (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
				}
			}
		}
		return dp[s1.length()][s2.length()];
	}
	
	/**
	 * 4.使用一维动态规划 
	 * 利用滚动数组:利用dp[i-1]的结果和dp[i]之前的结果来计算dp[i]。
	 * 时间复杂度:O(m*n)。长度为n的dp数组需要被填充m次。
	 * 空间府再度:O(n)。n是字符串s1的长度。
	 */
	public boolean isInterleave4(String s1, String s2, String s3) {
		if (s3.length() != s1.length() + s2.length()) return false;
		boolean dp[] = new boolean[s2.length() + 1];
		for (int i = 0; i <= s1.length(); i++) {
			for (int j = 0; j <= s2.length(); j++) {
				if (i == 0 && j == 0) {
					dp[j] = true;
				} else if (i == 0) {
					dp[j] = dp[j - 1] && s2.charAt(j - 1) == s3.charAt(i + j -1);
				} else if (j == 0) {
					dp[j] = dp[j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
				} else {
					dp[j] = (dp[j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)) ||
							(dp[j - 1] && s2.charAt(j - 1) == s3.charAt(i + j -1));
				}
			}
		}
		return dp[s2.length()];
	}
	
	
    public boolean isInterleave(String s1, String s2, String s3) {
    	// 判空
    	int l1 = s1.length(), l2 = s2.length(), l3 = s3.length();
    	if (l1 + l2 != l3) return false;
    	return true;
    }
	
}
