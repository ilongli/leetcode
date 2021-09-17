package leetcode.p4.q161_170;

/**
 * 168. Excel表列名称
 *
 * 给你一个整数 columnNumber ，返回它在 Excel 表中相对应的列名称。
 *
 * 例如：
 *
 * A -> 1
 * B -> 2
 * C -> 3
 * ...
 * Z -> 26
 * AA -> 27
 * AB -> 28
 * ...
 *  
 *
 * 示例 1：
 *
 * 输入：columnNumber = 1
 * 输出："A"
 * 示例 2：
 *
 * 输入：columnNumber = 28
 * 输出："AB"
 * 示例 3：
 *
 * 输入：columnNumber = 701
 * 输出："ZY"
 * 示例 4：
 *
 * 输入：columnNumber = 2147483647
 * 输出："FXSHRXW"
 *  
 *
 * 提示：
 *
 * 1 <= columnNumber <= 2^31 - 1
 *
 * @author ilongli
 * @date 2021/9/8 9:49
 */
public class ConvertToTitle {

    public static void main(String[] args) {

        ConvertToTitle test = new ConvertToTitle();

        System.out.println(test.convertToTitle(2147483647));

    }

    public String convertToTitle(int columnNumber) {

        StringBuilder sb = new StringBuilder();
        int next = 0, cur = 0;

        while (columnNumber != 0) {
            next = columnNumber / 26;
            cur = columnNumber % 26;
            if (cur == 0) {
                next--;
                sb.insert(0, 'Z');
            } else {
                sb.insert(0, (char) (cur + 64));
            }
            columnNumber = next;
        }

        return sb.toString();
    }

}
