package leetcode.p4.q151_160;

import java.util.Stack;

/**
 * 151.翻转字符串里的单词
 *给定一个字符串，逐个翻转字符串中的每个单词。
 *
 * 示例 1：
 *
 * 输入: "the sky is blue"
 * 输出: "blue is sky the"
 * 示例 2：
 *
 * 输入: "  hello world!  "
 * 输出: "world! hello"
 * 解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 示例 3：
 *
 * 输入: "a good   example"
 * 输出: "example good a"
 * 解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 *  
 *
 * 说明：
 *
 * 无空格字符构成一个单词。
 * 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 *  
 *
 * 进阶：
 *
 * 请选用 C 语言的用户尝试使用 O(1) 额外空间复杂度的原地解法。
 *
 * @author ilongli
 * @date 2020/7/9 9:36
 */
public class ReverseWords {

    public static void main(String[] args) {
        String s = "a good   example";
        ReverseWords reverseWords = new ReverseWords();

//        System.out.println(reverseWords.reverseWords2(s));

    }


    public String reverseWords2(String s) {

        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        int mark = -1;

        for (int idx = s.length() - 1; idx >= 0; idx--) {

            char c = s.charAt(idx);

            if (c == 32 || c == 160) {
                if (mark != -1) {
                    sb.append(s.substring(idx + 1, mark + 1)).append(" ");
                    mark = -1;
                }
            } else {
                if (mark == -1) {
                    mark = idx;
                }
            }
        }

        if (mark != -1) {
            sb.append(s.substring(0, mark + 1));
            return sb.toString();
        }

        if (sb.length() > 0) {
            return sb.substring(0, sb.length() - 1);
        }

        return "";
    }


    public String reverseWords(String s) {

        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        char[] chars = s.toCharArray();

        for (int idx = chars.length - 1; idx >= 0; idx--) {

            char _char = chars[idx];

            if (_char == 160 || _char == 32) {
                if (stack.size() > 0) {
                    while (stack.size() > 0) {
                        sb.append(stack.pop());
                    }
                    sb.append(" ");
                }
            } else {
                stack.push(_char);
            }
        }

        if (stack.size() > 0) {
            while (stack.size() > 0) {
                sb.append(stack.pop());
            }
            return sb.toString();
        }

        if (sb.length() > 0) {
            return sb.substring(0, sb.length() - 1);
        }

        return "";
    }

}
