package leetcode.p1;

/**
 * 给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。
 *
 *	'?' 可以匹配任何单个字符。
 *	'*' 可以匹配任意字符串（包括空字符串）。
 *	两个字符串完全匹配才算匹配成功。
 *	
 *	说明:
 *	
 *	s 可能为空，且只包含从 a-z 的小写字母。
 *	p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。
 *	示例 1:
 *	
 *	输入:
 *	s = "aa"
 *	p = "a"
 *	输出: false
 *	解释: "a" 无法匹配 "aa" 整个字符串。
 *	示例 2:
 *	
 *	输入:
 *	s = "aa"
 *	p = "*"
 *	输出: true
 *	解释: '*' 可以匹配任意字符串。
 *	示例 3:
 *	
 *	输入:
 *	s = "cb"
 *	p = "?a"
 *	输出: false
 *	解释: '?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
 *	示例 4:
 *	
 *	输入:
 *	s = "adceb"
 *	p = "*a*b"
 *	输出: true
 *	解释: 第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce".
 *	示例 5:
 *	
 *	输入:
 *	s = "acdcb"
 *	p = "a*c?b"
 *	输入: false
 */
public class IsMatch {

	public static void main(String[] args) {
		System.out.println(isMatch("acdcb", "a*c*b"));
	}
	
    public static boolean isMatch(String s, String p) {
        
    	int sIndex = 0, pIndex = 0, match = 0, starIndex = -1;
    	
    	// 遍历s
    	while (sIndex < s.length()) {
    		// 匹配成功(字符一致或者p[pIndex]为'?')
    		if (pIndex < p.length() && (p.charAt(pIndex) == '?' || s.charAt(sIndex) == p.charAt(pIndex))) {
    			sIndex++;
    			pIndex++;
    		} else if (pIndex < p.length() && p.charAt(pIndex) == '*') {
    			// 如果p[pIndex]为'*'，则假设用它来匹配空串，用starIndex记录*的位置
    			starIndex = pIndex++;
    			match = sIndex;
    		} else if (starIndex != -1) {
    			// 如果遇到匹配失败的情况，且之前有匹配到'*'
    			// 则将pIndex回退到starIndex的下一个位置
    			// match更新到下一个位置（表明*匹配多一个任意字符，然后sIndex回退到match+1的位置）
    			pIndex = starIndex + 1;
    			sIndex = ++match;
    		} else {
    			return false;
    		}
    	}
    	
    	// 去掉尾部多余的'*'
    	while (pIndex < p.length() && p.charAt(pIndex) == '*') {
    		pIndex++;
    	}
    	
    	return pIndex == p.length();
    }

}
