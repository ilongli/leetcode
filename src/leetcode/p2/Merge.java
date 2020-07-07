package leetcode.p2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 给出一个区间的集合，请合并所有重叠的区间。
 *
 *	示例 1:
 *	
 *	输入: [[1,3],[2,6],[8,10],[15,18]]
 *	输出: [[1,6],[8,10],[15,18]]
 *	解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 *	示例 2:
 *	
 *	输入: [[1,4],[4,5]]
 *	输出: [[1,5]]
 *	解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 * 
 * @author ilongli
 * @email 351365415@qq.com
 */
public class Merge {

	public static void main(String[] args) {
		List<Interval> intervals = new ArrayList<>();
		intervals.add(new Interval(1, 3));
//		intervals.add(new Interval(2, 6));
//		intervals.add(new Interval(8, 10));
//		intervals.add(new Interval(15, 18));
		System.out.println(merge2(intervals));
	}

	public static List<Interval> merge(List<Interval> intervals) {
		
		if (intervals.size() == 0) return Collections.emptyList();
		
		List<Interval> res = new ArrayList<>();
		
		// 排序
		Collections.sort(intervals, (a, b) -> {
			return a.start - b.start;
		});
		
		res.add(intervals.get(0));
		
		// 遍历判断
		for (Interval interaval : intervals) {
			Interval comp = res.get(res.size() - 1);
			if (comp.end >= interaval.start) {
				if (comp.end < interaval.end) {
					comp.start = comp.start;
					comp.end = interaval.end;
				}
			} else {
				res.add(interaval);
			}
		}
		
		return res;
	}

	public static List<Interval> merge2(List<Interval> intervals) {
		
		int n = intervals.size();
		
		if (n == 0) return Collections.emptyList();
		
		List<Interval> res = new ArrayList<>();
		
		int[] starts = new int[n];
		int[] ends = new int[n];
		
		for (int i = 0; i < n; i++) {
			starts[i] = intervals.get(i).start;
			ends[i] = intervals.get(i).end;
		}
		
		Arrays.sort(starts);
		Arrays.sort(ends);
		
		for (int i = 0, j = 0; i < n; i++) {
			if (i == n - 1 || starts[i + 1] > ends[i]) {
				res.add(new Interval(starts[j], ends[i]));
				j = i + 1;
			}
		}
		
		return res;
	}
}

class Interval {
	int start;
	int end;
	Interval() { start = 0; end = 0; }
	Interval(int s, int e) { start = s; end = e; }
	@Override
	public String toString() {
		return "Interval [start=" + start + ", end=" + end + "]";
	}
}