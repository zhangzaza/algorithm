package code24_BFRAndDP_06;


/// 给定一个正数数组arr
/// 请把arr中所有的数分成两个集合，尽量让两个集合的累加和接近
/// 返回：最接近的情况下，较小的集合的累加和
public class Code01_SubsArr {

    /// 第一种：暴力递归
    /// 只要找出 一种 小于 sum/2 的 最大值
    public static int right(int[] arr) {
        if (arr == null || arr.length <0){
            return 0;
        }
        int sum =0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        return process(arr,0,sum/2);
    }

    /// arr[index...] 可以自由选择，请返回累加和尽量接近rest
    /// 但不能超过rest的情况下，最接近的累加和是多少
    public static int process(int[] arr,int index,int rest) {
        if (index == arr.length){
            return 0;
        }else {
            //1.可能性1，不使用arr[i]
            int p1=process(arr,index+1,rest);
            //2.可能性2，要使用arr[i]
            int p2=0;
            if (rest >= arr[index]){
                p2=arr[index]+process(arr,index+1,rest-arr[index]);
            }
            return Math.max(p1,p2);
        }
    }


    /// 第二种：动态规划
    public static int dp1(int[] arr){
        if (arr == null || arr.length <0){
            return 0;
        }
        int sum =0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        sum /=2;
        int N = arr.length;
        int [][] dp = new int [N+1][sum+1]; //因为是0～N，0～sum，所以要+1
        // 根据base case 的得出
        for (int i = 0; i < sum; i++) {
            dp[N][i] = 0;
        }

        for (int index = N-1; index >=0; index--){
            for (int rest = 0; rest <= sum; rest++){
                // 逻辑照搬
                // 1.可能性1，不使用arr[i]
                int p1=dp[index+1][rest];
                // 2.可能性2，要使用arr[i]
                int p2=0;
                if (rest >= arr[index]){
                    p2=arr[index]+dp[index+1][rest-arr[index]];
                }
                dp[index][rest] = Math.max(p1,p2);
            }
        }

        return dp[0][sum];
    }


}
