package leetcode.p3.q111_120;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 119.杨辉三角II
 * 给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。
 *	https://upload.wikimedia.org/wikipedia/commons/0/0d/PascalTriangleAnimated2.gif
 *	在杨辉三角中，每个数是它左上方和右上方的数的和。
 *	
 *	示例:
 *	
 *	输入: 3
 *	输出: [1,3,3,1]
 *	进阶：
 *	
 *	你可以优化你的算法到 O(k) 空间复杂度吗？
 * 
 * @author ilongli
 * @email 351365415@qq.com
 */
public class GetRow {

	public static void main(String[] args) {
		System.out.println(new GetRow().getRow(3));
	}
	
    public List<Integer> getRow(int rowIndex) {
    	LinkedList<Integer> res = new LinkedList<>();
    	
    	for (int row = 0; row <= rowIndex; row++) {
    		// 在最后放一个1
    		res.addLast(1);
    		// 除了最前和最后，中间的所有数字倒着进行处理：
    		// 当前的值为前面的值加上本身的值
    		for (int col = (res.size() - 2); col > 0; col--) {
    			res.set(col, res.get(col) + res.get(col - 1));
    		}
    	}
    	
    	return res;
    }
    
    /**
     * 公式
     */
    public List<Integer> getRow2(int rowIndex) {
    	List<Integer> res = new ArrayList<Integer>(rowIndex + 1);
    	long num = 1;
    	for (int i = 0; i <= rowIndex; i++) {
    		res.add((int) num);
    		num = num * (rowIndex - i) / (i + 1);
    	}
    	return res;
    }
}
