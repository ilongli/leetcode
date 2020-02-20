package leetcode.p3.q131_140;

/**
 * 135.分发糖果
 * 老师想给孩子们分发糖果，有 N 个孩子站成了一条直线，老师会根据每个孩子的表现，预先给他们评分。
 *	你需要按照以下要求，帮助老师给这些孩子分发糖果：
 *	
 *	每个孩子至少分配到 1 个糖果。
 *	相邻的孩子中，评分高的孩子必须获得更多的糖果。
 *	那么这样下来，老师至少需要准备多少颗糖果呢？
 *	
 *	示例 1:
 *	
 *	输入: [1,0,2]
 *	输出: 5
 *	解释: 你可以分别给这三个孩子分发 2、1、2 颗糖果。
 *	示例 2:
 *	
 *	输入: [1,2,2]
 *	输出: 4
 *	解释: 你可以分别给这三个孩子分发 1、2、1 颗糖果。
 *	     第三个孩子只得到 1 颗糖果，这已满足上述两个条件。
 * 
 * @author ilongli
 * @email 351365415@qq.com
 */
public class Candy {

	public static void main(String[] args) {
		int[] ratings = {3,2,1,0};
		System.out.println(new Candy().candy(ratings));
	}
	
    public int candy(int[] ratings) {
    	int n = ratings.length;
    	if (n <= 1) return n;
        
    	int res = n;
    	int c1 = 1, c2 = 1, compare, before = 0, beforeHead = 0;
    	
    	for (int i = 1; i < n; i++) {
    		compare = Integer.compare(ratings[i - 1], ratings[i]);
    		if (compare > 0) {						// >
    			if (c1 == c2) res++;
    			if (beforeHead == 0) beforeHead = c1 - 1;
    			c1 = 1;
    			before++;
    			if (beforeHead >= before && before != 1) res--;
    		} else {
    			if (compare == 0) c1 = 1;			// ==
    			else res = res + (c1++ - c2) + 1;	// <
    			res += (before - 1) * before / 2;
    			before = 0;
    			beforeHead = 0;
    		}
    		c2 = 1;
    	}
    	if (before != 0) res += (before - 1) * before / 2;
    	return res;
    }
    
    public int candy2(int[] ratings) {
    	int n = ratings.length;
    	int[] candies = new int[n];
    	// 每人至少一颗糖
    	for (int i = 0; i < n; i++) candies[i] = 1;
    	
    	// 正序遍历
    	for (int i = 0; i < n - 1; i++) {
    		// 当前小朋友的rating比后一个小朋友的小，后一个小朋友的糖是当前小朋友的糖加1
    		if (ratings[i] < ratings[i + 1]) candies[i + 1] = candies[i] + 1;
    	}
    	
    	// 倒序遍历
    	for (int i = n - 1; i > 0; i--) {
    		// 当前小朋友的rating比后一个小朋友的小
    		if (ratings[i] < ratings[i - 1]) {
    			// 后一个小朋友的糖果数没有前一个的多，就更新后一个等于前一个加1
    			if (candies[i - 1] <= candies[i]) {
    				candies[i - 1] = candies[i] + 1;
    			}
    		}
    	}
    	
    	// 计算糖果总和
    	int sum = 0;
    	for (int i = 0; i < n; i++) sum += candies[i];
    	
    	return sum;
    }
}