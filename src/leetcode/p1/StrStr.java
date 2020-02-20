package leetcode.p1;

/**
 * 实现 strStr() 函数。
 *	
 *	给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
 *	
 *	示例 1:
 *	
 *	输入: haystack = "hello", needle = "ll"
 *	输出: 2
 *	示例 2:
 *	
 *	输入: haystack = "aaaaa", needle = "bba"
 *	输出: -1
 *	说明:
 *	
 *	当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 *	
 *	对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
 */
public class StrStr {
	
	public static void main(String[] args) {
		
		System.out.println(strStr("", "dsf"));
	}
	
	
    public static int strStr(String haystack, String needle) {
    	if (needle.length() == 0) return 0;
    	
    	// 生成next
    	int[] next = getNext(needle);
    	
    	// KMP遍历
    	char[] h = haystack.toCharArray(), n = needle.toCharArray();
    	int i = 0, j = 0;
    	while (i < h.length && j < n.length) {
    		if ( j == -1 || h[i] == n[j]) {
    			i++; 
    			j++;
    		} else {
    			j = next[j];
    		}
    	}
    	
    	return j == n.length ? i - j : -1;
    }
    
    public static int[] getNext(String needle) {
    	
    	char[] n = needle.toCharArray();
    	
    	int[] next = new int[n.length];
    	
    	next[0] = -1;
    	int i = 0, j = -1;
    
    	while (i < n.length - 1) {
    		if (j == -1 || n[i] == n[j]) {
    			next[++i] = ++j;
    		} else {
    			j = next[j];
    		}
    	}
    	
    	return next;
    }
}
