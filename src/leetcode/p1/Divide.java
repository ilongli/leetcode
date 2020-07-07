package leetcode.p1;

/**
 * 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 *	返回被除数 dividend 除以除数 divisor 得到的商。
 *	
 *	示例 1:
 *	
 *	输入: dividend = 10, divisor = 3
 *	输出: 3
 *	示例 2:
 *	
 *	输入: dividend = 7, divisor = -3
 *	输出: -2
 */
public class Divide {
	public static void main(String[] args) {
		System.out.println(divide(-13, 3));
	}
	
    public static int divide(int dividend, int divisor) {
    	
    	// 溢出判断
    	if (dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;
    	
    	int quotient = 0;
    	
    	// 均为正数
    	if (dividend >= 0 && divisor > 0) {
        	while (dividend >= divisor) {
        		dividend -= divisor;
        		quotient++;
        	}
        	return quotient;
    	}
    	
    	// 均为负数
    	if (dividend < 0 && divisor < 0) {
        	while (dividend <= divisor) {
        		dividend -= divisor;
        		quotient++;
        	}
        	return quotient;
    	}
    	
    	// 被除数为负，除数为正
    	if (dividend < 0 && divisor > 0) {
    		while (dividend < 0) {
    			dividend += divisor;
    			quotient--;
    		}
    		return dividend == 0 ? quotient : quotient + 1;
    	}
    	
    	// 被除数为正，除数为负
    	if (dividend >= 0 && divisor < 0) {
    		while (dividend > 0) {
    			dividend += divisor;
    			quotient--;
    		}
    		return dividend == 0 ? quotient : quotient + 1;
    	}
    	
    	// 此处应该是除数为0的情况
    	return quotient;
    }
    
    // TODO 使用指数来加快运算
}
