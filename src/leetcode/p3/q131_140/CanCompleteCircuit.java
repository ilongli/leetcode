package leetcode.p3.q131_140;

/**
 * 134.加油站
 * 在一条环路上有 N 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
 * 	你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
 * 	
 * 	如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。
 * 	
 * 	说明: 
 * 	
 * 	如果题目有解，该答案即为唯一答案。
 * 	输入数组均为非空数组，且长度相同。
 * 	输入数组中的元素均为非负数。
 * 	示例 1:
 * 	
 * 	输入: 
 * 	gas  = [1,2,3,4,5]
 * 	cost = [3,4,5,1,2]
 * 	
 * 	输出: 3
 * 	
 * 	解释:
 * 	从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
 * 	开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
 * 	开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
 * 	开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
 * 	开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
 * 	开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
 * 	因此，3 可为起始索引。
 * 	示例 2:
 * 	
 * 	输入: 
 * 	gas  = [2,3,4]
 * 	cost = [3,4,3]
 * 	
 * 	输出: -1
 * 	
 * 	解释:
 * 	你不能从 0 号或 1 号加油站出发，因为没有足够的汽油可以让你行驶到下一个加油站。
 * 	我们从 2 号加油站出发，可以获得 4 升汽油。 此时油箱有 = 0 + 4 = 4 升汽油
 * 	开往 0 号加油站，此时油箱有 4 - 3 + 2 = 3 升汽油
 * 	开往 1 号加油站，此时油箱有 3 - 3 + 3 = 3 升汽油
 * 	你无法返回 2 号加油站，因为返程需要消耗 4 升汽油，但是你的油箱只有 3 升汽油。
 * 	因此，无论怎样，你都不可能绕环路行驶一周。
 * 
 * 
 * @author ilongli
 * @email 351365415@qq.com
 */
public class CanCompleteCircuit {

	public static void main(String[] args) {
		int[] gas = {2, 3, 4};
		int[] cost = {3, 4, 3};
		System.out.println(new CanCompleteCircuit().canCompleteCircuit(gas, cost));
	}
	
	/**
	 * 1.
	 */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        
    	for (int i = 0; i < gas.length; i++) {
    		if (drive(i, gas, cost)) return i;
    	}
    	
    	return -1;
    }
    
    private boolean drive(int start, int[] gas, int[] cost) {
    	int petrol = 0;
    	for (int i = start; i < gas.length; i++) {
    		petrol += gas[i];
    		petrol -= cost[i];
    		if (petrol < 0) return false;
    	}
    	
    	for (int i = 0; i <= start; i++) {
    		petrol += gas[i];
    		petrol -= cost[i];
    		if (petrol < 0) return false;
    	}
    	
    	return true;
    }
    
    /**
     * 2.
     */
    // gas  = [1,2,3,4,5]
    // cost = [3,4,5,1,2]
    // re	= [-2,-2,-2,3,3]
    public int canCompleteCircuit2(int[] gas, int[] cost) {
    	int n = gas.length;
    	for (int i = 0; i < n; i++) {
    		int j = i;
    		int remain = gas[i];
    		while (remain - cost[j] >= 0) {
    			// 减去花费的加上新的点的补给
    			remain = remain - cost[j] + gas[(j + 1) % n];
    			j = (j + 1) % n;
    			// j回到了i
    			if (j == i) return i;
    		}
    		// 最远距离绕到了i的前面，所以i后边的都不可能绕一圈了
    		if (j < i) return -1;
    		
    		// i直接跳到j，外层for循环执行i++，相当于从j+1开始考虑
    		i = j;
    	}
    	return -1;
    }
}
