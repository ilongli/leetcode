package leetcode.p2;

import java.util.Arrays;

/**
 * 给定两个单词 word1 和 word2，计算出将 word1 转换成 word2 所使用的最少操作数 。
 *	你可以对一个单词进行如下三种操作：
 *	
 *	插入一个字符
 *	删除一个字符
 *	替换一个字符
 *	示例 1:
 *	
 *	输入: word1 = "horse", word2 = "ros"
 *	输出: 3
 *	解释: 
 *	horse -> rorse (将 'h' 替换为 'r')
 *	rorse -> rose (删除 'r')
 *	rose -> ros (删除 'e')
 *	示例 2:
 *	
 *	输入: word1 = "intention", word2 = "execution"
 *	输出: 5
 *	解释: 
 *	intention -> inention (删除 't')
 *	inention -> enention (将 'i' 替换为 'e')
 *	enention -> exention (将 'n' 替换为 'x')
 *	exention -> exection (将 'n' 替换为 'c')
 *	exection -> execution (插入 'u')
 *
 * @author ilongli
 * @email 351365415@qq.com
 */
public class MinDistance {

	public static void main(String[] args) {
		String word1 = "intention";
		String word2 = "execution";
		System.out.println(minDistance(word1, word2));
	}
	
    public static int minDistance(String word1, String word2) {
    	// init
        int n1 = word1.length(), n2 = word2.length();
        char[] char1 = word1.toCharArray();
        char[] char2 = word2.toCharArray();
        int[][] dp = new int[n1 + 1][n2 + 1];
        // init the first row
        for (int j = 0; j < n2 + 1; j++) dp[0][j] = j;
        // init the first col
        for (int i = 0; i < n1 + 1; i++) dp[i][0] = i;
        
        // 当word1[i] == word2[j]时，dp[i][j] = dp[i-1][j-1];
        // 当word1[i] != word2[j]时，dp[i][j] = min(dp[i-1][j-1],dp[i-1][j],dp[i][j-1]) + 1;
        for (int i = 1; i <= n1; i++) {
        	for (int j = 1; j <= n2; j++) {
        		if (char1[i-1] == char2[j-1]) {
        			dp[i][j] = dp[i-1][j-1];
        		} else {
        			dp[i][j] = Math.min(Math.min(dp[i-1][j-1], dp[i-1][j]), dp[i][j-1]) + 1;
        		}
        	}
        }
   
    	return dp[n1][n2];
    }
}
