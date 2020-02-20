package leetcode.p1;

import java.util.HashSet;
import java.util.Set;

public class LengthOfLongestSubstring {

	public static void main(String[] args) {
		System.out.println(lengthOfLongestSubstring("pwwkew"));
	}
	
    public static int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int max = 0;
        int num = 0;
        for (char c : s.toCharArray()) {
            if (set.contains(c)) {
                if (num > max) max = num;
                set.clear();
                num = 0;
            }
            set.add(c);
            num++;
        }
        return max>num ? max : num;
    }

}
