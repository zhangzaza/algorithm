package code25_windowsMaxMin;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/// arr是货币数组，其中的值都是正数，在给定一个正数aim
/// 每个值都认为是一张货币，返回组成aim的最少货币数
/// 注意：因为要求最少货币数，所以每一张货币认为是相同或者不同就不重要了
public class Code04_AimMinPagesCost {


    /// 第一种：暴力递归
    public static int minCoins(int[] arr, int aim) {
        return process(arr, 0, aim);
    }

    public static int process(int[] arr, int index, int rest) {
        if (rest < 0) {
            return Integer.MAX_VALUE;
        }
        if (index == arr.length) {
            return rest == 0 ? 0 : Integer.MAX_VALUE;
        } else {
            int p1 = process(arr, index + 1, rest);
            int p2 = process(arr, index + 1, rest - arr[index]);
            if (p2 != Integer.MAX_VALUE) {
                p2++;
            }
            return Math.min(p1, p2);
        }
    }


    /// 第二种：动态规划「时间复杂度0， arr长度*aim」{包含了枚举行为}
    public static int dp1(int[] arr, int aim) {
        if (aim == 0) {
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 0;
        for (int j = 1; j <= aim; j++) {
            dp[N][j] = Integer.MAX_VALUE;
        }

        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int p1 = dp[index + 1][rest];
                int p2 = rest - arr[index] >= 0 ? dp[index + 1][rest - arr[index]] : Integer.MAX_VALUE;
                if (p2 != Integer.MAX_VALUE) {
                    p2++;
                }
                dp[index][rest] = Math.min(p1, p2);
            }
        }
        return dp[0][aim];
    }

    /// 第三种：动态规划「不包含枚举行为」
    public static class Info {
        public int[] coins;
        public int[] zhangs;

        public Info(int[] c, int[] z) {
            coins = c;
            zhangs = z;
        }
    }

    public static Info getInfo(int[] arr) {
        HashMap<Integer, Integer> counts = new HashMap<>();
        for (int value : arr) {
            if (!counts.containsKey(value)) {
                counts.put(value, 1);
            } else {
                counts.put(value, counts.get(value) + 1);
            }
        }

        int N = counts.size();
        int[] coins = new int[N];
        int[] zhangs = new int[N];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            coins[index] = entry.getKey();
            zhangs[index++] = entry.getValue();
        }
        return new Info(zhangs, coins);
    }


    /// dp2时间复杂度：0(arr长度) + 0(货币种树*aim*每种货币的平均张数)
    public static int dp2(int[] arr, int aim) {
        if (aim == 0) {
            return 0;
        }

        // 得到info时间复杂度0(arr长度)
        Info info = getInfo(arr);
        int[] coins = info.coins;
        int[] zhangs = info.zhangs;
        int N = coins.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 0;
        for (int j = 1; j <= aim; j++) {
            dp[N][j] = Integer.MAX_VALUE;
        }

        //虽然嵌套了很多循环，但是时间复杂度为0(货币种树*aim)
        //因为用了窗口内最小值的更新结构
        for (int index = N - 1; index >= 0; index--) {
            for (int mod = 0; mod < Math.min(aim + 1, coins[index]); mod++) {
                //当前 面试 X
                // mod，mod+X，mod+2*X，mod+3*X...
                LinkedList<Integer> minWindow = new LinkedList<>();
                minWindow.add(mod);
                dp[index][mod] = dp[index + 1][mod];
                for (int r = mod + coins[index]; r <= aim; r += coins[index]) {
                    while (!minWindow.isEmpty() && dp[index + 1][minWindow.peekLast()] == Integer.MAX_VALUE
                            ||
                            (dp[index + 1][minWindow.peekLast()] // 当前尾巴上的张数
                                    +
                                    compensate(minWindow.peekLast(), r, coins[index])//补偿的张数：1.最下值的钱，2.r为现在来到的钱，3.面值
                                    >= dp[index + 1][r])) {
                        minWindow.pollLast();
                    }
                    minWindow.addLast(r);
                    int overdue = r - coins[index] * (zhangs[index] + 1);
                    if (minWindow.peekFirst() == overdue) {
                        minWindow.pollFirst();
                    }
                    dp[index][r] = dp[index + 1][minWindow.peekFirst()] + compensate(minWindow.peekFirst(), r, coins[index]);
                }

            }
        }

        return dp[0][aim];
    }

    public static int compensate(int pre, int cur, int coin) {
        return (cur - pre) / coin;
    }


}
