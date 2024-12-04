package code22_BFRAndDP_04;


import java.util.HashMap;
import java.util.Map;

/// arr是货币数组，其中的值都是正数。在给定一个正数aim
/// 每个值都认为一张货币，认为值相同的货币没有任何不同
/// 返回组成aim的方法数
/// 例如：arr={1,2,1,1,2,1,2} ,aim =4
/// 方法：1+1+1+1=4  2+2=4  1+1+2=4
/// 一共三种方法，所以返回3
public class Code04_CoinsWaysSameLimit {

    public static class Info {
        public int[] ways;
        public int[] coins;

        public Info(int[] ways, int[] coins) {
            this.ways = ways;
            this.coins = coins;
        }
    }

    /// 对数组的所有面值和相对应的数量进行排序
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
        for (Map.Entry<Integer, Integer> integerIntegerEntry : counts.entrySet()) {
            coins[index] = integerIntegerEntry.getKey();
            zhangs[index++] = integerIntegerEntry.getValue();
        }
        return new Info(zhangs, coins);
    }

    /// 第一种：暴力递归
    public static int process(int[] coins, int[] zhangs, int index, int rest) {
        if (index == coins.length) {
            return rest == 0 ? 1 : 0;
        }

        int ways = 0;
        for (int zhang = 0;
             zhang * coins[index] <= rest && zhang <= zhangs[index];//zhang * coins[index] <= rest 说明还有剩余，且zhang张 <= zhangs[index]张
             zhang++) {
            ways += process(coins, zhangs, index + 1, rest - zhang * coins[index]);
        }
        return ways;
    }

    /// 第二种：动态规划「使用枚举」
    public static int dp1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        Info info = getInfo(arr);
        int N = info.coins.length;
        int[] coins = info.coins;
        int[] zhangs = info.ways;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int ways = 0;
                for (int zhang = 0; zhang * coins[index] <= rest && zhang <= zhangs[index]; zhang++) {
                    ways += dp[index + 1][rest - zhang * coins[index]];
                }
                dp[index][rest] = ways;
            }
        }
        return dp[0][aim];
    }


    /// 第二种：动态规划「不使用枚举」「看图片」
    public static int dp2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        Info info = getInfo(arr);
        int N = info.coins.length;
        int[] coins = info.coins;
        int[] zhangs = info.ways;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                // 1.先获得下面的位置
                dp[index][rest] = dp[index + 1][rest];
                // 2.再获得左边的位置
                if (rest - coins[index] >= 0) {
                    dp[index][rest] += dp[index][rest - coins[index]];
                }
                // 3.看看d位置有没有算冲突，剩余的钱数减掉(极限的张数+1) 还没有越界，说明 d 位置的方法数重复了，所以减掉 d 「看图片」
                if (rest - coins[index] * (zhangs[index] + 1) >= 0) {
                    dp[index][rest] -= dp[index + 1][rest - coins[index] * zhangs[index]];
                }
            }
        }
        return dp[0][aim];
    }

}
