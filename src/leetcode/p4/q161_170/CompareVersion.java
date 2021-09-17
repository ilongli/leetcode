package leetcode.p4.q161_170;

/**
 * 165. 比较版本号
 *
 * 给你两个版本号 version1 和 version2 ，请你比较它们。
 *
 * 版本号由一个或多个修订号组成，各修订号由一个 '.' 连接。每个修订号由 多位数字 组成，可能包含 前导零 。每个版本号至少包含一个字符。修订号从左到右编号，下标从 0 开始，最左边的修订号下标为 0 ，下一个修订号下标为 1 ，以此类推。例如，2.5.33 和 0.1 都是有效的版本号。
 *
 * 比较版本号时，请按从左到右的顺序依次比较它们的修订号。比较修订号时，只需比较 忽略任何前导零后的整数值 。也就是说，修订号 1 和修订号 001 相等 。如果版本号没有指定某个下标处的修订号，则该修订号视为 0 。例如，版本 1.0 小于版本 1.1 ，因为它们下标为 0 的修订号相同，而下标为 1 的修订号分别为 0 和 1 ，0 < 1 。
 *
 * 返回规则如下：
 *
 * 如果 version1 > version2 返回 1，
 * 如果 version1 < version2 返回 -1，
 * 除此之外返回 0。
 *  
 *
 * 示例 1：
 *
 * 输入：version1 = "1.01", version2 = "1.001"
 * 输出：0
 * 解释：忽略前导零，"01" 和 "001" 都表示相同的整数 "1"
 * 示例 2：
 *
 * 输入：version1 = "1.0", version2 = "1.0.0"
 * 输出：0
 * 解释：version1 没有指定下标为 2 的修订号，即视为 "0"
 * 示例 3：
 *
 * 输入：version1 = "0.1", version2 = "1.1"
 * 输出：-1
 * 解释：version1 中下标为 0 的修订号是 "0"，version2 中下标为 0 的修订号是 "1" 。0 < 1，所以 version1 < version2
 * 示例 4：
 *
 * 输入：version1 = "1.0.1", version2 = "1"
 * 输出：1
 * 示例 5：
 *
 * 输入：version1 = "7.5.2.4", version2 = "7.5.3"
 * 输出：-1
 *  
 *
 * 提示：
 *
 * 1 <= version1.length, version2.length <= 500
 * version1 和 version2 仅包含数字和 '.'
 * version1 和 version2 都是 有效版本号
 * version1 和 version2 的所有修订号都可以存储在 32 位整数 中
 *
 * @author ilongli
 * @date 2021/9/6 9:36
 */
public class CompareVersion {

    public static void main(String[] args) {

        CompareVersion test = new CompareVersion();

//        String version1 = "7.5.2.4", version2 = "7.5.3";
//        String version1 = "1.0", version2 = "1.0.0";
//        String version1 = "1.00.1", version2 = "1.0.2";
//        String version1 = "1.2", version2 = "1.10";
//        String version1 = "1.01", version2 = "1.001";
        String version1 = "0.1", version2 = "1.1";

        System.out.println(test.compareVersion2(version1, version2));

//        System.out.println(version1.substring(5, 1));

    }

    /**
     * 官方题解
     * @param version1
     * @param version2
     * @return
     */
    public int compareVersion3(String version1, String version2) {
        int n = version1.length(), m = version2.length();
        int i = 0, j = 0;
        while (i < n || j < m) {
            int x = 0;
            for (; i < n && version1.charAt(i) != '.'; ++i) {
                x = x * 10 + (version1.charAt(i) - '0');
            }
            ++i; // 跳过点号
            int y = 0;
            for (; j < m && version2.charAt(j) != '.'; ++j) {
                y = y * 10 + (version2.charAt(j) - '0');
            }
            ++j; // 跳过点号
            if (x != y) {
                return x > y ? 1 : -1;
            }
        }
        return 0;
    }

    public int compareVersion2(String version1, String version2) {

        int l1 = version1.length(), l2 = version2.length();
        char[] c1 = version1.toCharArray(), c2 = version2.toCharArray();
        int lp = 0, rp = 0, tp = 0;

        while (lp < l1 || rp < l2) {

            tp = lp;
            while (tp < l1) {
                char c = c1[tp];
                if (c == '0' && lp == tp) {
                    lp++;
                    tp++;
                    continue;
                } else if (c == '.') {
                    break;
                }
                tp++;
            }

            int n1 = tp == lp ? 0 : Integer.parseInt(version1.substring(lp, tp));
            lp = tp + 1;

            tp = rp;
            while (tp < l2) {
                char c = c2[tp];
                if (c == '0' && rp == tp) {
                    rp++;
                    tp++;
                    continue;
                } else if (c == '.') {
                    break;
                }
                tp++;
            }

            int n2 = tp == rp ? 0 : Integer.parseInt(version2.substring(rp, tp));
            rp = tp + 1;

            int compare = n1 - n2;
            if (compare > 0) return 1;
            else if (compare < 0) return -1;
        }

        return 0;
    }

    public int compareVersion(String version1, String version2) {

        String[] split1 = version1.split("\\.");
        String[] split2 = version2.split("\\.");

        int l1 = split1.length, l2 = split2.length, index = 0;

        while (index < l1 || index < l2) {

            String s1 = index < l1 ? removePrefixZero(split1[index]) : "0";
            String s2 = index < l2 ? removePrefixZero(split2[index]) : "0";

            int n1 = Integer.parseInt(s1);
            int n2 = Integer.parseInt(s2);
            int compare = n1 - n2;
            if (compare > 0) return 1;
            else if (compare < 0) return -1;

            index++;
        }

        return 0;
    }

    public String removePrefixZero(String str) {
        int len = str.length(), i = 0;
        while (i < len && str.charAt(i) == '0') {
            i++;
        }
        if (i == len) return "0";
        return str.substring(i);
    }

}
