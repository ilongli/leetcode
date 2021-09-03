package leetcode.p4.q151_160;

import java.util.HashMap;

/**
 * 159. 至多包含两个不同字符的最长子串
 *
 * 题目描述
 * 给定一个字符串 s ，找出 至多 包含两个不同字符的最长子串 t ，并返回该子串的长度。
 *
 * 示例 1:
 *
 * 输入: "eceba"
 * 输出: 3
 * 解释: t 是 "ece"，长度为3。
 * 示例 2:
 *
 * 输入: "ccaabbb"
 * 输出: 5
 * 解释: t 是 "aabbb"，长度为5。
 *
 * @author ilongli
 * @date 2021/8/28 9:25
 */
public class LengthOfLongestSubstringTwoDistinct {

    public static void main(String[] args) {

        String s = "ccaabbb";
        LengthOfLongestSubstringTwoDistinct test = new LengthOfLongestSubstringTwoDistinct();
        int res = test.lengthOfLongestSubstringTwoDistinct(s);
        System.out.println(res);

    }


    public int lengthOfLongestSubstringTwoDistinct(String s) {

        if (s.length() == 0) return 0;

        // 用map来记录字符的最新坐标
        HashMap<Character, Integer> map = new HashMap<>();
        int res = 0, left = 0;

        for (int i = 0; i < s.length(); i++) {

            char c = s.charAt(i);
            map.put(c, i);

            while (map.size() > 2) {
                char lc = s.charAt(left);
                if (map.get(lc) == left) map.remove(lc);
                left++;
            }

            res = Math.max(res, i - left + 1);
        }

        return res;
    }

}
