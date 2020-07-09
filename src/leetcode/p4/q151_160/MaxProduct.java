package leetcode.p4.q151_160;

/**
 * 152.乘积最大子数组
 *给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 *
 * 示例 1:
 *
 * 输入: [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 * 示例 2:
 *
 * 输入: [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 *
 * @author ilongli
 * @date 2020/7/9 16:47
 */
public class MaxProduct {

    public static void main(String[] args) {

        int[] nums = {2,3,-2,4};

        MaxProduct maxProduct = new MaxProduct();

        System.out.println(maxProduct.maxProduct(nums));

    }


    public int maxProduct(int[] nums) {

        return 0;
    }

    /**
     * 无脑迭代法
     */
    public int maxProduct2(int[] nums) {

        int max = 0, cur = 0;

        // 遍历每一个数字
        for (int i = 0; i < nums.length; i++) {

            cur = nums[i];

            if (i == 0) max = cur;
            else max = Math.max(max, cur);

            // 如果为0，直接pass
            if (cur == 0) continue;

            // 遍历后面的，一直乘下去
            for (int j = i + 1; j < nums.length; j++) {
                // 如果为0，直接break
                if (nums[j] == 0) break;
                //
                cur *= nums[j];
                max = Math.max(max, cur);
            }

        }

        return max;
    }
}
