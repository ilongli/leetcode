package leetcode.p3.q131_140;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * 139.单词拆分
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 *	说明：
 *	
 *	拆分时可以重复使用字典中的单词。
 *	你可以假设字典中没有重复的单词。
 *	示例 1：
 *	
 *	输入: s = "leetcode", wordDict = ["leet", "code"]
 *	输出: true
 *	解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
 *	示例 2：
 *	
 *	输入: s = "applepenapple", wordDict = ["apple", "pen"]
 *	输出: true
 *	解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
 *	     注意你可以重复使用字典中的单词。
 *	示例 3：
 *	
 *	输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 *	输出: false
 * 
 * @author ilongli
 * @email 351365415@qq.com
 */
public class WordBreak {

	public static void main(String[] args) {
		String s = "leetcode";
		List<String> wordDict = Arrays.asList("leet", "code");
		System.out.println(new WordBreak().wordBreak(s, wordDict));
	}

	/**
	 * 回溯(带记忆优化)
	 */
    public boolean wordBreak(String s, List<String> wordDict) {
    	return helper(s, new HashSet<String>(wordDict), 0, new Boolean[s.length()]);
    }
    
    private boolean helper(String s, Set<?> wordDict, int start, Boolean[] mem) {
    	if (mem[start] != null) return mem[start];
    	for (int end = start + 1; end <= s.length(); end++) {
    		if (wordDict.contains(s.substring(start, end))) {
    			if (end == s.length()) {
    				return true;
    			}
    			if (helper(s, wordDict, end, mem)) {
    				return mem[start] = true;
    			}
    		}
    	}
    	return mem[start] = false;
    }
    
    /**
     * BFS
     */
    public boolean wordBreak2(String s, List<String> wordDict) {
    	Set<String> wordDictSet = new HashSet<>(wordDict);
    	Queue<Integer> queue = new LinkedList<>();
    	int[] visited = new int[s.length()];
    	queue.add(0);
    	while (!queue.isEmpty()) {
    		int start = queue.remove();
    		if (visited[start] == 0) {
    			for (int end = start + 1; end <= s.length(); end++) {
    				if (wordDictSet.contains(s.subSequence(start, end))) {
    					queue.add(end);
    					if (end == s.length()) return true;
    				}
    			}
    			visited[start] = 1;
    		}
    	}
    	return false;
    }
    
    /**
     * 动态规划(分治)
     */
    public boolean wordBreak3(String s, List<String> wordDict) {
    	Set<String> wordDictSet = new HashSet<>(wordDict);
    	boolean[] dp = new boolean[s.length() + 1];
    	dp[0] = true;
    	for (int i = 1; i <= s.length(); i++) {
    		for (int j = 0; j< i; j++) {
    			if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
    				dp[i] = true;
    				break;
    			}
    		}
    	}
    	return dp[s.length()];
    }
    
}
