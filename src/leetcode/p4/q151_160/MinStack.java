package leetcode.p4.q151_160;

import java.util.LinkedList;

/**
 * 155. 最小栈
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 *
 * push(x) —— 将元素 x 推入栈中。
 * pop() —— 删除栈顶的元素。
 * top() —— 获取栈顶元素。
 * getMin() —— 检索栈中的最小元素。
 *
 *
 * 示例:
 *
 * 输入：
 * ["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 *
 * 输出：
 * [null,null,null,null,-3,null,0,-2]
 *
 * 解释：
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.getMin();   --> 返回 -2.
 *
 * @author ilongli
 * @date 2021/8/24 9:23
 */
public class MinStack {

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
    }

    LinkedList<Integer> stack = null;
    int min;

    /** initialize your data structure here. */
    public MinStack() {
        stack = new LinkedList<>();
        min = 0;
    }

    public void push(int val) {
        if (stack.size() == 0) {
            min = val;
        } else {
            if (val < min) min = val;
        }
        stack.push(val);
    }

    public void pop() {
        Integer pop = stack.pop();
        if (pop == min && stack.size() > 0) {
            // 更新最小
            min = stack.get(0);
            for (int i = 1; i < stack.size(); i++) {
                int cur = stack.get(i);
                if (cur < min) min = cur;
            }
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min;
    }

}
