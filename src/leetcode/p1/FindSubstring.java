package leetcode.p1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给定一个字符串 s 和一些长度相同的单词 words。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。
 *
 *	注意子串要与 words 中的单词完全匹配，中间不能有其他字符，但不需要考虑 words 中单词串联的顺序。
 *	
 *
 *	示例 1：
 *	
 *	输入：
 *	  s = "barfoothefoobarman",
 *	  words = ["foo","bar"]
 *	输出：[0,9]
 *	解释：
 *	从索引 0 和 9 开始的子串分别是 "barfoor" 和 "foobar" 。
 *	输出的顺序不重要, [9,0] 也是有效答案。
 *	示例 2：
 *	
 *	输入：
 *	  s = "wordgoodgoodgoodbestword",
 *	  words = ["word","good","best","word"]
 *	输出：[]
 *
 *  参考：https://leetcode.windliang.cc/leetCode-30-Substring-with-Concatenation-of-All-Words.html
 */
public class FindSubstring {
	public static void main(String[] args) {
		System.out.println(findSubstring2("barfoothefoobarman", new String[] {"foo", "bar"}));
	}
	
    public static List<Integer> findSubstring(String s, String[] words) {
        
    	// words的个数
    	int wordNum = words.length;
    	
    	// 判空（words为空）
    	if (wordNum == 0) return Collections.emptyList();
    	
    	// 每个单词的长度(每次单词的长度一样)
    	int wordLen = words[0].length();
    	
    	// 判空（s的长度小于wordLen*wordNum）
    	if (s.length() < wordLen * wordNum) return Collections.emptyList();
    	
    	// 用于存储结果的list
    	List<Integer> res = new ArrayList<>();
    	
    	// map1，用于储存每个单词的出现次数（key：单词；value：单词出现的次数）
    	Map<String, Integer> allWords = new HashMap<>();
    	for (String word : words) {
    		allWords.compute(word, (k, v) -> v == null ? 1 : (v + 1));
    	}
    	
    	// 用wordLen*wordNum长度的滑块在s上进行滑动
    	for (int i = 0; i < s.length() - wordLen * wordNum + 1; i++) {
    		// map2，用于临时存储当前滑块含有的单词
    		Map<String, Integer> hasWords = new HashMap<>();
    		int num = 0;
    		
    		// 开始遍历滑块内的每个单词
    		while (num < wordNum) {
    			// 取出当前滑块的第(num+1)个单词出来
    			String word = s.substring(i + num * wordLen, i + (num + 1) * wordLen);
    			// 判断该单词是否在allWords中存在
    			if (allWords.containsKey(word)) {
    				// 将单词放入hasWords内
    				// 并判断此时该单词在滑块内的数量是否超出该单词在allWords内的数量
    				if (hasWords.compute(word, (k, v) -> v == null ? 1 : (v + 1)) > allWords.get(word)) {
    					// 如果超过，说明当前滑块内的子串不符合条件
    					break;
    				}
    			} else {
    				// 如果不存在，说明当前滑块内的子串不符合条件
    				break;
    			}
    			
    			num++;
    		}
    		
    		// 如果num值等于wordNum说明当前滑块内的所有单词都符合条件
    		if (num == wordNum) res.add(i);
    	}
    	
    	return res;
    }

    public static List<Integer> findSubstring2(String s, String[] words) {
    	
    	// words的个数
    	int wordNum = words.length;
    	
    	// 判空（words为空）
    	if (wordNum == 0) return Collections.emptyList();
    	
    	// 每个单词的长度(每次单词的长度一样)
    	int wordLen = words[0].length();
    	
    	// 判空（s的长度小于wordLen*wordNum）
    	if (s.length() < wordLen * wordNum) return Collections.emptyList();
    	
    	// 用于存储结果的list
    	List<Integer> res = new ArrayList<>();
    	
    	// map1，用于储存每个单词的出现次数（key：单词；value：单词出现的次数）
    	Map<String, Integer> allWords = new HashMap<>();
    	for (String word : words) {
    		allWords.compute(word, (k, v) -> v == null ? 1 : (v + 1));
    	}
    	
    	// 将所有的移动次数，划分为wordLen类
    	for (int j = 0; j < wordLen; j++) {
    		// map2，用于临时存储当前滑块含有的单词
    		Map<String, Integer> hasWords = new HashMap<>();
    		int num = 0;
    		
        	// 用wordLen*wordNum长度的滑块在s上进行滑动，每次移动一个单词的长度
        	for (int i = j; i < s.length() - wordLen * wordNum + 1; i += wordLen) {
        		boolean hasRemoved = false;	// 防止情况三移除后，情况一继续移除
        		
        		// 开始遍历滑块内的每个单词
        		while (num < wordNum) {
        			// 取出当前滑块的第(num+1)个单词出来
        			String word = s.substring(i + num * wordLen, i + (num + 1) * wordLen);
        			// 判断该单词是否在allWords中存在
        			if (allWords.containsKey(word)) {
        				// 将单词放入hasWords内
        				// 并判断此时该单词在滑块内的数量是否超出该单词在allWords内的数量
        				if (hasWords.compute(word, (k, v) -> v == null ? 1 : (v + 1)) > allWords.get(word)) {
        					// 如果超过，说明出现情况3，当前单词都符合，但是次数超了
        					hasRemoved = true;
        					int removeNum = 0;
        					// 一直移除单词，直到次数符合
        					while (hasWords.get(word) > allWords.get(word)) {
        						String firstWord = s.substring(i + removeNum * wordLen, i + (removeNum + 1) * wordLen);
        						hasWords.compute(firstWord, (k, v) -> v - 1);
        						removeNum++;
        					}
        					num = num - removeNum + 1;
        					i = i + (removeNum - 1) * wordLen;
        					break;
        				}
        			} else {
        				// 遇到不匹配的单词，说明出现情况2
        				// 直接将i移动到该单词的后面
        				hasWords.clear();
        				i = i + num * wordLen;
        				num = 0;
        				break;
        			}
        			num++;
        		}
        		// 如果num值等于wordNum说明当前滑块内的所有单词都符合条件
        		if (num == wordNum) res.add(i);
        		
        		if (num > 0 && !hasRemoved) {
        			// 这里便会出现情况1
        			hasWords.compute(s.substring(i, i + wordLen), (k, v) -> v - 1);
        			num = num - 1;
        		}
        	}
    	}
    	
    	return res;
    }
}
