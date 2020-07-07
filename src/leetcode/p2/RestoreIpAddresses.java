package leetcode.p2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
 *	示例:
 *	
 *	输入: "25525511135"
 *	输出: ["255.255.11.135", "255.255.111.35"]
 * @author ilongli
 * @email 351365415@qq.com
 */
public class RestoreIpAddresses {

	public static void main(String[] args) {
		System.out.println(new RestoreIpAddresses().restoreIpAddresses("11111"));
	}
	
	
	List<String> res = new ArrayList<>();
	LinkedList<String> segments = new LinkedList<>();
	String s = "";
	int len = 0;
	
    public List<String> restoreIpAddresses(String s) {
    	this.len = s.length();
    	if (this.len < 4) return res;
    	
    	this.s = s;
    	
    	backtrack(-1, 3);
    	
    	return res;
    }
    
    /**
     * 回溯
     * @param pre_pos	上一个放置的点的位置
     * @param dots		待放置点的数量
     */
    public void backtrack(int pre_pos, int dots) {
    	// 取出下一个点的最大可放置的位置(不能超过字符串的长度)
    	int max_pos = Math.min(len - 1, pre_pos + 4);
    	// 逐个位置放置点(最多三个位置)
    	for (int cur_pos = pre_pos + 1; cur_pos < max_pos; cur_pos++) {
    		// 取出当前放置点与上一个放置点之间的数字，并检查有效性
    		String segment = s.substring(pre_pos + 1, cur_pos + 1);
    		if (vaild(segment)) {
    			// 如果有效，则放置改点，并把当前的切割出来的数字放入segments集合中
    			segments.add(segment);
    			// 如果3个点都放置好了，就将当前结果放入集合中
    			if (dots - 1 == 0) {
    				update_output(cur_pos);
    			} else {
    				// 否则就继续放置点
    				backtrack(cur_pos, dots - 1);
    			}
    			// 这个位置的情况全部判断好后，去除这个点，继续下一个位置
    			segments.removeLast();
    		}
    	}
    }

    /**
     * 更新结果集合
     * @param cur_pos	当前点的位置(最后一个点的位置)
     */
    private void update_output(int cur_pos) {
    	// 取出最后切割的数字，并检查其有效性
    	String segment = s.substring(cur_pos + 1, len);
		// 如果当前数字的长度超过3，则无效
    	if (segment.length() <= 3 && vaild(segment)) {
    		segments.add(segment);
    		res.add(String.join(".", segments));
    		segments.removeLast();
    	}
	}

	/**
     * 检查当前切割数字的有效性
     * @param segment	当前切割出来的数字
     * @return
     */
	private boolean vaild(String segment) {
		// 只有当数字只有一位的时候，数字的第一位(最高位)才可能为'0'
		if (segment.charAt(0) == '0') {
			return segment.length() == 1;
		} else {
			// 当前的数字不能超过255
			return Integer.valueOf(segment) <= 255;
		}
	}
}
