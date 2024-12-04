package code42_QuadrangleInequality;


/// 题目三：
/// 摆放着n堆石子。现要将石子有次序的合并成一堆
/// 规定每次只能选相邻的2堆石子合并成新的一堆。
/// 并将新的一堆石子数记为该次合并的得分
/// 求出将n堆石子合并成一堆的最小得分(或最大得分)合并方案
public class Code03_CombineStones {

    public static int[] sum(int[] arr){
        int N = arr.length;
        int[] sum = new int[N+1];
        sum[0] = 0;
        for (int i = 1; i <= N; i++) {
            sum[i] = sum[i-1] + arr[i-1];
        }
        return sum;
    }

    public static int w(int[] sum, int L, int R){
        return sum[R+1] - sum[L];
    }


    /// 时间复杂度：O(N^3)
    public static int minSumStones(int[] arr){
        if (arr == null || arr.length <2){
            return 0;
        }
        int N = arr.length;
        int[] sum = sum(arr);
        int[][] dp = new int[N][N];
        for(int L =N-2; L>=0; L--){
            for (int R = L+1; R < N; R++) {
                int ans = Integer.MAX_VALUE;
                for (int K = L; K < R; K++) {
                    ans = Math.min(ans, dp[L][K] + dp[K+1][R]);
                }
                dp[L][R] = ans + w(sum,L,R);
            }
        }
        return dp[0][N-1];
    }



    public static int minSumStones2(int[] arr){
        if (arr == null || arr.length <2){
            return 0;
        }
        int N = arr.length;
        int[] sum = sum(arr);
        int[][] dp = new int[N][N];
        int[][] best = new int[N][N];
        //填写第一条和第二条对角线
        for (int i = 0; i < N-1; i++) {
            best[i][i+1] = i;
            dp[i][i+1] = w(sum,i,i+1);
        }
        //填写剩余的对角线
        for (int L = N-3; L >=0; L--) {
            for (int R = L+2; R < N; R++) {
                int next = Integer.MAX_VALUE;
                int choose =-1;
                for (int K = best[L][R-1]; K <= best[L+1][R]; K++) {//只在两者之间寻找
                    int cur = dp[L][K] + dp[K+1][R];
                    if (cur <next){
                        next = cur;
                        choose = K;
                    }
                }
                best[L][R] = choose;
                dp[L][R] = next + w(sum,L,R);
            }
        }
        return dp[0][N-1];
    }


}
