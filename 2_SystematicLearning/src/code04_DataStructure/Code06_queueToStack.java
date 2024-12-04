package code04_DataStructure;

import java.util.LinkedList;
import java.util.Queue;

//如何用队列结构实现栈结构
//思路：
//1.使用两个队列实现
//2.一个队列先放数据，突然要最后一个数的时候，就把除最后一个数外的数都放到另一个队列中，返回最后一个数
//3.来回换，就实现了栈的功能
public class Code06_queueToStack {

    public class StackUsingQueues {
        private Queue<Integer> queue1;
        private Queue<Integer> queue2;

        // 构造函数，初始化队列
        public StackUsingQueues() {
            queue1 = new LinkedList<>();
            queue2 = new LinkedList<>();
        }

        // 入栈操作
        public void push(int x) {
            if (!queue1.isEmpty()) {
                queue1.offer(x);
            } else {
                queue2.offer(x);
            }
        }

        // 出栈操作
        public int pop() {
            if (isEmpty()) {
                throw new RuntimeException("Stack is empty");
            }
            return moveAndPop();
        }

        // 获取栈顶元素
        public int top() {
            if (isEmpty()) {
                throw new RuntimeException("Stack is empty");
            }
            int topElement = moveAndPop();
            push(topElement);  // 由于只是获取栈顶元素，不是真正的弹出，所以需要再把元素放回去
            return topElement;
        }

        // 判空操作
        public boolean isEmpty() {
            return queue1.isEmpty() && queue2.isEmpty();
        }

        // 辅助函数：将非空队列中的元素移动到另一个队列中，返回最后一个元素
        private int moveAndPop() {
            Queue<Integer> nonEmptyQueue = queue1.isEmpty() ? queue2 : queue1;
            Queue<Integer> emptyQueue = queue1.isEmpty() ? queue1 : queue2;
            while (nonEmptyQueue.size() > 1) {
                emptyQueue.offer(nonEmptyQueue.poll());
            }
            return nonEmptyQueue.poll();  // 返回最后一个元素
        }
    }

}
