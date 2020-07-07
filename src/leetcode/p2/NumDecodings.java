package leetcode.p2;

/**
 * 一条包含字母 A-Z 的消息通过以下方式进行了编码：
 *	'A' -> 1
 *	'B' -> 2
 *	...
 *	'Z' -> 26
 *	给定一个只包含数字的非空字符串，请计算解码方法的总数。
 *	
 *	示例 1:
 *	
 *	输入: "12"
 *	输出: 2
 *	解释: 它可以解码为 "AB"（1 2）或者 "L"（12）。
 *	示例 2:
 *	
 *	输入: "226"
 *	输出: 3
 *	解释: 它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
 * @author ilongli
 * @email 351365415@qq.com
 */
public class NumDecodings {

	public static void main(String[] args) {
		System.out.println(new NumDecodings().numDecodings2("226"));
	}
	
	
    public int numDecodings2(String s) {
    	// check null
    	if (s.length() == 0 || s.charAt(0) == '0') return 0;
    	
    	char[] strs = s.toCharArray();
    	int len = s.length();
    	// dp[i]用于记录字符串至第i-1位前的解码方法的总数
    	int[] dp = new int[len + 1];
    	dp[0] = 1;
    	
    	for (int i = 1; i < len + 1; i++) {
    		// 当前数字不为0时，dp[i] += dp[i - 1]表示当前组合数量包括前一位数字前的组合总数
    		if (strs[i - 1] != '0') {
    			dp[i] += dp[i - 1];
    		}
    		// 当前数组与前一位组合的数字处于10-26时，
    		// dp[i] += dp[i - 2]表示当前组合数量包括前两位数字前的组合总数
    		if (i > 1) {
    			int num = (strs[i - 2] - '0') * 10 + (strs[i - 1] - '0');
    			// 连续两个0，表没有对应的解码方式，直接返回0
    			if (num == 0) return 0;
    			if (num > 9 && num < 27) dp[i] += dp[i - 2];
    		}
    	}
    	return dp[len];
    }
	
	
	
    public int numDecodings(String s) {
        char[] strs = s.toCharArray();
    	return backtrack(strs, 0, 0);
    }
    
    // backtrack
    public int backtrack(char[] strs, int idx, int pre) {
    	if (idx == strs.length) {
    		return pre == 0 ? 1 : 0;
    	}

    	int cur = strs[idx] - 48;
    	
    	if (pre == 0 && cur == 0) return 0;
    	
    	int count = 0;
    	
    	if (pre == 0) {
    		count += backtrack(strs, idx + 1, 0);
    		if (cur < 3) count += backtrack(strs, idx + 1, cur);
    	} else if (pre == 1) {
    		count += backtrack(strs, idx + 1, 0);
    	} else if (pre == 2) {
    		if (cur < 7) count += backtrack(strs, idx + 1, 0);
    		else return 0;
    	}
    	
    	return count;
    }
    
}
