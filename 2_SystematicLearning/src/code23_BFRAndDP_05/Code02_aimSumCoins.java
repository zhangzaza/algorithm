package code23_BFRAndDP_05;


/// arr是面值数量的数组，比如{1,2,5,10,20,50,100}， aim是钱数，求组成aim的最少硬币数
/// 其中数组的值是正数且没有重复，每个值都认为是一种面值，且认为张数是无限支张
/// 返回组成aim的最少货币数量
public class Code02_aimSumCoins {

    /// 第一种：使用暴力递归
    public static int minCoins1(int[] arr, int aim){
        return process(arr,0,aim);
    }

    /// arr[index....] 面值，每种面值张数自由选择
    /// 搞出rest正好那么多钱，返回最小张数
    public static int process(int[] arr,int index,int rest){
        if (rest < 0){
            return Integer.MAX_VALUE;
        }

        if (index == arr.length){
            //你现在已经没钱了，但是你要搞定的钱是0元
            return rest == 0 ? 0 : Integer.MAX_VALUE;
        }else {
            int ans = Integer.MAX_VALUE;
            for (int zhang = 0; zhang * arr[index] <= rest; zhang++){
                int next= process(arr,index+1,rest - zhang * arr[index]);
                if (next != Integer.MAX_VALUE){
                    ans = Math.min(ans,next+zhang);
                }
            }
            return ans;
        }
    }


    /// 第二种：使用动态规划「包含了枚举」
    public static int minCoins2(int[] arr, int aim){
        if (aim < 0){
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N+1][aim+1];
        dp[N][0] = 0;//if (index == arr.length) return rest == 0 ? 0 : Integer.MAX_VALUE; 可以得知
        for(int j=1;j<=aim;j++){
            dp[N][j] = Integer.MAX_VALUE;
        }

        for (int index = N-1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int ans = Integer.MAX_VALUE;

                //逻辑照搬 process
                for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
                    int next = dp[index + 1][rest - zhang * arr[index]];
                    if (next != Integer.MAX_VALUE) {
                        ans = Math.min(ans, next +zhang);
                    }
                }
                dp[index][rest] = ans;
            }
        }

        return dp[0][aim];
    }


    /// 第二种：使用动态规划「不包含枚举」「看图」
    public static int minCoins3(int[] arr, int aim){
        if (aim < 0){
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N+1][aim+1];
        dp[N][0] = 0;//if (index == arr.length) return rest == 0 ? 0 : Integer.MAX_VALUE; 可以得知
        for(int j=1;j<=aim;j++){
            dp[N][j] = Integer.MAX_VALUE;
        }

        for (int index = N-1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[index][rest] = dp[index+1][rest];
                if (rest - arr[index] >= 0 //说明还有剩余，前面还存在之
                        && dp[index][rest-arr[index]] != Integer.MAX_VALUE){ //说明前面有解，并不是无解，都为初始化的系统默认值
                    dp[index][rest] = Math.min(dp[index][rest],dp[index][rest-arr[index]]+1);
                }
            }
        }

        return dp[0][aim];
    }




}
