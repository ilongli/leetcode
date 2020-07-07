package leetcode.p1;

import java.util.Arrays;

/**
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 *
 *	示例 1:
 *	
 *	输入: num1 = "2", num2 = "3"
 *	输出: "6"
 *	示例 2:
 *	
 *	输入: num1 = "123", num2 = "456"
 *	输出: "56088"
 *	说明：
 *	
 *	num1 和 num2 的长度小于110。
 *	num1 和 num2 只包含数字 0-9。
 *	num1 和 num2 均不以零开头，除非是数字 0 本身。
 *	不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 */
public class Multiply {

	public static void main(String[] args) {
		System.out.println(multiply("10", "11"));
	}
	
    public static String multiply(String num1, String num2) {
        // check null
    	if (num1.equals("0") || num2.equals("0")) return "0";
    	
    	int n1 = num1.length();
    	int n2 = num2.length();
    	// 最后结果
    	int[] rev = new int[n1 + n2];
    	
    	// 各位相乘
    	for (int i = n1 - 1; i >= 0; i--) {
    		for (int j = n2 - 1; j >= 0; j--) {
    			// 相乘
    			int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
    			// 加上之前i+j+1位置的结果
    			int sum = mul + rev[i + j + 1];
    			// 更新结果
    			rev[i + j] += sum / 10;
    			rev[i + j + 1] = sum % 10;
    		}
    	}
    	
    	StringBuilder sb = new StringBuilder();
    	
    	for (int i = 0; i < rev.length; i++) {
    		if (i == 0 && rev[i] == 0) continue;
    		sb.append(rev[i]);
    	}
    	
    	return sb.toString();
    }

}
