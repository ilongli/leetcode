package leetcode.p3.q121_130;

/**
 * 125.验证回文串
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 *	说明：本题中，我们将空字符串定义为有效的回文串。
 *	
 *	示例 1:
 *	
 *	输入: "A man, a plan, a canal: Panama"
 *	输出: true
 *	示例 2:
 *	
 *	输入: "race a car"
 *	输出: false
 * 
 * 
 * @author ilongli
 * @email 351365415@qq.com
 */
public class IsPalindrome {

	public static void main(String[] args) {
		System.out.println(new IsPalindrome().isPalindrome("race a car"));
		
		
	}
	
    public boolean isPalindrome(String s) {
    	
        char[] sc = s.toCharArray();

        int head, tail;
        
        for (int i = 0, j = s.length() - 1; i <= j;) {
        	head = check((int) sc[i]);
        	if (head == -1) {
        		i++;
        		continue;
        	}
        	tail = check((int) sc[j]);
        	if (tail == -1) {
        		j--;
        		continue;
        	}
        	if (head != tail) {
        		return false;
        	} else {
        		i++;
        		j--;
        	}
        }
        
    	
    	return true;
    }
    
    private int check(int c) {
    	// 数字
    	if (c >= 48 && c <= 57) return c;
    	// 小写字母
    	if (c >= 97 && c <= 122) return c;
    	// 大写字母
    	if (c >= 65 && c <= 90) return c + 32;
    	
    	return -1;
    }
}
