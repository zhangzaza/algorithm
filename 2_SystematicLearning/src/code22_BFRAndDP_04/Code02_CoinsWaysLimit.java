package code22_BFRAndDP_04;

/// arr是货币数组，其中的值都是正数。在给定一个正数aim
/// 每个值都认为一张货币，
/// 即便是值相同的货币也认为每一张都是不同的，
/// 返回组成aim的方法数
/// 例如：arr={1,1,1} , aim=2
/// 第0个和第1个能组成2，第1个和第2个能组成2，第0个和第2个能组成2一共就3种方法，所以返回3
public class Code02_CoinsWaysLimit {



    /// 第一种：暴力递归
    public static int coinWays1(int[] arr, int aim) {
        return process(arr, 0, aim);
    }

    // arr[index....] 组成了正好rest这么多钱，有几种方法
    public static int process(int[] arr, int index, int rest) {
        if (rest < 0) {
            return 0;
        }
        if (index == arr.length) {
            return rest == 0 ? 1 : 0;
        } else {
            //返回，用了这张纸币的方法数量和不用这张纸币的方法数量
            return process(arr, index + 1, rest) + process(arr, index, rest - arr[index]);
        }
    }



    /// 第二种：动态规划
    public static int coinWays2(int[] arr, int aim) {
        if (aim == 0) {
            return 1;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;//if (index == arr.length)return rest==0?1:0;  根据这句话得出
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[index][rest] =
                        dp[index + 1][rest] // 不用
                                +
                                (rest - arr[index] >= 0 ? dp[index][rest - arr[index]] : 0); // 用「但是需要进行数组下标越界的判断」
            }
        }
        return dp[0][aim]; //return process(arr, 0, aim); 根据这句话得出
    }


}
