package leetcode.p1;

/**
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
 *
 *	示例 1:
 *	
 *	输入: 2.00000, 10
 *	输出: 1024.00000
 *	示例 2:
 *	
 *	输入: 2.10000, 3
 *	输出: 9.26100
 *	示例 3:
 *	
 *	输入: 2.00000, -2
 *	输出: 0.25000
 *	解释: 2^-2 = 1/2^2 = 1/4 = 0.25
 *	说明:
 *	
 *	-100.0 < x < 100.0
 *	n 是 32 位有符号整数，其数值范围是 [−2^31, 2^31 − 1] 。
 */
public class MyPow {
	
	public static void main(String[] args) {
		System.out.println(myPow(2, 6));
	}
	
	// 2 6
	// 6 / 2 = 3	2 * 2 = 4
	// 3 / 2 = 1	2
	// 1 / 2 = 0	1
	
	// 递归的解法
	public static double myPow(double x, int n) {
		if (n == 0) return 1;
		double half = myPow(x, n / 2);
		if (n % 2 == 0) return half * half;
		if (n > 0) return half * half * x;
		return half * half / x;
	}
	
	// 迭代的解法
    public static double myPow2(double x, int n) {
    	double res = 1.0;
    	for (int i = n; i != 0; i /= 2) {
    		if (i % 2 != 0) res *= x;
    		x *= x;
    	}
    	return n < 0 ? 1 / res : res;
    }
}
