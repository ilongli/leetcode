package leetcode.p1;

import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesis {

	public static void main(String[] args) {
		
//		System.out.println(generateParenthesis(3));
		System.out.println(generateParenthesis2(1));
	}
	
	public static List<String> generateParenthesis2(int n) {
        List<String> ans = new ArrayList<>();
        if (n == 0) {
            ans.add("");
        } else {
            for (int c = 0; c < n; ++c)
                for (String left: generateParenthesis2(c))
                    for (String right: generateParenthesis2(n-1-c))
                        ans.add("(" + left + ")" + right);
        }
        return ans;
	}
	
    public static List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
    	backtrack(ans, "", 0, 0, n);
    	return ans;
    }
    
    public static void backtrack(List<String> ans, String cur, 
    		int open, int close, int max) {
    	if (cur.length() == max * 2) {
    		ans.add(cur);
    		return;
    	}
    	
    	if (open < max) {
    		backtrack(ans, cur + "(", open + 1, close, max);
    	}
    	if (close < open) {
    		backtrack(ans, cur + ")", open, close + 1, max);
    	}
    }
	
}
