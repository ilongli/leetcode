package leetcode.p4.q151_160;

/**
 * 158. 用 Read4 读取 N 个字符 II
 *
 * 给你一个文件，并且该文件只能通过给定的 read4 方法来读取，请实现一个方法使其能够读取 n 个字符。注意：你的 read 方法可能会被调用多次。
 *
 * read4 的定义：
 *
 * read4 API 从文件中读取 4 个连续的字符，然后将这些字符写入缓冲区数组 buf4 。
 *
 * 返回值是读取的实际字符数。
 *
 * 请注意，read4() 有其自己的文件指针，类似于 C 中的 FILE * fp 。
 *
 * 参数类型: char[] buf4
 * 返回类型: int
 *
 * 注意: buf4[] 是目标缓存区不是源缓存区，read4 的返回结果将会复制到 buf4[] 当中。
 * 下列是一些使用 read4 的例子：
 *
 * https://github.com/doocs/leetcode/blob/main/solution/0100-0199/0158.Read%20N%20Characters%20Given%20Read4%20II%20-%20Call%20multiple%20times/README.md
 *
 * File file("abcde"); // 文件名为 "abcde"， 初始文件指针 (fp) 指向 'a'
 * char[] buf4 = new char[4]; // 创建一个缓存区使其能容纳足够的字符
 * read4(buf4); // read4 返回 4。现在 buf4 = "abcd"，fp 指向 'e'
 * read4(buf4); // read4 返回 1。现在 buf4 = "e"，fp 指向文件末尾
 * read4(buf4); // read4 返回 0。现在 buf4 = ""，fp 指向文件末尾
 * read 方法：
 *
 * 通过使用 read4 方法，实现 read 方法。该方法可以从文件中读取 n 个字符并将其存储到缓存数组 buf 中。您 不能 直接操作文件。
 *
 * 返回值为实际读取的字符。
 *
 * read 的定义：
 *
 * 参数:   char[] buf, int n
 * 返回值: int
 *
 * 注意: buf[] 是目标缓存区不是源缓存区，你需要将结果写入 buf[] 中。
 *
 *
 * 示例 1：
 *
 * File file("abc");
 * Solution sol;
 * // 假定 buf 已经被分配了内存，并且有足够的空间来存储文件中的所有字符。
 * sol.read(buf, 1); // 当调用了您的 read 方法后，buf 需要包含 "a"。 一共读取 1 个字符，因此返回 1。
 * sol.read(buf, 2); // 现在 buf 需要包含 "bc"。一共读取 2 个字符，因此返回 2。
 * sol.read(buf, 1); // 由于已经到达了文件末尾，没有更多的字符可以读取，因此返回 0。
 * 示例 2：
 *
 * File file("abc");
 * Solution sol;
 * sol.read(buf, 4); // 当调用了您的 read 方法后，buf 需要包含 "abc"。 一共只能读取 3 个字符，因此返回 3。
 * sol.read(buf, 1); // 由于已经到达了文件末尾，没有更多的字符可以读取，因此返回 0。
 *
 *
 * 提示：
 *
 * 你 不能 直接操作该文件，文件只能通过 read4 获取而 不能 通过 read。
 * read  函数可以被调用 多次。
 * 请记得 重置 在 Solution 中声明的类变量（静态变量），因为类变量会 在多个测试用例中保持不变，影响判题准确。请 查阅 这里。
 * 你可以假定目标缓存数组 buf 保证有足够的空间存下 n 个字符。
 * 保证在一个给定测试用例中，read 函数使用的是同一个 buf。
 *
 * @author ilongli
 * @date 2021/8/27 9:23
 */
public class Read42 {

    public static void main(String[] args) {


    }

    // 使用一个读指针和写指针来记录上次read到的位置
    private int buffPtr = 0;
    private int buffCnt = 0;
    private char[] buff = new char[4];

    public int read(char[] buf, int n) {

        int ptr = 0;

        while (ptr < n) {
            if (buffPtr == 0) {
                buffCnt = read4(buff);
            }
            if (buffCnt == 0) {
                break;
            }
            while (ptr < n && buffPtr < buffCnt) {
                buf[ptr++] = buff[buffPtr++];
            }
//            if (buffPtr >= buffCnt) {
            // 表示读完了，将buffPtr置0，下次循环进入后会重新read4
            if (buffPtr == buffCnt) {
                buffPtr = 0;
            }
        }

        return ptr;
    }

    private int read4(char[] buffer) {
        //this is a dummy method to make it compile
        return 0;
    }

}
