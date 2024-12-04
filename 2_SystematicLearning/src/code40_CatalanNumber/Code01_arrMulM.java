package code40_CatalanNumber;

import java.util.TreeSet;

/// 给定一个非负数组arr，和一个正数m。
/// 返回arr的所有子序列中累加和%m之后的最大值。
public class Code01_arrMulM {

    /// 1.第一种dp
    /// 注意：数组长度会很长
    /// Code01_1 如果sum长度很大 就会影响运算
    public static int max1(int[] arr, int m) {
        int sum = 0;
        int N = arr.length;
        for (int i = 0; i < N; i++) {
            sum += arr[i];
        }
        boolean[][] dp = new boolean[N][sum + 1];
        for (int i = 0; i < N; i++) {
            dp[i][0] = true;
        }
        dp[0][arr[0]] = true;
        for (int index = 1; index < N; index++) {
            for (int rest = 1; rest <= sum; rest++) {
                dp[index][rest] = dp[index - 1][rest];
                if (rest >= arr[index]) {
                    dp[index][rest] |= dp[index - 1][rest - arr[index]];
                }
            }
        }
        int ans = 0;
        for (int rest = 0; rest <= sum; rest++) {
            if (dp[N - 1][rest]) {
                ans = Math.max(ans, rest % m);
            }
        }
        return ans;
    }


    /// 第二种dp方式
    public static int max2(int[] arr, int m) {
        int N = arr.length;
        boolean[][] dp = new boolean[N][m + 1];
        for (int i = 0; i < N; i++) {
            dp[i][0] = true;
        }
        dp[0][arr[0] % m] = true;
        for (int index = 1; index < N; index++) {
            for (int rest = 1; rest <= m; rest++) {
                dp[index][rest] = dp[index - 1][rest];
                int cur = arr[index] % m;
                if (rest - cur >= 0) {
                    dp[index][rest] |= dp[index - 1][rest - cur];
                } else {
                    dp[index][rest] |= dp[index - 1][rest + m - cur];
                }
            }
        }
        int ans = 0;
        for (int rest = 0; rest <= m; rest++) {
            if (dp[N - 1][rest]) {
                ans = rest;
            }
        }
        return ans;
    }


    /// 第三种dp方式
    //如果arr的累加和很大，m也很大
    //但是arr的长度相对不大
    public static int max3(int[] arr, int m) {
        if (arr.length == 1) {
            return arr[0] % m;
        }
        int mid = (arr.length - 1) / 2;
        TreeSet<Integer> sortSet1 = new TreeSet<>();
        process(arr, 0, 0, mid, m, sortSet1);
        TreeSet<Integer> sortSet2 = new TreeSet<>();
        process(arr, mid + 1, 0, arr.length - 1, m, sortSet2);
        int ans = 0;
        for (Integer cur : sortSet1) {
            ans = Math.max(ans, cur + sortSet2.floor(m - 1 - cur));
        }
        return ans;

    }


    // 从index 出发，最后有边界是end +1 ，arr[index ..... end]
    public static void process(int[] arr, int index, int sum, int end, int m, TreeSet<Integer> sortSet) {
        if (index == end + 1) {
            sortSet.add(sum % m);
        } else {
            process(arr, index + 1, sum, end, m, sortSet);
            process(arr, index + 1, sum + arr[index], end, m, sortSet);
        }
    }




}
