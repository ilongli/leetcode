package leetcode.p2;

import java.util.ArrayList;
import java.util.List;

/**
 * 给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。

 *	按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
 *	
 *	"123"
 *	"132"
 *	"213"
 *	"231"
 *	"312"
 *	"321"
 *	给定 n 和 k，返回第 k 个排列。
 *	
 *	说明：
 *	
 *	给定 n 的范围是 [1, 9]。
 *	给定 k 的范围是[1,  n!]。
 *	示例 1:
 *	
 *	输入: n = 3, k = 3
 *	输出: "213"
 *	示例 2:
 *	
 *	输入: n = 4, k = 9
 *	输出: "2314"
 * @author ilongli
 * @email 351365415@qq.com
 */
public class GetPermutation {

	public static void main(String[] args) {
		
		System.out.println(getPermutation(4, 11));
		
	}
	
    public static String getPermutation(int n, int k) {
    	// check null
    	if (n == 1) return "1";
    	
        int[] factorialGroup = new int[n - 1];
    	// 生成阶乘数组
        int fgCur = 1;
        for (int idx = 1; idx < n; idx++) {
        	fgCur *= idx;
        	factorialGroup[n - idx - 1] = fgCur;
        }
        
        // 生成待选数字集合
        List<String> numList = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
        	numList.add(String.valueOf(i));
        }
    	
        StringBuilder res = new StringBuilder();
        
        // 取出对应位置的数字
        for (int idx = 0; idx < (n - 1); idx++) {
        	int quotient = k / factorialGroup[idx];
        	int remainder = k % factorialGroup[idx];
        	
        	// 边界情况判断
        	if (remainder == 0) {
        		if (quotient == 0) {
        			break;
        		} else {
        			res.append(numList.remove(quotient - 1));
        			int start = numList.size() - 1;
        			for (int i = start; i >= 0; i--) {
        				res.append(numList.remove(i));
        			}
        			break;
        		}
        	}
        	
        	res.append(numList.remove(quotient));
        	
        	k = remainder;
        }
        
        // 取出剩余的数字
        int size = numList.size();
		for (int i = 0; i < size; i++) {
			res.append(numList.remove(i));
		}
        
    	return res.toString();
    }
}
