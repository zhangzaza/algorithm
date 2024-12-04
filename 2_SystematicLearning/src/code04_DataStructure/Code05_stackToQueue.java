package code04_DataStructure;

import java.util.Stack;

//如何用栈结构实现队列结构
//思路：
//1.使用两个栈实现，一个是push栈，一个是pop栈
//2.push栈用来压入数据，pop栈用来弹出数据
//3.数据在来回切换的时候，数据不能切换一半
public class Code05_stackToQueue {

    public static class TwoStackQueue {
        private Stack<Integer> pushStack;
        private Stack<Integer> popStack;

        public TwoStackQueue(){
            pushStack = new Stack<>();
            popStack = new Stack<>();
        }

        private void pushToPop(){
            if(popStack.isEmpty()){
                while (!pushStack.isEmpty()){
                    popStack.push(pushStack.pop());
                }
            }
        }


        public void push(int value){
            pushStack.push(value);
            pushToPop();
        }


        public int pop(){
            pushToPop();
            return popStack.pop();
        }


        public int peek(){
            pushToPop();
            return popStack.peek();
        }



    }


}
