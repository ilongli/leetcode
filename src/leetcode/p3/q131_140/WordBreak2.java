package leetcode.p3.q131_140;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * 140.单词拆分II
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，在字符串中增加空格来构建一个句子，使得句子中所有的单词都在词典中。返回所有这些可能的句子。
 *	说明：
 *	
 *	分隔时可以重复使用字典中的单词。
 *	你可以假设字典中没有重复的单词。
 *	示例 1：
 *	
 *	输入:
 *	s = "catsanddog"
 *	wordDict = ["cat", "cats", "and", "sand", "dog"]
 *	输出:
 *	[
 *	  "cats and dog",
 *	  "cat sand dog"
 *	]
 *	示例 2：
 *	
 *	输入:
 *	s = "pineapplepenapple"
 *	wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
 *	输出:
 *	[
 *	  "pine apple pen apple",
 *	  "pineapple pen apple",
 *	  "pine applepen apple"
 *	]
 *	解释: 注意你可以重复使用字典中的单词。
 *	示例 3：
 *	
 *	输入:
 *	s = "catsandog"
 *	wordDict = ["cats", "dog", "sand", "and", "cat"]
 *	输出:
 *	[]
 * @author ilongli
 * @email 351365415@qq.com
 */
public class WordBreak2 {

	public static void main(String[] args) {
		String s = "aaaaa";
		List<String> wordDict = Arrays.asList("aa", "a");
		List<String> result = new WordBreak2().wordBreak(s, wordDict);
		for (String string : result) {
			System.out.println(string);
		}
	}
	
	
	/**
	 * 递归
	 */
    public List<String> wordBreak(String s, List<String> wordDict) {
        return word_Break(s, new HashSet<String>(wordDict), 0);
    }
    
    HashMap<Integer, List<String>> map = new HashMap<>();

    public List<String> word_Break(String s, Set<String> wordDict, int start) {
        if (map.containsKey(start)) {
            return map.get(start);
        }
        LinkedList<String> res = new LinkedList<>();
        if (start == s.length()) {
            res.add("");
        }
        for (int end = start + 1; end <= s.length(); end++) {
            if (wordDict.contains(s.substring(start, end))) {
                List<String> list = word_Break(s, wordDict, end);
                for (String l : list) {
                    res.add(s.substring(start, end) + (l.equals("") ? "" : " ") + l);
                }
            }
        }
        map.put(start, res);
        return res;
    }    
}
