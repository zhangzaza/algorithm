package code04_DataStructure;


//实现一个特殊的栈，在基本功能的基础上，在实现返回栈中最小的功能
//1.pop，push，getMin操作的时间复杂度都是O(1)，「getMin只是拿到最小值，并没有弹出的逻辑」
//2.设计的栈类型可以使用现成的栈结构

//dataStack:  [3, 5, 2 ,3 , 1]
//minStack:   [3, 3, 2 ,2 , 1]
import java.util.Stack;

//实现的方式：使用两个栈
public class Code04_getMinStack {

    public static class MinStack {

        // 数据栈
        private Stack<Integer> dataStack;
        // 最小栈
        private Stack<Integer> minStack;

        // 构造函数
        public MinStack() {
            dataStack = new Stack<>();
            minStack = new Stack<>();
        }

        // 入栈操作
        public void push(int x) {
            dataStack.push(x);
                minStack.push(x<minStack.peek()?x:minStack.peek());
        }

        // 出栈操作
        public void pop() {
            if (!minStack.isEmpty()) {
                minStack.pop();
            }
            // 数据栈进行出栈操作
            if (!dataStack.isEmpty()) {
                dataStack.pop();
            }
        }

        // 获取栈顶元素
        public int top() {
            if (!dataStack.isEmpty()) {
                return dataStack.peek();
            }
            throw new RuntimeException("Stack is empty.");
        }

        // 获取最小值
        public int getMin() {
            if (!minStack.isEmpty()) {
                return minStack.peek();
            }
            throw new RuntimeException("Stack is empty.");
        }
    }


}
