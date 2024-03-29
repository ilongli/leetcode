package leetcode.p4.q151_160;

import leetcode.common.ListNode;

/**
 * 160. 相交链表
 * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表没有交点，返回 null 。
 *
 * 图示两个链表在节点 c1 开始相交：
 *
 * https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/14/160_statement.png
 *
 * 题目数据 保证 整个链式结构中不存在环。
 *
 * 注意，函数返回结果后，链表必须 保持其原始结构 。
 *
 *  
 *
 * 示例 1：
 *
 * https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/14/160_example_1.png
 *
 * 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
 * 输出：Intersected at '8'
 * 解释：相交节点的值为 8 （注意，如果两个链表相交则不能为 0）。
 * 从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。
 * 在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
 * 示例 2：
 *
 * https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/14/160_example_2.png
 *
 * 输入：intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
 * 输出：Intersected at '2'
 * 解释：相交节点的值为 2 （注意，如果两个链表相交则不能为 0）。
 * 从各自的表头开始算起，链表 A 为 [0,9,1,2,4]，链表 B 为 [3,2,4]。
 * 在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
 * 示例 3：
 *
 * https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/14/160_example_3.png
 *
 * 输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
 * 输出：null
 * 解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。
 * 由于这两个链表不相交，所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。
 * 这两个链表不相交，因此返回 null 。
 *  
 *
 * 提示：
 *
 * listA 中节点数目为 m
 * listB 中节点数目为 n
 * 0 <= m, n <= 3 * 104
 * 1 <= Node.val <= 105
 * 0 <= skipA <= m
 * 0 <= skipB <= n
 * 如果 listA 和 listB 没有交点，intersectVal 为 0
 * 如果 listA 和 listB 有交点，intersectVal == listA[skipA + 1] == listB[skipB + 1]
 *  
 *
 * 进阶：你能否设计一个时间复杂度 O(n) 、仅用 O(1) 内存的解决方案？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/intersection-of-two-linked-lists
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author ilongli
 * @date 2021/8/30 9:24
 */
public class GetIntersectionNode {

    public static void main(String[] args) {



    }

    /**
     * 思路:
     * 1.先计算出A和B链表的长度la和lb
     * 2.长度较长的链表，先走|la - lb|步
     * 3.然后A，B链表同步走，当nodeA和nodeB时，当前节点即为结果
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        if (headA == null || headB == null) return null;

        ListNode res = null;

        // 1.
        ListNode _headA = headA, _headB = headB;
        int la = 0, lb = 0;
        while (_headA != null || _headB != null) {
            if (_headA != null) {
                _headA = _headA.next;
                la++;
            }
            if (_headB != null) {
                _headB = _headB.next;
                lb++;
            }
        }

        // 2.
        if (la != lb) {
            int offset = Math.abs(la - lb);
            if (la > lb) {
                while (offset != 0) {
                    headA = headA.next;
                    offset--;
                }
            } else {
                while (offset != 0) {
                    headB = headB.next;
                    offset--;
                }
            }
        }

        // 3.
        while (headA != null) {
            if (headA == headB) return headA;
            headA = headA.next;
            headB = headB.next;
        }

        return res;
    }


    /**
     * 官方解法2:
     * https://leetcode-cn.com/problems/intersection-of-two-linked-lists/solution/xiang-jiao-lian-biao-by-leetcode-solutio-a8jn/
     */
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode pA = headA, pB = headB;
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }


}
