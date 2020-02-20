package leetcode.p2;

/**
 * 给定一个仅包含大小写字母和空格 ' ' 的字符串，返回其最后一个单词的长度。
* 
* 如果不存在最后一个单词，请返回 0 。
* 
* 说明：一个单词是指由字母组成，但不包含任何空格的字符串。
* 
* 示例:
* 
* 输入: "Hello World"
* 输出: 5
 * @author ilongli
 * @email 351365415@qq.com
 */
public class LengthOfLastWord {

	public static void main(String[] args) {

		System.out.println(lengthOfLastWord2("a"));
		
	}
	
	public static int lengthOfLastWord2(String s) {
		// check null
		if (s.length() == 0) return 0;
		
		boolean start = false;
		int count = 0;
		
		for (int i = (s.length() - 1); i >= 0; i--) {
			char c = s.charAt(i);
			if (c == ' ') {
				if (start)
					return count;
				else
					continue;
			} else {
				count++;
				if (!start) start = true;
			}
		}
		return count;
	}
	
    public static int lengthOfLastWord(String s) {
    	// check null
    	if (s.length() == 0) return 0;
    	
    	int pre = 0, cur = 0;
    	
    	for (int i = 0; i < s.length(); i++) {
    		char c = s.charAt(i);
    		if (c == ' ') cur = 0;
    		else pre = ++cur;
    	}
    	
    	// check end
    	if (s.charAt(s.length() - 1) == ' ') return pre;
    	else return cur;
    }
}
