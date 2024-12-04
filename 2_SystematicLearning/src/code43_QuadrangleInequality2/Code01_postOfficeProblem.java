package code43_QuadrangleInequality2;


/// 邮局问题（Post Office Problem）是一类经典的动态规划问题，
/// 通常描述为在一个直线上有若干个村庄，目标是设置一定数量的邮局，使得所有村庄到最近邮局的距离总和最小。
/// 问题的主要挑战是确定邮局的最佳位置以及如何分配村庄给不同的邮局。
public class Code01_postOfficeProblem {

    /// 思路：
    /// 1.数组为奇数时，建立在中点即可，数组为偶数时，建立在偶数的中位数，两个一样
    /// 2.推出指针中间指针不回退

    public static int postOffice(int[] arr,int num) {
        if(arr == null || arr.length < num || num < 1){
            return 0;
        }
        int N = arr.length;
        int[][] w = new int[N+1][N+1];
        for (int L = 0; L < N; L++) {
            //L == R
            for (int R = L+1; R < N; R++) {
                // 每个邮局最好的位置
                w[L][R] = w[L][R-1] + arr[R] - arr[(L+R)>>1];
            }
        }
        int[][] dp = new int[N][num+1];

        // 0.....i 建立一个邮局，怎么最优
        for (int i = 0; i < N; i++) {
            dp[i][1] =w[0][i];
        }

        for (int i = 1; i < N; i++) {
            // 邮局数量比点多的时候，距离为0
            // j邮局从2 开始尝试，不要超过给定的总数量num，也不要超过给定的位置数
            for (int j = 2; j <= Math.min(i,num); j++) {
                dp[i][j] = Integer.MAX_VALUE;
                for(int k=0;k <= i ;k++){
                    dp[i][j] = Math.min(dp[i][j],dp[k][j-1]+w[k+1][i]);
                }
            }
        }
        return dp[N-1][num];
    }

}
