package leetcode.p4.q161_170;

import java.util.Arrays;

/**
 * 164. 最大间距
 *
 * 给定一个无序的数组，找出数组在排序之后，相邻元素之间最大的差值。
 *
 * 如果数组元素个数小于 2，则返回 0。
 *
 * 示例 1:
 *
 * 输入: [3,6,9,1]
 * 输出: 3
 * 解释: 排序后的数组是 [1,3,6,9], 其中相邻元素 (3,6) 和 (6,9) 之间都存在最大差值 3。
 * 示例 2:
 *
 * 输入: [10]
 * 输出: 0
 * 解释: 数组元素个数小于 2，因此返回 0。
 * 说明:
 *
 * 你可以假设数组中所有元素都是非负整数，且数值在 32 位有符号整数范围内。
 * 请尝试在线性时间复杂度和空间复杂度的条件下解决此问题。
 *
 * @author ilongli
 * @date 2021/9/3 9:15
 */
public class MaximumGap {

    public static void main(String[] args) {

        int[] nums = new int[] {2, 8, 18, 28, 48, 52};

        MaximumGap test = new MaximumGap();
        int res = test.maximumGap(nums);
        System.out.println(res);
    }

    /**
     * 该解法实际上就是将[min, max]区间平均分成的N-1份，每份占d
     * 然后分桶，N-1个桶，等于max的单独一个桶，所以总共N个桶
     * 第0~N-1的桶为左闭右开[d*(i-1) + min, d*i + min)   0<=i<=N-1
     * 第N个桶为[max, max]
     *
     * 之后将所有的数字放入这些桶，容易发现，最大间距的两个数字，一定是落在两个不同的桶
     * 利用这个特性，只需每次比较每个第i+1个桶的最小值与第i个桶的最大值之差，取其中的最大值，即为题目答案
     * @param nums
     * @return
     */
    public int maximumGap(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return 0;
        }
        // 取出数组的最小和最大值
        int minVal = Arrays.stream(nums).min().getAsInt();
        int maxVal = Arrays.stream(nums).max().getAsInt();

        // 取 d = ⌊(max−min)/(N−1)⌋ (这里确保了d最小为1)
        // 其实这个d，就是将区间[min, max]，平均分成了N份，每份占d这大（不考虑边际情况）
        // 这里的边际情况指的是(maxVal - minVal) / (n-1) < 1
        int d = Math.max(1, (maxVal - minVal) / (n - 1));

        // 划分桶，实际上，该值等于N（不考虑边际情况）
        int bucketSize = (maxVal - minVal) / d + 1;

        // bucket[n][0]存的是桶的最小值
        // bucket[n][1]存的是桶的最大值
        int[][] bucket = new int[bucketSize][2];
        for (int i = 0; i < bucketSize; ++i) {
            Arrays.fill(bucket[i], -1); // 存储 (桶内最小值，桶内最大值) 对， (-1, -1) 表示该桶是空的
        }

        // 将数字放入桶中，且用一个二维数组记录每个桶的最大和最小值
        for (int i = 0; i < n; i++) {
            int idx = (nums[i] - minVal) / d;
            if (bucket[idx][0] == -1) {
                bucket[idx][0] = bucket[idx][1] = nums[i];
            } else {
                bucket[idx][0] = Math.min(bucket[idx][0], nums[i]);
                bucket[idx][1] = Math.max(bucket[idx][1], nums[i]);
            }
        }

        // 最大间距一定是出现在两个不同的桶之间，而且一定是后一个桶的最小值-前一个桶的最大值
        int ret = 0;
        int prev = -1;
        for (int i = 0; i < bucketSize; i++) {
            if (bucket[i][0] == -1) {
                continue;
            }
            if (prev != -1) {
                ret = Math.max(ret, bucket[i][0] - bucket[prev][1]);
            }
            prev = i;
        }
        return ret;
    }

}
