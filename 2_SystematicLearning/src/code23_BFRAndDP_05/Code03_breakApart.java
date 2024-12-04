package code23_BFRAndDP_05;

/// 给定一个数，这个数可以裂开，并且后面裂开的数不比前面裂开的数小
/// 比如 5
/// 1.1+1+1+1+1
/// 2.1+1+1+2
/// 3.1+1+3
/// 4.1+2+2
/// 5.1+4
/// 6.2+3
/// 7.5
public class Code03_breakApart {


    /// 第一种：暴力递归
    public static int ways(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return process(1, n);
    }

    // 上一个拆出来的数是pre
    // 还剩rest需要去拆
    // 返回拆解的方法数
    public static int process(int pre, int rest) {
        if (pre > rest) {
            return 0;
        }
        if (pre == rest) {
            return 1;
        }
        // pre<rest
        int ways = 0;
//        for (int first = pre; first <rest ; first++) {
//            ways+=process(first,rest-first);
//        }
//        ways++;// 这里表示只是拆出来单独的这个数字 如果这里不想要加的话，就在上面循环中加上 first<=rest，因为在base case中已经判断如果pre==rest 返回1 看下面
        for (int first = pre; first <= rest; first++) {
            ways += process(first, rest - first);
        }
        return ways;
    }


    /// 第二种：改成动态规划「包含了枚举」
    public static int dp1(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int[][] dp = new int[n + 1][n + 1];
        for (int pre = 0; pre <= n; pre++) {
            dp[pre][0] = 1;
            dp[pre][pre] = 1;
        }

        // 从右下角开始遍历
        for (int pre = n - 1; pre >= 1; pre--) {
            for (int rest = pre + 1; rest <= n; rest++) {

                // 逻辑照搬
                int ways = 0;
                for (int first = pre; first <= rest; first++) {
                    ways += dp[first][rest - first];
                }
                dp[pre][rest] = ways;

            }
        }

        return dp[1][n];
    }

    /// 第三种：动态规划「不包含枚举」
    public static int dp2(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int[][] dp = new int[n + 1][n + 1];
        for (int pre = 0; pre <= n; pre++) {
            dp[pre][0] = 1;
            dp[pre][pre] = 1;
        }

        // 从右下角开始遍历
        for (int pre = n - 1; pre >= 1; pre--) {
            for (int rest = pre + 1; rest <= n; rest++) {

                // 严格表结构
                dp[pre][rest] = dp[pre + 1][rest];
                dp[pre][rest] += dp[pre][rest - pre];

            }
        }

        return dp[1][n];
    }


}
