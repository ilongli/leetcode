package leetcode.p4.q161_170;

import java.util.HashMap;
import java.util.Map;

/**
 * 166. 分数到小数
 *
 * 给定两个整数，分别表示分数的分子 numerator 和分母 denominator，以 字符串形式返回小数 。
 *
 * 如果小数部分为循环小数，则将循环的部分括在括号内。
 *
 * 如果存在多个答案，只需返回 任意一个 。
 *
 * 对于所有给定的输入，保证 答案字符串的长度小于 104 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：numerator = 1, denominator = 2
 * 输出："0.5"
 * 示例 2：
 *
 * 输入：numerator = 2, denominator = 1
 * 输出："2"
 * 示例 3：
 *
 * 输入：numerator = 2, denominator = 3
 * 输出："0.(6)"
 * 示例 4：
 *
 * 输入：numerator = 4, denominator = 333
 * 输出："0.(012)"
 * 示例 5：
 *
 * 输入：numerator = 1, denominator = 5
 * 输出："0.2"
 *  
 *
 * 提示：
 *
 * -231 <= numerator, denominator <= 231 - 1
 * denominator != 0
 *
 * @author ilongli
 * @date 2021/9/7 9:26
 */
public class FractionToDecimal {

    public static void main(String[] args) {




    }

    public String fractionToDecimal(int numerator, int denominator) {
        // 分子为0，直接返回"0"
        if (numerator == 0) {
            return "0";
        }
        StringBuilder fraction = new StringBuilder();
        // If either one is negative (not both)
        // 其中一个是负数，结果也是负数，加上"-"
        if (numerator < 0 ^ denominator < 0) {
            fraction.append("-");
        }
        // Convert to Long or else abs(-2147483648) overflows
        // 转成long型，防止溢出
        long dividend = Math.abs(Long.valueOf(numerator));
        long divisor = Math.abs(Long.valueOf(denominator));
        // 整除，取出整数部门
        fraction.append(String.valueOf(dividend / divisor));
        // 取出余数
        long remainder = dividend % divisor;
        // 如果余数为零，最终结果为整数，直接返回
        if (remainder == 0) {
            return fraction.toString();
        }
        // 否则带有小数，加上小数点: "."
        fraction.append(".");

        // 使用map记录每一位余数出现的位置（相对于整个fraction）
        Map<Long, Integer> map = new HashMap<>();
        while (remainder != 0) {
            // 当相同的余数出现时，说明该余数为重复开始的位置
            if (map.containsKey(remainder)) {
                fraction.insert(map.get(remainder), "(");
                fraction.append(")");
                break;
            }
            map.put(remainder, fraction.length());
            remainder *= 10;
            fraction.append(String.valueOf(remainder / divisor));
            remainder %= divisor;
        }
        return fraction.toString();
    }

}
