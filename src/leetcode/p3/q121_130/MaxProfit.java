package leetcode.p3.q121_130;

/**
 * 121.买卖股票的最佳时机
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 *	如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。
 *	
 *	注意你不能在买入股票前卖出股票。
 *	
 *	示例 1:
 *	
 *	输入: [7,1,5,3,6,4]
 *	输出: 5
 *	解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 *	     注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
 *	示例 2:
 *	
 *	输入: [7,6,4,3,1]
 *	输出: 0
 *	解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 * @author ilongli
 * @email 351365415@qq.com
 */
public class MaxProfit {

	public static void main(String[] args) {
		int[] prices = new int[]{7,5,7,2,3,1};
		System.out.println(new MaxProfit().maxProfit2(prices));
	}
	// [7,6,4,2,3,1]
	// [7,6,4,2,3,1]
	// [7,1,5,3,6,4]
	/**
	 * 暴力法
	 */
    public int maxProfit(int[] prices) {

    	int res = 0;
    	
    	for (int i = 0; i < prices.length - 1; i++) {
    		int buy = prices[i];
    		int sell = prices[i + 1];
    		for (int j = i + 2; j < prices.length; j++) {
    			if (prices[j] > sell) sell = prices[j];
    		}
    		if (sell > buy) res = Math.max(res, sell - buy);
    	}
    	
    	return res;
    }

    /**
     * 优化(不断更新最小值)
     */
    // [3,5,2,2,3]
    public int maxProfit2(int[] prices) {
    	if (prices.length < 2) return 0;
    	int min = prices[0], res = 0;
    	
    	for (int i = 1; i < prices.length; i++) {
    		if (prices[i] < min) {
    			min = prices[i];
    		} else {
    			res = Math.max(res, prices[i] - min);
    		}
    	}
    	
    	return res;
    }
    
    /**
     * 动态规划(转换为最大子数列问题)
     * 转换规则：当天的价格减去前一天的价格
     * 如：[1,6,2,8] -> [0,5,-4,6]
     * 然后用Kadane算法求出最大子数列
     */
    public int maxProfit3(int[] prices) {
    	
    	int n = prices.length;
    	int dp = 0;
    	int max = 0;
    	for (int i = 1; i < n; i++) {
    		int num = prices[i] - prices[i - 1];
    		dp = Math.max(dp + num, num);
    		max = Math.max(max, dp);
    	}
    	
    	return max;
    }
}
