package leetcode.p1;

import java.util.Stack;

public class IsValid {

	public static void main(String[] args) {
		
		System.out.println(isValid("()"));
		
	}
	
    public static boolean isValid(String s) {
    	
    	Stack<Character> brackets = new Stack<>();
    	
    	for (char c : s.toCharArray()) {
    		// 如果是左括号则入栈
    		if (c == '(' || c == '[' || c == '{') {
    			brackets.push(c);
    		}
    		// 如果是右括号则出栈
    		if (c == ')' || c == ']' || c == '}') {
    			// 如果此时brackets为空，说明无效，直接返回false
    			if (brackets.isEmpty()) return false;
    			// 如果出栈的括号与c不匹配，说明无效，直接返回false
    			if (brackets.pop() != (c == ')' ? c - 1 : c - 2)) return false; 
    		}
    	}
    	// brackets为空说明全部括号匹配成功
        return brackets.isEmpty();
    }
}
