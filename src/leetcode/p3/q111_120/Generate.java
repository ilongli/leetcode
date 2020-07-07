package leetcode.p3.q111_120;

import java.util.ArrayList;
import java.util.List;

/**
 * 118.杨辉三角
 * 
 * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
 *	https://upload.wikimedia.org/wikipedia/commons/0/0d/PascalTriangleAnimated2.gif
 *
 *	在杨辉三角中，每个数是它左上方和右上方的数的和。
 *	
 *	示例:
 *	
 *	输入: 5
 *	输出:
 *	[
 *	     [1],
 *	    [1,1],
 *	   [1,2,1],
 *	  [1,3,3,1],
 *	 [1,4,6,4,1]
 *	]
 * @author ilongli
 * @email 351365415@qq.com
 */
public class Generate {

	public static void main(String[] args) {
		System.out.println(new Generate().generate(5));
	}
	
    public List<List<Integer>> generate(int numRows) {
    	List<List<Integer>> res = new ArrayList<>();
        int colNum = 1;
        for (int row = 0; row < numRows; row++) {
        	List<Integer> rowList = new ArrayList<>();
        	for (int col = 0; col < colNum; col++) {
        		// 边界
        		if (col == 0 || col == (colNum - 1)) {
        			rowList.add(1);
        		} else {
        			rowList.add(res.get(row - 1).get(col - 1) + res.get(row - 1).get(col));
        		}
        	}
        	res.add(rowList);
        	colNum++;
        }
    	
    	return res;
    }
}
