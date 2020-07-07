package leetcode.p2;

/**
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 *	每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *	
 *	注意：给定 n 是一个正整数。
 *	
 *	示例 1：
 *	
 *	输入： 2
 *	输出： 2
 *	解释： 有两种方法可以爬到楼顶。
 *	1.  1 阶 + 1 阶
 *	2.  2 阶
 *	示例 2：
 *	
 *	输入： 3
 *	输出： 3
 *	解释： 有三种方法可以爬到楼顶。
 *	1.  1 阶 + 1 阶 + 1 阶
 *	2.  1 阶 + 2 阶
 *	3.  2 阶 + 1 阶
 * @author ilongli
 * @email 351365415@qq.com
 */
public class ClimbStairs {

	public static void main(String[] args) {
		System.out.println(climbStairs(30));
	}
	
    public static int climbStairs(int n) {
        int[] stairs = new int[n];
        stairs[0] = 1;
        for (int i = 1; i < n; i++) {
        	if (i == 1) {
        		stairs[i] = 2;
        	} else {
        		stairs[i] = stairs[i - 1] + stairs[i - 2];
        	}
        }
    	return stairs[n - 1];
    }
	
}
