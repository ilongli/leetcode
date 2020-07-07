package leetcode.p3.q121_130;

/**
 * 123.买卖股票的最佳时机III
 * 
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 *	设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
 *	
 *	注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *	
 *	示例 1:
 *	
 *	输入: [3,3,5,0,0,3,1,4]
 *	输出: 6
 *	解释: 在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
 *	     随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
 *	示例 2:
 *	
 *	输入: [1,2,3,4,5]
 *	输出: 4
 *	解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。   
 *	     注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。   
 *	     因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 *	示例 3:
 *	
 *	输入: [7,6,4,3,1] 
 *	输出: 0 
 *	解释: 在这个情况下, 没有交易完成, 所以最大利润为 0。
 * @author ilongli
 * @email 351365415@qq.com
 */
public class MaxProfit3 {

	public static void main(String[] args) {
		int[] prices = new int[] {1,2,5,4,7};
		System.out.println(new MaxProfit3().maxProfit(prices));
	}
	
	/**
	 * 状态机
	 * 参考：https://leetcode.wang/leetcode-123-Best-Time-to-Buy-and-Sell-StockIII.html
	 * s1表示第一次买入后当前的钱
	 * s2表示第一次卖出后当前的钱
	 * s3表示第二次买入后当前的钱
	 * s4表示第二次卖出后当前的钱
	 */
	 public int maxProfit3(int[] prices) {
		 if (prices.length == 0) return 0;
		 
		 int s1 = - prices[0], s2 = Integer.MIN_VALUE, s3 = s2, s4 = s3;
		 
		 for (int i = 1; i < prices.length; i++) {
			 s1 = Math.max(s1, - prices[i]);	// 买入价格更低的股
			 s2 = Math.max(s2, s1 + prices[i]);	// 卖出当前股，或者不操作
			 s3 = Math.max(s3, s2 - prices[i]);	// 第二次买入，或者不操作
			 s4 = Math.max(s4, s3 + prices[i]);	// 第二次卖出，或者不操作
		 }
		 return Math.max(0, s4);
	 }
	
	/**
	 * 动态规划
	 * 参考：https://leetcode.wang/leetcode-123-Best-Time-to-Buy-and-Sell-StockIII.html
	 */
	public int maxProfit2(int[] prices) {
		if (prices.length == 0) return 0;
		
		int dp1 = 0, dp2 = 0, min1 = prices[0], min2 = prices[2];
		
		for (int i = 1; i < prices.length; i++) {
			min1 = Math.min(prices[i] - 0, min1);
			dp1 = Math.max(dp1, prices[i] - min1);
			
			min2 = Math.min(prices[i] - dp1, min2);
			dp2 = Math.max(dp2, prices[i] - min2); 
		}
		
		return dp2;
	}
	
	
	/**
	 *  
	 */
	// [3,3,5,0,0,3,1,4]
	// [1,7,3,3,3,0,6,5,10]
    public int maxProfit(int[] prices) {
    	if (prices.length < 2) return 0;
    	
    	int maxLeftIdx = 0, maxRightIdx = 0, curMinIdx = 0, profit = 0;
    	
    	// 找出最大增加区域
    	for (int i = 1; i < prices.length; i++) {
    		if (prices[i] < prices[curMinIdx]) {
    			curMinIdx = i;
    		} else {
    			int curProfit = prices[i] - prices[curMinIdx];
    			if (curProfit > profit) {
    				profit = curProfit;
    				maxLeftIdx = curMinIdx;
    				maxRightIdx = i;
    			}
    		}
    	}
    	
    	// 分隔后的利益(如果有)
    	int splitProfit = profit;
    	if (maxRightIdx - maxLeftIdx > 2) {
    		// 找出区域内的最小值(不包括边界)
    		int rangeMax = prices[maxLeftIdx + 1], insideProfit = 0;
     		for (int i = maxLeftIdx + 2; i < maxRightIdx; i++) {
        		if (prices[i] > rangeMax) {
        			rangeMax = prices[i];
        		} else {
        			insideProfit = Math.max(insideProfit, rangeMax - prices[i]);
        		}
    		}
     		if (insideProfit > 0) {
     			splitProfit = insideProfit + profit;
     		}
    	}
    	
    	int leftRangeMax = getMaxProfit(prices, 0, maxLeftIdx - 1);
    	int rightRangeMax = getMaxProfit(prices, maxRightIdx + 1, prices.length - 1);
    	
    	return Math.max(Math.max(splitProfit, profit + leftRangeMax), Math.max(splitProfit, profit + rightRangeMax));
    }
    
    private int getMaxProfit(int[] prices, int left, int right) {
    	if (right - left < 1) return 0;
    	int min = prices[left], res = 0;
    	for (int i = left + 1; i <= right; i++) {
    		if (prices[i] < min) {
    			min = prices[i];
    		} else {
    			res = Math.max(res, prices[i] - min);
    		}
    	}
    	return res;
    }
}
