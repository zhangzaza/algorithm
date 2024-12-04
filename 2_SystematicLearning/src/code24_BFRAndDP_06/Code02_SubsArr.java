package code24_BFRAndDP_06;

/// 给定一个正数数组arr，请吧arr中所有的数分成两个集合
/// 如果arr长度为偶数，两个集合包含数的个数要一样多
/// 如果arr长度为奇数，两个集合包含数的个数必须只差一个
/// 请尽量让两个集合的累加和 接近
/// 返回；
/// 最接近的情况下，较小集合的累加和
public class Code02_SubsArr {

    /// 第一种：暴力递归
    public static int right(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }

        if (arr.length % 2 == 0) {
            return process(arr, 0, arr.length / 2, sum / 2);
        } else {
            return Math.max(process(arr, 0, arr.length / 2, sum / 2), process(arr, 0, arr.length / 2 + 1, sum / 2));
        }
    }

    // arr[index...] 自由选择，
    // index为数组的下标
    // 挑选的个数一定要是picks个，
    // 累加和<=rest,离rest最近的返回
    // 返回值是累加和
    public static int process(int[] arr, int index, int picks, int rest) {
        if (index == arr.length) {//如果已经遍历完
            return picks == 0 ? 0 : -1; // 但是还需要picks数量，判断如果picks数量为0，返回累加和为0，否则返回-1；-1表示该方法不奏效
        } else {
            //1.可能性1，不使用arr[i]
            int p1 = process(arr, index + 1, picks, rest);

            //2.可能性2，就是要使用arrp[i]这个数
            int p2 = -1;
            int next = -1;
            if (arr[index] <= rest) {
                next = process(arr, index + 1, picks - 1, rest - arr[index]);
            }
            if (next != -1) {
                p2 = arr[index] + next;
            }
            return Math.max(p1, p2);
        }
    }


    /// 第二种：动态规划
    public static int dp(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        sum = sum / 2;
        int N = arr.length;
        int M = (N + 1) / 2;
        int[][][] dp = new int[N + 1][M + 1][sum + 1];


        //完善 dp 表
        //========
        //1.所有位置没有计算之前，所有位置都是无效的
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= M; j++) {
                for (int k = 0; k <= sum; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }
        //2.base case
        for (int rest = 0; rest <= sum; rest++) {
            dp[N][0][rest] = 0;
        }
        //3.逻辑照办
        for (int index = N - 1; index >= 0; index--) {
            for (int picks = 0; picks <= M; picks++) {
                for (int rest = 0; rest <= sum; rest++) {
                    // 照搬在这里
                    //1.可能性1，不使用arr[i]
                    int p1 = dp[index + 1][picks][rest];
                    //2.可能性2，就是要使用arrp[i]这个数
                    int p2 = -1;
                    int next = -1;
                    if (picks - 1 >= 0 && arr[index] <= rest) {// 这里进行了修改，因为如果picks为0 的时候会越界
                        next = dp[index + 1][picks - 1][rest - arr[index]];
                    }
                    if (next != -1) {
                        p2 = arr[index] + next;
                    }
                    dp[index][picks][rest] = Math.max(p1, p2);
                }
            }
        }


        //========
        if (arr.length % 2 == 0) {
            return dp[0][arr.length / 2][sum];
        } else {
            return Math.max(dp[0][arr.length / 2][sum], dp[0][arr.length / 2 + 1][sum]);
        }

    }
}
