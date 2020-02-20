package leetcode.p2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给出一个无重叠的 ，按照区间起始端点排序的区间列表。
 *
 *	在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 *	
 *	示例 1:
 *	
 *	输入: intervals = [[1,3],[6,9]], newInterval = [2,5]
 *	输出: [[1,5],[6,9]]
 *	示例 2:
 *	
 *	输入: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 *	输出: [[1,2],[3,10],[12,16]]
 *	解释: 这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
 * 
 * @author ilongli
 * @email 351365415@qq.com
 */
public class Insert {

	public static void main(String[] args) {

//		List<Interval> intervals = new ArrayList<>();
		/*		intervals.add(new Interval(1, 2));
				intervals.add(new Interval(3, 5));
				intervals.add(new Interval(6, 7));
				intervals.add(new Interval(8, 10));
				intervals.add(new Interval(12, 16));*/
//		intervals.add(new Interval(1, 3));
//		intervals.add(new Interval(6, 9));
//		System.out.println(insert2(intervals, new Interval(2 , 5)));
		
		
		int[][] arr = new int[5][2];
		arr[0] = new int[] {1, 2};
		arr[1] = new int[] {3, 5};
		arr[2] = new int[] {6, 7};
		arr[3] = new int[] {8, 10};
		arr[4] = new int[] {12, 16};
		
//		Arrays.stream(arr).map(Arrays::toString).forEach(System.out::println);
		
		int[][] result = insert(arr, new int[] {7, 17});
		Arrays.stream(result).map(Arrays::toString).forEach(System.out::println);
	}
	
    public static int[][] insert(int[][] intervals, int[] newInterval) {
       
    	List<int[]> res = new ArrayList<>();
    	// check null
    	if (intervals.length == 0) {
    		res.add(newInterval);
    		int[][] _res = new int[res.size()][2];
    		return res.toArray(_res);
    	}
    	
    	// 初始化
    	int[] insert = new int[2];
    	int cur = newInterval[0];
    	boolean isInserting = false;
    	boolean isFinish = false;
    	
    	for (int i = 0; i < intervals.length;) {
    		
    		// 如果已经插入结束，则后面的直接插入
    		if (isFinish) {
    			res.add(intervals[i++]);
    			continue;
    		}
    		
    		// 判断插入位置
    		Integer r = check(intervals[i], cur, isInserting);
    		if (r != null) {
    			if (!isInserting) {
    				insert[0] = r;
    				isInserting = true;
    				cur = newInterval[1];
    				continue;
    			} else {
    				insert[1] = r;
    				isFinish = true;
    				res.add(insert);
    				// 
    				if (r < intervals[i][0]) res.add(intervals[i]);
    				i++;
    				continue;
    			}
    		}
    		
    		// 如果未进行插入操作，并且此时没有找到插入位置，则直接插入
    		if (!isInserting) res.add(intervals[i]);
    		i++;
    	}
    	
    	// 如果正在插入，并且没有插入结束，说明插入结束位置落在最右边
    	if (isInserting && !isFinish) {
    		insert[1] = newInterval[1];
    		res.add(insert);
    	}
    	
    	// 如果没有插入操作，并且没有插入结束，说明插入开始和结束位置都落在最右边
    	if (!isInserting && !isFinish) {
    		res.add(newInterval);
    	}
    	
    	
		int[][] _res = new int[res.size()][2];
		return res.toArray(_res);
    }
	
	public static Integer check(int[] interval, int cur, boolean isInserting) {
		int cl = interval[0]; 
		int cr = interval[1];
		
		if (cur < cl) { 
			return cur; 
		} else if (cur <= cr) {
			return isInserting ? cr : cl;
		}
		
		return null;
	}
	
	public static List<Interval> insert2(List<Interval> intervals, Interval newInterval) {
		List<Interval> res = new ArrayList<>();
		// check null
    	if (intervals.size() == 0) {
    		res.add(newInterval);
    		return res;
    	}
    	
    	Interval insert = new Interval();
    	int cur = newInterval.start;
    	boolean isInserting = false;
    	boolean isFinish = false;

    	
    	for (int i = 0; i < intervals.size();) {
    		
    		// 如果已经插入结束，则后面的直接插入
    		if (isFinish) {
    			res.add(intervals.get(i++));
    			continue;
    		}
    		
    		// 判断插入位置
    		Integer r = check(intervals.get(i), cur, isInserting);
    		if (r != null) {
    			if (!isInserting) {
    				insert.start = r;
    				isInserting = true;
    				cur = newInterval.end;
    				continue;
    			} else {
    				insert.end = r;
    				isFinish = true;
    				res.add(insert);
    				// 
    				if (r < intervals.get(i).start) res.add(intervals.get(i));
    				i++;
    				continue;
    			}
    		}
    		
    		// 如果未进行插入操作，并且此时没有找到插入位置，则直接插入
    		if (!isInserting) res.add(intervals.get(i));
    		i++;
    	}
    	
    	// 如果正在插入，并且没有插入结束，说明插入结束位置落在最右边
    	if (isInserting && !isFinish) {
    		insert.end = newInterval.end;
    		res.add(insert);
    	}
    	
    	// 如果没有插入操作，并且没有插入结束，说明插入开始和结束位置都落在最右边
    	if (!isInserting && !isFinish) {
    		res.add(newInterval);
    	}
		return res;
	}
	
	public static Integer check(Interval interval, int cur, boolean isInserting) {
		int cl = interval.start; 
		int cr = interval.end;
		
		if (cur < cl) { 
			return cur; 
		} else if (cur <= cr) {
			return isInserting ? cr : cl;
		}
		
		return null;
	}
	
    public static List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        
    	List<Interval> res = new ArrayList<>();
    	
    	if (intervals.size() == 0) {
    		res.add(newInterval);
    		return res;
    	}
    	
    	int start = 0;
    	boolean isInserting = false;
    	
    	for (int i = 0; i < intervals.size(); i++) {
    		if (!isInserting && intervals.get(i).end >= newInterval.start) {
    			isInserting = true;
    			start = intervals.get(i).start;
    			continue;
    		}
    		if (isInserting && intervals.get(i).start > newInterval.end) {
    			isInserting = false;
    			res.add(new Interval(start, intervals.get(i - 1).end > newInterval.end ? intervals.get(i - 1).end : newInterval.end));
    		}
    		if (isInserting) continue;
    		res.add(intervals.get(i));
    	}
    	
    	if (isInserting) {
    		
    	} else {
    		
    	}
    	
    	return res;
    }

}
