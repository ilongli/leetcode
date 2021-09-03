package leetcode.p4.q161_170;

import java.util.ArrayList;
import java.util.List;

/**
 * 163. 缺失的区间
 *
 * 给定一个排序的整数数组 nums ，其中元素的范围在 闭区间 [lower, upper] 当中，返回不包含在数组中的缺失区间。
 *
 * 示例：
 *
 * 输入: nums = [0, 1, 3, 50, 75], lower = 0 和 upper = 99,
 * 输出: ["2", "4->49", "51->74", "76->99"]
 *
 * @author ilongli
 * @date 2021/9/2 9:39
 */
public class findMissingRanges {

    public List<String> findMissingRanges(int[] nums, int lower, int upper) {

        List<String> res = new ArrayList<>();

        long low = (long) lower - 1;
        long up = 0;
        for (int i = 0; i <= nums.length; i++) {
            if (i == nums.length) {
                up = (long) upper + 1;
            } else {
                up = nums[i];
            }
            if (up == low + 2) {
                res.add(low + 1 + "");
            } else if (up > low + 2) {
                res.add((low + 1) + "->" + (up - 1));
            }
            low = up;
        }

        return res;
    }

}
