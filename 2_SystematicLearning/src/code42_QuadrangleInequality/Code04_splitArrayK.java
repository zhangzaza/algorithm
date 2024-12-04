package code42_QuadrangleInequality;
// 分割数组的最大值(画匠问题)
// 给定一个非负整数数组 nums 和一个整数 m
// 你需要将这个数组分成 m 个非空的连续子数组。
// 设计一个算法使得这 m 个子数组各自和的最大值最小。

/// 测试链接：https://leetcode.cn/problems/split-array-largest-sum/
public class Code04_splitArrayK {

    /// 1.暴力 的 动态规划「含枚举」
    public static int splitArray(int[] nums, int k) {
        int N = nums.length;
        int[] help = new int[N + 1];
        for (int i = 0; i < N; i++) {
            help[i + 1] = help[i] + nums[i];
        }
        int[][] dp = new int[N][k + 1];
        for (int i = 0; i < N; i++) {
            dp[i][1] = help[i + 1] - help[0];
        }

        for (int i = 1; i < Math.min(N, k); i++) {
            dp[i][i + 1] = Math.max(dp[i - 1][i], nums[i]);
        }

        for (int i = 2; i < N; i++) {
            for (int j = 2; j <= Math.min(i, k); j++) {
                dp[i][j] = Integer.MAX_VALUE;
                for (int m = i; m >= j - 1; m--) {
                    dp[i][j] = Math.min(dp[i][j], Math.max(dp[m - 1][j - 1], help[i + 1] - help[m]));
                }
            }
        }
        return dp[N - 1][k];
    }


    /// 2.优化后的动态规划「有错，但是是思路是对的」 「看code04_改」
    public static int splitArray2(int[] nums, int K) {
        int N = nums.length;
        int[] sum = new int[N + 1];
        for (int i = 0; i < N; i++) {
            sum[i + 1] = sum[i] + nums[i];
        }
        int[][] dp = new int[N][K + 1];
        int[][] best = new int[N][K + 1];
        for (int j = 1; j <= K; j++) {
            dp[0][j] = nums[0];
            best[0][j] = 0;
        }

        for (int i = 1; i < N; i++) {
            dp[i][1] = sum(sum, 0, i);
            best[i][1] = -1;
        }

        for (int j = 2; j <= K; j++) {
            //单独处理此列最下的格子
            //dp[N-1][j]
            int down = best[N - 1][j - 1];
            int ans = Integer.MAX_VALUE;
            int bestChoose = -1;
            for (int leftEnd = down; leftEnd < N; leftEnd++) {
                // 0....leftEnd j-1
                // leftEnd + 1...i   最后一个画家
                int leftCost = leftEnd == -1 ? 0 : dp[leftEnd][j - 1];
                int rightCost = leftEnd == N - 1 ? 0 : sum(sum, leftEnd + 1, N - 1);
                int cur = Math.max(leftCost, rightCost);
                if (cur <= ans) {
                    ans = cur;
                    bestChoose = leftEnd;
                }
            }
            dp[N - 1][j] = ans;
            best[N - 1][j] = bestChoose;
            int up = 0;

            for (int i = N - 2; i >= 0; i--) {
                down = best[N - 1][j - 1];
                up = best[i + 1][j];
                ans = Integer.MAX_VALUE;
                bestChoose = -1;
                for (int leftEnd = down; leftEnd < up; leftEnd++) {
                    // 0....leftEnd j-1
                    // leftEnd + 1...i   最后一个画家
                    int leftCost = leftEnd == -1 ? 0 : dp[leftEnd][j - 1];
                    int rightCost = leftEnd == i ? 0 : sum(sum, leftEnd + 1, i);
                    int cur = Math.max(leftCost, rightCost);
                    if (cur <= ans) {
                        ans = cur;
                        bestChoose = leftEnd;
                    }
                }
                dp[i][j] = ans;
                best[i][j] = bestChoose;
            }

        }

        return dp[N - 1][K];

    }

    public static int sum(int[] arr, int L, int R) {
        return arr[R + 1] - arr[L];
    }


    /// 使用二分法，最优解
    // 时间复杂度O(n * log(sum))，额外空间复杂度O(1)
    public static int splitArray3(int[] nums, int k) {
        long sum = 0;
        for (int num : nums) {
            sum += num;
        }
        long ans = 0;
        // [0,sum]二分
        for (long l = 0, r = sum, m, need; l <= r; ) {
            // 中点m
            m = l + ((r - l) >> 1);
            // 必须让数组每一部分的累加和 <= m，请问划分成几个部分才够!
            need = f(nums, m);
            if (need <= k) {
                // 达标
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return (int) ans;
    }

    // 必须让数组arr每一部分的累加和 <= limit，请问划分成几个部分才够!
    // 返回需要的部分数量
    public static int f(int[] arr, long limit) {
        int parts = 1;
        int sum = 0;
        for (int num : arr) {
            if (num > limit) {
                return Integer.MAX_VALUE;
            }
            if (sum + num > limit) {
                parts++;
                sum = num;
            } else {
                sum += num;
            }
        }
        return parts;
    }

}
