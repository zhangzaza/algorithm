package code27_Fibonacci;

import java.util.Stack;

/// 给定一个数组arr，返回所有子数组最小值的累加和
/// 题意：
/// 1.对于每一个子数组，找到该子数组的最小值。
/// 2.累积所有子数组的最小值，以得到一个总和。
public class Code01_SubMinSum {

    /// 第一种：不用单调栈
    public static int subArrayMinSum(int[] arr) {
        // left[i] = x 指的是 arr[i]左边，离arr[i]最近， <= arr[i],位置在x
        int[] left = leftNearLessEqual(arr);
        // right[i] = y 指的是 arr[i]右边，离arr[i]最近， < arr[i],位置在y
        int[] right = rightNearLess(arr);

        int ans = 0;
        //求的是arr[i] 的贡献是 start * end * arr[i]。
        for (int i = 0; i < arr.length; i++) {
            int start = i - left[i];//其中 start = i - left[i] 表示 arr[i] 可以作为最小值的子数组的开始位置个数。
            int end = right[i] - i;//end = right[i] - i 表示 arr[i] 可以作为最小值的子数组的结束位置个数。
            ans += start * end * arr[i];//对于每个元素 arr[i]，它作为子数组的最小值的次数可以用 start * end 表示。
        }
        return ans;
    }

    public static int[] leftNearLessEqual(int[] arr) {
        int N= arr.length;
        int [] left = new int[N];
        for (int i = 0; i < N; i++) {
            int ans = -1;
            for(int j=i-1;j>=0;j--){
                if(arr[j]<=arr[i]){
                    ans = j;
                    break;
                }
            }
            left[i] = ans;
        }
        return left;
    }

    public static int[] rightNearLess(int[] arr) {
        int N= arr.length;
        int [] right = new int[N];
        for (int i =0;i<N;i++){
            int ans = N;
            for(int j=i+1;j<N;j++){
                if(arr[j]<arr[i]){
                    ans = j;
                    break;
                }
            }
            right[i] = ans;
        }
        return right;
    }


    /// 使用单调栈实现
    public static int sumSubArrMinSum2(int[] arr) {
        int[] left = leftNearLessEqual2(arr);
        int[] right = rightNearLess2(arr);
        long ans = 0;
        for (int i = 0; i < arr.length; i++){
            long start = i - left[i];
            long end = right[i] - i;
            ans += start * end * (long)arr[i];
        }
        return (int)ans;
    }

    public static int[] leftNearLessEqual2(int[] arr) {
        int N= arr.length;
        int [] left = new int[N];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                left[stack.pop()] = i;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()){
            left[stack.pop()] = -1;
        }
        return left;
    }

    public static int[] rightNearLess2(int[] arr) {
        int N= arr.length;
        int [] right = new int[N];
        Stack<Integer> stack = new Stack<>();
        for (int i = N-1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                right[stack.pop()] = i;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()){
            right[stack.pop()] = N;
        }
        return right;
    }


}
