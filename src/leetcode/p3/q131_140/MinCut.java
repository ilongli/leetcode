package leetcode.p3.q131_140;

import java.util.HashMap;

/**
 * 132.分隔回文串II
 * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
 *	返回符合要求的最少分割次数。
 *	
 *	示例:
 *	
 *	输入: "aab"
 *	输出: 1
 *	解释: 进行一次分割就可将 s 分割成 ["aa","b"] 这样两个回文子串。
 * @author ilongli
 * @email 351365415@qq.com
 */
public class MinCut {

	public static void main(String[] args) {
		
	}
	
	/**
	 * 1.分治
	 */
    public int minCut(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];
        int length = s.length();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int len = 1; len <= length; len++) {
        	for (int i = 0; i <= s.length() - len; i++) {
        		int j = i + len - 1;
        		dp[i][j] = s.charAt(i) == s.charAt(j) && (len < 3 || dp[i + 1][j - 1]);
        	}
        }
    	
    	return minCutHelper(s, 0, dp, map);
    }
    
    private int minCutHelper(String s, int start, boolean[][] dp, HashMap<Integer, Integer> map) {
    	if (map.containsKey(start)) {
    		return map.get(start);
    	}
    	if (dp[start][s.length() - 1]) {
    		return 0;
    	}
    	int min = Integer.MAX_VALUE;
    	for (int i = start; i < s.length(); i++) {
    		if (dp[start][i]) {
    			min = Math.min(min, 1 + minCutHelper(s, i + 1, dp, map));
    		}
    	}
    	map.put(start, min);
    	return min;
    }
    
    
    /**
     * 2.回溯
     */
    public int minCut2(String s) {
    	boolean[][] dp = new boolean[s.length()][s.length()];
    	int length = s.length();
    	
    	for (int len = 1; len <= length; len++) {
    		for (int i = 0; i <= s.length() - len; i++) {
    			int j = i + len - 1;
    			dp[i][j] = s.charAt(i) == s.charAt(j) && (len < 3 || dp[i + 1][j - 1]);
    		}
    	}
    	HashMap<Integer, Integer> map = new HashMap<>();
    	minCutHelper2(s, 0, dp, 0, map);
    	return min;
    }
    
    int min = Integer.MAX_VALUE;
    
    private void minCutHelper2(String s, int start, boolean[][] dp, int num, HashMap<Integer, Integer> map) {
    	if (map.containsKey(start)) {
    		min = Math.min(min, num + map.get(start));
    		return;
    	}
    	
    	if (dp[start][s.length() - 1]) {
    		min = Math.min(min, num);
    		return;
    	}
    	
    	for (int i = start; i < s.length() - 1; i++) {
    		if (dp[start][i]) {
    			minCutHelper2(s, i + 1, dp, num + 1, map);
    		}
    	}
    	
    	if (min > num) {
    		map.put(start, min - num);
    	}
    }
    
    
    /**
     * 3.
     */
    public int minCut3(String s) {
    	int[] dp = new int[s.length()];
    	int n = s.length();
    	// 假设没有任何回文串，初始化dp
    	for (int i = 0; i < n; i++) {
    		dp[i] = i;
    	}
    	
    	// 考虑每个中心
    	for (int i = 0; i < s.length(); i++) {
    		int j = 0;
    		// 考虑奇数情况
    		while (true) {
    			if (i - j < 0 || i + j > n - 1) break;
    			if (s.charAt(i - j) == s.charAt(i + j)) {
    				if (i - j == 0) dp[i + j] = 0;
    				else dp[i + j] = Math.min(dp[i + j], dp[i - j - 1] + 1);
    			} else {
    				break;
    			}
    			j++;
    		}
    		
    		// 考虑偶数情况
    		j = 1;
    		while (true) {
    			if (i - j + 1 < 0 || i + j > n - 1) break;
    			if (s.charAt(i - j + 1) == s.charAt(i + j)) {
    				if (i - j + 1 == 0) dp[i + j] = 0;
    				// ... , dp[i - j + 1 - 1] + 1);
    				else dp[i + j] = Math.min(dp[i + j], dp[i - j] + 1);
    			} else {
    				break;
    			}
    			j++;
    		}
    	}
    	
    	return dp[n - 1];
    }
    
    
    
    
    
    
    
    
}
