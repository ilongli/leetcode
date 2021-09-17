package leetcode.p4.q171_180;

/**
 * 171. Excel 表列序号
 *
 * 给你一个字符串 columnTitle ，表示 Excel 表格中的列名称。返回该列名称对应的列序号。
 *
 *  
 *
 * 例如，
 *
 *     A -> 1
 *     B -> 2
 *     C -> 3
 *     ...
 *     Z -> 26
 *     AA -> 27
 *     AB -> 28
 *     ...
 *  
 *
 * 示例 1:
 *
 * 输入: columnTitle = "A"
 * 输出: 1
 * 示例 2:
 *
 * 输入: columnTitle = "AB"
 * 输出: 28
 * 示例 3:
 *
 * 输入: columnTitle = "ZY"
 * 输出: 701
 * 示例 4:
 *
 * 输入: columnTitle = "FXSHRXW"
 * 输出: 2147483647
 *  
 *
 * 提示：
 *
 * 1 <= columnTitle.length <= 7
 * columnTitle 仅由大写英文组成
 * columnTitle 在范围 ["A", "FXSHRXW"] 内
 *
 * @author ilongli
 * @date 2021/9/11 10:32
 */
public class TitleToNumber {

    public static void main(String[] args) {

        TitleToNumber test = new TitleToNumber();

        String columnTitle = "AA";

        System.out.println(test.titleToNumber(columnTitle));

    }

    public int titleToNumber(String columnTitle) {
        int res = 0;
        for (int i = 0; i < columnTitle.length(); i++) {
            char c = columnTitle.charAt(i);
            int curNum = c - 'A' + 1;
            res = res * 26 + curNum;
        }
        return res;
    }

}
