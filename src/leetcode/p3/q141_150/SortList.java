package leetcode.p3.q141_150;

import leetcode.common.ListNode;

/**
 * 148.排序链表
 * 在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
 * 
 *	示例 1:
 *	输入: 4->2->1->3
 *	输出: 1->2->3->4
 *
 *	示例 2:
 *	输入: -1->5->3->4->0
 *	输出: -1->0->3->4->5
 * 
 * @author ilongli
 * @email 351365415@qq.com
 */
public class SortList {


	public static void main(String[] args) {
		ListNode head = new ListNode(-1);
		ListNode h2 = new ListNode(5);
		ListNode h3 = new ListNode(3);
		ListNode h4 = new ListNode(4);
		ListNode h5 = new ListNode(0);
		head.next = h2;
		h2.next = h3;
		h3.next = h4;
		h4.next = h5;



		ListNode _head = new ListNode(4);
		ListNode _h2 = new ListNode(9);
		ListNode _h3 = new ListNode(11);
		ListNode _h4 = new ListNode(16);
		_head.next = _h2;
		_h2.next = _h3;
		_h3.next = _h4;


//		h4.next = _head;


		ListNode mergeHead = new SortList().sortList(head);


		while (mergeHead != null) {
			System.out.print(mergeHead.val + "->");
			mergeHead = mergeHead.next;
		}

	}

	// 所用到的变量
	// 归并后的head
	public ListNode mergeHead;
	// 递归过程的下一个节点(临时节点)
	public ListNode nextRight;
	// 临时节点
	public ListNode tempNode, left, right, curNode, pLeft, pRight;


	// 4->2->1->3
    public ListNode sortList(ListNode head) {

		if (head == null || head.next == null) return head;

		int curLength = 2;

		pLeft = divide(head ,curLength);

		while (curNode != null) {

			pRight = divide(curNode, curLength);

			merge(pLeft, pRight);

			pLeft = mergeHead;

			curLength <<= 1;
		}
        
    	return mergeHead;
    }

	/**
	 * 分治
	 * @param head		开始节点
	 * @param length	长度
	 * @return			结果
	 */
    public ListNode divide(ListNode head, int length) {

    	if (head == null || head.next == null) {
    		curNode = null;
    		return head;
		}

		if (length == 2) {
			left = head;
			right = head.next;
			head.next = null;
			curNode = right.next;
			right.next = null;
			merge(left, right);
		} else {
			merge(divide(head, length >> 1), divide(curNode, length >> 1));
		}
		return mergeHead;
	}


	/**
	 * 归并
	 * 时间复杂度 < O(n)
	 * @param left		左链表开始节点
	 * @param right		右链表开始节点
	 */
	public void merge(ListNode left, ListNode right) {

		// check null
		if (right == null) {
			mergeHead = left;
			return;
		};

		// 总是将开始节点小的放left一边
		if (left.val > right.val) {
			tempNode = left;
			left = right;
			right = tempNode;
		}

		// 归并后的head总是当前的left
		mergeHead = left;


		// 两边遍历比较并swap
		while (right != null) {
			/**
			 * 思路：
			 * 1.一开始，left.val > right.val。将左序列遍历到left.next.val > right.val为止
			 * 2.再将右序列遍历到right.next.val > left.next.val为止
			 * 3.将此时的右序列的[0, right]放入left后面。
			 */
			// 1.
			while (left.next != null && left.next.val < right.val) left = left.next;

			// 1.x 边界条件判断，如果此时的left.next = null，说明左序列已经到尾部，直接将尾部拼接到右序列的头部
			if (left.next == null) {
				left.next = right;
				break;
			}

			// 2.
			// 此时的tempNode记录右序列的开始节点
			tempNode = right;
			while (right.next != null && right.next.val < left.next.val) right = right.next;

			// 3.
			// 下一个right
			nextRight = right.next;
			// swap
			right.next = left.next;
			left.next = tempNode;
			// 初始化下一个遍历的数据
			left = right;
			right = nextRight;
		}
   }
	
}
