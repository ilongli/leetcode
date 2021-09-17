package leetcode.p4.q171_180;

/**
 * 172. 阶乘后的零
 *
 * 给定一个整数 n ，返回 n! 结果中尾随零的数量。
 *
 * 进阶：你可以设计并实现对数时间复杂度的算法来解决此问题吗？
 *
 *  
 *
 * 示例 1：
 *
 * 输入：n = 3
 * 输出：0
 * 解释：3! = 6 ，不含尾随 0
 * 示例 2：
 *
 * 输入：n = 5
 * 输出：1
 * 解释：5! = 120 ，有一个尾随 0
 * 示例 3：
 *
 * 输入：n = 0
 * 输出：0
 *  
 *
 * 提示：
 *
 * 0 <= n <= 104
 *
 * @author ilongli
 * @date 2021/9/13 10:59
 */
public class TrailingZeroes {

    public static void main(String[] args) {

    }

    public int trailingZeroes(int n) {
        int count = 0;
        while (n > 0) {
            n /= 5;
            count += n;
        }
        return count;
    }

}
