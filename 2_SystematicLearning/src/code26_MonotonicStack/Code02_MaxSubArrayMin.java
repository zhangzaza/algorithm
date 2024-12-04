package code26_MonotonicStack;

import java.util.Stack;

/// 给定一个只包含正数的数组arr，arr中任何一个子数组sub，一定都可以算出(sub累加和)*(sub中的最小值)是什么？
/// 「子数组一定是连续的」
/// 那么所有子数组中，这个值最大是什么？
public class Code02_MaxSubArrayMin {


    /// 思路：index从0开始，必须要找到index下标的数字为最小值的 sum最大的子数组
    /// 第一种想法：
    ///     第一步：遍历数组，完善 Stack<List<Integer>> 单调栈 「但是这里的时间复杂度还是O(N^2),得对步骤进行优化，不能全部完善到栈中再计算」
    ///     第二步：计算前缀和数组，求出
    ///     第三步：遍历前缀和数组，计算每个位置的sum*min
    /// 第二种实现：
    ///     第一步：前缀和
    ///     第二步：单调栈，并且只使用 Stack<Integer>
    ///     第三步：计算每个位置的sum*min


    /// 第二种实现
    public static int maxSubArrayMin2(int[] arr) {
        int size = arr.length;
        //1.生成前缀和数组
        int[] preSum = new int[size];
        preSum[0] = arr[0];
        for (int i = 1; i < size; i++) {
            preSum[i] = preSum[i - 1] + arr[i];
        }
        //2.单调栈
        int max = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < size; i++) {
            // 「重要点：这里主要就是想到的是重复值，就算有重复值也没事，第三步会进行处理，这里最重要」
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                int popIndex = stack.pop();
                int popIndexMax=(preSum[i - 1] - preSum[stack.peek()]) * arr[popIndex];
                max = Math.max(max, (stack.isEmpty() ? preSum[i - 1] : popIndexMax));
            }
            stack.push(i);
        }
        //3.如果已经遍历结束了，处理单调栈
        while (!stack.isEmpty()) {
            int popIndex = stack.pop();
            int popIndexMax=(preSum[size - 1] - preSum[stack.peek()]) * arr[popIndex];
            max = Math.max(max, (stack.isEmpty() ? preSum[size - 1] : popIndexMax));
        }
        return max;
    }


    /// 暴力方法 O(N^3)
    public static int maxSubArrayMin1(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int minNum = Integer.MAX_VALUE;
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    minNum = Math.min(minNum, arr[k]);
                    sum += arr[k];
                }
                max = Math.max(max, sum * minNum);
            }
        }
        return max;
    }

}
