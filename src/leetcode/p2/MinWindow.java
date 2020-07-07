package leetcode.p2;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字母的最小子串。
 *	示例：
 *	
 *	输入: S = "ADOBECODEBANC", T = "ABC"
 *	输出: "BANC"
 *	说明：
 *	
 *	如果 S 中不存这样的子串，则返回空字符串 ""。
 *	如果 S 中存在这样的子串，我们保证它是唯一的答案。
 * @author ilongli
 * @email 351365415@qq.com
 */
public class MinWindow {

	public static void main(String[] args) {
//		String s = "ADOBECODEBANC", t = "ABC";
//		String s = "aa", t = "aa";
		String s = "cabwefgewcwaefgcf", t = "cae";
		System.out.println(minWindow(s, t));
	}
	
    public static String minWindow(String s, String t) {
    	// check null
    	if (s.equals("") || t.equals("")) return "";
    	
    	// init
    	int left = 0, right = 0, count = 0, required = t.length();
    	String res = "";
    	char[] sCharArray = s.toCharArray();
    	boolean isExpanding = true;
    	
    	Map<Character, Integer> tMap = new HashMap<>();
    	
    	for (char c : t.toCharArray()) {
    		if (tMap.containsKey(c)) {
    			tMap.put(c, tMap.get(c) - 1);	
    		} else {
    			tMap.put(c, -1);
    		}
    	}
    	required = tMap.size();
    	
    	while (right < s.length() || !isExpanding) {
    		
    		if (isExpanding) {
    			// 滑动窗口扩张
    			char curChar = sCharArray[right++];
    			if (tMap.containsKey(curChar)) {
    				int value = tMap.get(curChar) + 1;
    				tMap.put(curChar, value);
    				if (value == 0) count++;
    			}
    		} else {
    			// 滑动窗口收缩
    			char curChar = sCharArray[left++];
    			if (tMap.containsKey(curChar)) {
    				int value = tMap.get(curChar);
    				tMap.put(curChar, value - 1);
    				if (value == 0) count--;
    			}
    		}
    		
    		if (count == required) {
    			isExpanding = false;
    			// 更新res
    			if ((right - left) < res.length() || res.equals("")) {
    				res = s.substring(left, right);
    			}
    		} else {
    			isExpanding = true;
    		}
    	}
    	
    	return res;
    }
}
