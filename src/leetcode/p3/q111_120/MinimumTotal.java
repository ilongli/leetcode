package leetcode.p3.q111_120;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 *	例如，给定三角形：
 *	
 *	[
 *	     [2],
 *	    [3,4],
 *	   [6,5,7],
 *	  [4,1,8,3]
 *	]
 *	自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 *	
 *	说明：
 *	
 *	如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
 * @author ilongli
 * @email 351365415@qq.com
 */
public class MinimumTotal {

	public static void main(String[] args) {
		List<List<Integer>> triangle = new ArrayList<>();
		triangle.add(Arrays.asList(   2));
		triangle.add(Arrays.asList(  3,4));
		triangle.add(Arrays.asList( 6,5,7));
		triangle.add(Arrays.asList(4,9,8,1));
		System.out.println(new MinimumTotal().minimumTotal2(triangle));
	}
	
	/**
	 * 递归(超出时间限制)
	 */
	private int total = 0;
	private int size = 0;
	private boolean isInit = false;
	
    public int minimumTotal(List<List<Integer>> triangle) {
        
    	this.size = triangle.size();
    	
    	if (this.size == 0) return 0;
    	
    	helper(1, triangle.get(0).get(0), 0, triangle);
    	
    	return this.total;
    }
    
    private void helper(int level, int sum, int idx, List<List<Integer>> triangle) {
    	
    	// 到底了
    	if (level == this.size) {
    		if (this.isInit) {
    			this.total = Math.min(this.total, sum);
    		} else {
    			this.total = sum;
    			this.isInit = true;
    		}
    	} else {
    		helper(level + 1, sum + triangle.get(level).get(idx), idx, triangle);
    		helper(level + 1, sum + triangle.get(level).get(idx + 1), idx + 1, triangle);
    	}
    }

    
    /**
     * 动态规划
     */
    public int minimumTotal2(List<List<Integer>> triangle) {
    	
    	int size = triangle.size();
    	
    	// 判空
    	if (size == 0) return 0;
    	
    	int[] dp = new int[size];
    	
    	dp[0] = triangle.get(0).get(0);
    	
    	for (int i = 1; i < size; i++) {
    		// 倒着来
    		for (int j = i; j >= 0; j--) {
    			if (j == i) {
    				dp[j] = dp[j - 1] + triangle.get(i).get(j);
    			} else if (j == 0) {
    				dp[j] = dp[j] + triangle.get(i).get(j);
    			} else {
    				dp[j] = Math.min(dp[j], dp[j - 1]) + triangle.get(i).get(j);
    			}
    		}
    	}

    	int res = dp[0];
    	for (int i = 1; i < size; i++) {
    		if (dp[i] < res) res = dp[i];
    	}
    	
    	return res;
    }
}
