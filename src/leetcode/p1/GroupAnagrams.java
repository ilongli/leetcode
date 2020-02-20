package leetcode.p1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 *
 *	示例:
 *	
 *	输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
 *	输出:
 *	[
 *	  ["ate","eat","tea"],
 *	  ["nat","tan"],
 *	  ["bat"]
 *	]
 *	说明：
 *	
 *	所有输入均为小写字母。
 *	不考虑答案输出的顺序。
 */
public class GroupAnagrams {

	public static void main(String[] args) {
		
		System.out.println(groupAnagrams(new String[] {"eat", "tea", "tan", "ate", "nat", "bat"}));
	}
	
    public static List<List<String>> groupAnagrams(String[] strs) {
        
    	Map<String, List<String>> map = new HashMap<>();
    	// 遍历每个字符串
    	for (String str : strs) {
    		// 讲字符串转换为字符数组并排序
    		char[] charArray = str.toCharArray();
    		Arrays.sort(charArray);
    		// 将排序后的字符串数组重新组成字符串，作为map中的key值
    		String key = String.valueOf(charArray);
    		// 放入map中
    		List<String> val = map.get(key);
    		if (val == null) {
    			ArrayList<String> list = new ArrayList<String>();
    			list.add(str);
    			map.put(key, list);
    		} else {
    			val.add(str);
    		}
    	}
    	
    	return new ArrayList<List<String>>(map.values());
    }
}
