package code18_bitmap2_ForceRecursion1;

import java.util.Stack;

/// 请逆序一个栈：
/// 要求：
/// 1.不能申请额外的数据结构
/// 2.只能使用递归函数
public class Code08_ReverseStack {



    public static void reverse(Stack<Integer> stack){
        if(stack.isEmpty()){
            return;
        }
        int last = func(stack);
        reverse(stack);
        stack.push(last);
    }


    //获取栈底元素，并移除栈底元素，其他元素不变
    public static int func(Stack<Integer> stack){
        int result = stack.pop();
        if(stack.isEmpty()){
            return result;
        }else{
            int last = func(stack);
            stack.push(result);
            return last;
        }
    }

}
