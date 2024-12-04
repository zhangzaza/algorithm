package code26_MonotonicStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/// 找出一个数组中 左边最近比他小的数，右边最近比他小的数，求出所有位置
public class Code01_findLeftSmallerAndRightSmaller {

    // arr = [3,1,2,3]
    //        0,1,2,3
    // [
    //   0 : [-1, 1]
    //   1 : [-1,-1]
    //   2 : [ 1,-1]
    //   3 : [ 2,-1]
    // ]

    /// 针对 没有重复值的数组
    public static int[][] getNearLessNoRepeat(int[] arr) {
        int[][] res = new int[arr.length][2];
        Stack<Integer> stacks = new Stack<>();//「下小，上大」
        // 1. 一次从index为0 开始 往栈中放入数据，弹出所有比arr[i]小的,并且更新结果
        for (int i = 0; i < arr.length; i++) {
            while (!stacks.isEmpty() && arr[i] < arr[stacks.peek()]){
                int popIndex = stacks.pop();
                int leftLessIndex = stacks.isEmpty() ? -1 : stacks.peek();
                res[popIndex][0] = leftLessIndex;
                res[popIndex][1] = i;
            }
            stacks.push(i);
        }
        //2.剩下的数据，没有被弹出，说明右边没有比他小的
        while (!stacks.isEmpty()) {
            int popIndex = stacks.pop();
            int leftLessIndex = stacks.isEmpty() ? -1 : stacks.peek();
            res[popIndex][0] =leftLessIndex;
            res[popIndex][1] = -1;
        }
        return res;
    }

    /// 针对 有重复值的数组
    public static int[][] getNearLess(int[] arr) {
        int[][] res = new int[arr.length][2];
        Stack<List<Integer>> stack = new Stack<>();//每个链表中的值对应的都是下标
        // 1. 一次从index为0 开始 往栈中放入数据，弹出所有比arr[i]小的,并且更新结果
        for (int i = 0; i < arr.length; i++) {
            //1.1.弹出所有比arr[i]小的
            while (!stack.isEmpty() && arr[stack.peek().get(0)]>arr[i]){//stack.peek().get(0) 是一个链表，链表中的元素都是下标，这个链表对应的值一样，但是下标不一样
                List<Integer> popList = stack.pop();
                int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size()-1);//stack.peek().size()-1 表示左边最近记录的下标「stack.peek()拿出的链表中对应的值都一样」
                for (Integer index : popList) {
                    res[index][0] = leftLessIndex;
                    res[index][1] = i;
                }
            }
            //1.2.往栈中放入数据
            if(!stack.isEmpty() && arr[stack.peek().get(0)]==arr[i]){
                stack.peek().add(i);
            }else{
                List<Integer> list = new ArrayList<>();
                list.add(i);
                stack.push(list);
            }
        }

        //2.剩下的数据，没有被弹出，说明右边没有比他小的
        while (!stack.isEmpty()) {
            List<Integer> popList = stack.pop();
            int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size()-1);
            for (Integer index : popList) {
                res[index][0] = leftLessIndex;
                res[index][1] = -1;
            }
        }
        return res;
    }


}
