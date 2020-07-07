package leetcode.p3.q111_120;

/**
 * 115.不同的子序列
 * 
 * 给定一个字符串 S 和一个字符串 T，计算在 S 的子序列中 T 出现的个数。
 *	一个字符串的一个子序列是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。（例如，"ACE" 是 "ABCDE" 的一个子序列，而 "AEC" 不是）
 *	
 *	示例 1:
 *	
 *	输入: S = "rabbbit", T = "rabbit"
 *	输出: 3
 *	解释:
 *	
 *	如下图所示, 有 3 种可以从 S 中得到 "rabbit" 的方案。
 *	(上箭头符号 ^ 表示选取的字母)
 *	
 *	rabbbit
 *	^^^^ ^^
 *	rabbbit
 *	^^ ^^^^
 *	rabbbit
 *	^^^ ^^^
 *	示例 2:
 *	
 *	输入: S = "babgbag", T = "bag"
 *	输出: 5
 *	解释:
 *	
 *	如下图所示, 有 5 种可以从 S 中得到 "bag" 的方案。 
 *	(上箭头符号 ^ 表示选取的字母)
 *	
 *	babgbag
 *	^^ ^
 *	babgbag
 *	^^    ^
 *	babgbag
 *	^    ^^
 *	babgbag
 *	  ^  ^^
 *	babgbag
 *	    ^^^
 * 
 * @author ilongli
 * @email 351365415@qq.com
 */
public class NumDistinct {

	public static void main(String[] args) {
		String s = "okok", t = "ok";
		System.out.println(new NumDistinct().numDistinct2(s, t));
	}
	
	/**
	 * 递归(超出时间限制)
	 */
	char[] sca, tca;
	int res = 0;
	
    public int numDistinct(String s, String t) {
    	// check null
    	if (s.length() < t.length()) return res;
    	
    	this.sca = s.toCharArray();
    	this.tca = t.toCharArray();
    	
    	helper(0, 0, 0);
    	
    	return res;
    }
    
    private void helper(int num, int sp, int tp) {
    	if (num == this.tca.length) {
    		this.res++;
    		return;
    	}
    	if (sp == this.sca.length) return;
    	// 剪枝
    	if (this.sca.length - sp + num < this.tca.length) return; 
    	
    	if (this.sca[sp] == this.tca[tp]) {
    		helper(num + 1, sp + 1, tp + 1);
    	} 
    	helper(num, sp + 1, tp);
    }
    
    /**
     * 动态规划
     * 参考：https://leetcode.wang/leetcode-115-Distinct-Subsequences.html
     */
    public int numDistinct2(String s, String t) {
    	int s_len = s.length(), t_len = t.length();
    	int[][] dp = new int[s_len + 1][t_len + 1];
    	// 当t为空字符串时，所有dp为1
    	for (int i = 0; i <= s_len; i++) dp[i][t_len] = 1;
    	
    	for (int i = t_len - 1; i >= 0; i--) {
//    		dp[s_len][i] = 0;
    		for (int j = s_len - 1; j >= 0; j--) {
    			// 分两种情况
    			if (t.charAt(i) == s.charAt(j)) {
    				// 如果字母相同，有两种情况
    				// ①选择当前字母	②不选择当前字母
    				dp[j][i] = dp[j + 1][i + 1] + dp[j + 1][i];
    			} else {
    				// 如果字母不相同，只能不选择当前字母
    				dp[j][i] = dp[j + 1][i];
    			}
    		}
    	}
    	return dp[0][0];
    }
    
}
