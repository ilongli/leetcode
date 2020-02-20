package leetcode.p2;

/**
 * 实现 int sqrt(int x) 函数。
 *	计算并返回 x 的平方根，其中 x 是非负整数。
 *	
 *	由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 *	
 *	示例 1:
 *	
 *	输入: 4
 *	输出: 2
 *	示例 2:
 *	
 *	输入: 8
 *	输出: 2
 *	说明: 8 的平方根是 2.82842..., 
 *	     由于返回类型是整数，小数部分将被舍去。
 * @author ilongli
 * @email 351365415@qq.com
 */
public class MySqrt {

	public static void main(String[] args) {
		System.out.println(mySqrt(Integer.MAX_VALUE));
	}
	
    public static int mySqrt(int x) {
    	if (x == 0 || x == 1) return x;
        int min = 0, max = x, mid = 0;
        while (min != max && (min+1) != max) {
        	mid = (min + max + 1) / 2;
        	if (x / mid < mid) {
        		max = mid;
        	} else {
        		min = mid;
        	}
        }
        
    	return min;
    }
}
