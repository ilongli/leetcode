package leetcode.p1;

/**
 * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
 *
 *	示例 1:
 *	
 *	输入: "(()"
 *	输出: 2
 *	解释: 最长有效括号子串为 "()"
 *	示例 2:
 *	
 *	输入: ")()())"
 *	输出: 4
 *	解释: 最长有效括号子串为 "()()"
 *
 *  参考：https://leetcode-cn.com/problems/longest-valid-parentheses/comments/
 *  @望雪有感
 */
public class LongestValidParentheses {

	public static void main(String[] args) {
		System.out.println(longestValidParentheses("(()"));
	}
	
    public static int longestValidParentheses(String s) {
        char[] chars = s.toCharArray();
        return Math.max(calc(chars, 0, 1, chars.length, '('), calc(chars, chars.length -1, -1, -1, ')'));
    }
    
    private static int calc(char[] chars, int i, int flag, int end, char comp) {
    	int max = 0, bal = 0, curLen = 0, validLen = 0;
    	while (i != end) {
    		bal += (chars[i] == comp ? 1 : -1);
    		curLen++;
    		if (bal < 0) {
    			max = max > validLen ? max : validLen;
    			bal = 0;
    			curLen = 0;
    			validLen = 0;
    		} else if (bal == 0) {
    			validLen = curLen;
    		}
    		i += flag;
    	}
    	return max > validLen ? max : validLen;
    }
}
