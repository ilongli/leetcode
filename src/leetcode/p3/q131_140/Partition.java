package leetcode.p3.q131_140;

import java.util.ArrayList;
import java.util.List;

/**
 * 131.分割回文串
 * 
 * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
 *	返回 s 所有可能的分割方案。
 *	
 *	示例:
 *	
 *	输入: "aab"
 *	输出:
 *	[
 *	  ["aa","b"],
 *	  ["a","a","b"]
 *	]
 * @author ilongli
 * @email 351365415@qq.com
 */
public class Partition {

	public static void main(String[] args) {
		
	}
	
	
	
	/**
	 * 1.分治
	 */
    public List<List<String>> partition(String s) {
    	return partitionHelper(s, 0);
    }
    
    /**
     * @param s
     * @param start
     * @return
     */
    private List<List<String>> partitionHelper(String s, int start) {
    	// 递归出口，空字符串
    	if (start == s.length()) {
    		List<String> list = new ArrayList<>();
    		List<List<String>> ans = new ArrayList<>();
    		ans.add(list);
    		return ans;
    	}
    	
    	List<List<String>> ans = new ArrayList<>();
    	for (int i = start; i < s.length(); i++) {
    		// 当前切割后是回文串才考虑
    		if (isPalindrome(s.substring(start, i + 1))) {
    			String left = s.substring(start, i+ 1);
    			// 遍历后边字符串的所有结果，将当前的字符串加到头部
    			for (List<String> l : partitionHelper(s, i + 1)) {
    				l.add(0, left);
    				ans.add(l);
    			}
    		}
    	}
    	
    	return ans;
    }
    
    private boolean isPalindrome(String s) {
    	int i = 0;
    	int j = s.length() - 1;
    	while (i < j) {
    		if (s.charAt(i) != s.charAt(j)) {
    			return false;
    		}
    		i++;
    		j--;
    	}
    	return true;
    }


    /**
     * 2.分治优化
     * 用动态规划的方法，把所有字符是否回文串提前存起来
     */
    public List<List<String>> partition2(String s) {
    	boolean[][] dp = new boolean[s.length()][s.length()];
    	int length = s.length();
    	for (int len = 1; len <= length; len++) {
    		for (int i = 0; i <= s.length() - len; i++) {
    			int j = i + len - 1;
    			dp[i][j] = s.charAt(i) == s.charAt(j) && (len < 3 || dp[i + 1][j - 1]);
    		}
    	}
    	
    	return partitionHelper2(s, 0 , dp);
    }

    private List<List<String>> partitionHelper2(String s, int start, boolean[][] dp) {
    	if (start == s.length()) {
    		List<String> list = new ArrayList<>();
    		List<List<String>> ans = new ArrayList<>();
    		ans.add(list);
    		return ans;
    	}
    	List<List<String>> ans = new ArrayList<>();
    	for (int i = start; i < s.length(); i++) {
    		if (dp[start][i]) {
    			String left = s.substring(start, i + 1);
    			for (List<String> l : partitionHelper2(s, i + 1, dp)) {
    				l.add(0, left);
    				ans.add(l);
    			}
    		}
    	}
    	
    	return ans;
    }

    /**
     * 3.回溯
     */
    public List<List<String>> partition3(String s) {
    	boolean[][] dp = new boolean[s.length()][s.length()];
    	int length = s.length();
    	for (int len = 1; len <= length; len++) {
    		for (int i = 0; i <= s.length() - len; i++) {
    			dp[i][i + len - 1] = s.charAt(i) == s.charAt(i + len - 1) && (len < 3 || dp[i + 1][i + len - 2]);
    		}
    	}
    	List<List<String>> ans = new ArrayList<>();
    	partitionHelper3(s, 0, dp, new ArrayList<>(), ans);
    	return ans;
    }

    private void partitionHelper3(String s, int start, boolean[][] dp, List<String> temp, List<List<String>> res) {
    	if (start == s.length()) {
    		res.add(new ArrayList<>(temp));
    	}
    	
    	for (int i = start; i < s.length(); i++) {
    		if (dp[start][i]) {
    			String left = s.substring(start, i + 1);
    			temp.add(left);
    			partitionHelper3(s, i + 1, dp, temp, res);
    			temp.remove(temp.size() - 1);
    		}
    	}
    }
    
    
}
