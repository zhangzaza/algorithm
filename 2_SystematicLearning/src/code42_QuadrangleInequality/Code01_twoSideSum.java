package code42_QuadrangleInequality;

/// 题目一：
/// 给定一个非负数组arr，长度为N
/// 那么有N-1种方案可以把arr切成左右两部分
/// 每一种方案都有，min{左部分累加和，右部分累加和}
/// 求那么多方案中，min{左部分累加和，右部分累加和}的最大值是多少？
/// 整个过程要求时间复杂度O(N)
public class Code01_twoSideSum {

    public static int bestSplit(int[] arr){
        if (arr == null || arr.length == 0){
            return 0;
        }
        int N = arr.length;
        int sumAll=0;
        for (int num : arr) {
            sumAll += num;
        }
        int ans =0;
        int sumL = 0;
        for (int i = 0; i < N-1; i++) {
            sumL += arr[i];
            int sumR = sumAll - sumL;
            ans = Math.max(ans, Math.min(sumL, sumR));
        }
        return ans;
    }

}
