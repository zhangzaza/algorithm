package code22_BFRAndDP_04;

/// arr是货币数组，其中的值都是正数。在给定一个正数aim
/// 每个值都认为一张货币，每张货币可以用的次数是无穷张
/// 返回组成aim的方法数
/// 例如：arr={1,1,1} , aim=2
/// 第0个和第1个能组成2，第1个和第2个能组成2，第0个和第2个能组成2一共就3种方法，所以返回3
public class Code03_CoinsWaysNoLimit {

    /// 第一种：暴力递归
    public static int coinWays1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        return process(arr, 0, aim);
    }

    public static int process(int[] arr, int index, int rest) {
        // 因为下面有判断 zhang*arr[index]<=rest 所以这里不用再进行判断
        //if (rest < 0){
        //    return 0;
        //}
        if (index == arr.length) {
            return rest == 0 ? 1 : 0;
        }

        int ways = 0;
        for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
            ways += process(arr, index + 1, rest - zhang * arr[index]);
        }
        return ways;
    }


    /// 第二种：动态规划
    public static int coinWays2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1; // if (index == arr.length){return rest == 0 ? 1 : 0; 根据base case得出

        for (int index = N - 1; index >= 0; index--) { // 从最下面一行开始填写
            for (int rest = 0; rest <= aim; rest++) { // 从最左边一列开始填写

                // 逻辑照搬
                int ways = 0;
                for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
                    ways += dp[index + 1][rest - zhang * arr[index]];
                }
                dp[index][rest] = ways;
            }
        }

        return dp[0][aim];
    }


    /// 记忆化搜索：就是递归函数中已经算过的值进行缓存
    /// 严格表结构「动态规划」：通过表的关系，进行了状态转移，从简单算到复杂，拿到我们想要的值

    /// 重要点：
    /// 1.如果没有枚举，就是逻辑中没有for循环，记忆化搜索和严格表结构是没有区别的
    /// 2.如果有枚举，严格表结构还可以进行优化


    /// 第三种：对第二种的优化 「看图片」
    public static int coinWays3(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1; // if (index == arr.length){return rest == 0 ? 1 : 0; 根据base case得出

        for (int index = N - 1; index >= 0; index--) { // 从最下面一行开始填写
            for (int rest = 0; rest <= aim; rest++) { // 从最左边一列开始填写

                dp[index][rest] = dp[index + 1][rest];
                if (rest - arr[index] >= 0){
                    dp[index][rest] += dp[index][rest - arr[index]];
                }

            }
        }

        return dp[0][aim];
    }



}
