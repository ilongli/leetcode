package leetcode.p4.q161_170;

/**
 * 161. 相隔为 1 的编辑距离
 *
 * 给定两个字符串 s 和 t，判断他们的编辑距离是否为 1。
 *
 * 注意：
 *
 * 满足编辑距离等于 1 有三种可能的情形：
 *
 * 往 s 中插入一个字符得到 t
 * 从 s 中删除一个字符得到 t
 * 在 s 中替换一个字符得到 t
 * 示例 1：
 *
 * 输入: s = "ab", t = "acb"
 * 输出: true
 * 解释: 可以将 'c' 插入字符串 s 来得到 t。
 * 示例 2:
 *
 * 输入: s = "cab", t = "ad"
 * 输出: false
 * 解释: 无法通过 1 步操作使 s 变为 t。
 * 示例 3:
 *
 * 输入: s = "1203", t = "1213"
 * 输出: true
 * 解释: 可以将字符串 s 中的 '0' 替换为 '1' 来得到 t。
 *
 * @author ilongli
 * @date 2021/8/31 9:38
 */
public class IsOneEditDistance {

    public static void main(String[] args) {

    }

    public boolean isOneEditDistance(String s, String t) {

        // 如果两个字符串的长度相差2个或以上，直接返回false
        int offset = Math.abs(s.length() - t.length());
        if (offset >= 2) return false;

        if (offset == 1) {
            // 如果长度相差1

            // 区分长短
            String longer = (s.length() > t.length()) ? s : t;
            String shorter = longer.equals(s) ? t : s;

            // 从左到右遍历，找到不同的位置，
            for (int i = 0; i < shorter.length(); i++) {
                if (longer.charAt(i) != shorter.charAt(i)) {
                    // 长度去掉一个字符，剩下的和短的比较，如果相同，则返回true
                    return longer.substring(i + 1).equals(shorter.substring(i));
                }
            }

            return true;
        } else {
            // 如果长度相同
            // 则对应的位置只能有一个字符不同
            int diffCnt = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) != t.charAt(i)) diffCnt++;
            }
            return diffCnt == 1;
        }

    }
}
