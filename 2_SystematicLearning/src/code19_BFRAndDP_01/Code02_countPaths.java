package code19_BFRAndDP_01;

///题目描述
/// 在一个一维数组中，你需要从起始索引 a 移动到目标索引 b总共需要恰好进行 n 步操作。每一步操作，你可以选择向左或向右移动一个单位。
/// 请确定有多少种不同的路径可以在给定的步数内从索引 a 到达索引 b。
///
/// 条件
/// 1.每一步你可以选择向左移动一个索引或向右移动一个索引。
/// 2.总步数正好为 n。
/// 3.返回的应该是从 a 到 b 的所有可能路径数。
public class Code02_countPaths {


    /// 暴力递归的方式
    /// /*
    /// cur：机器人当前来到的位置是cur
    /// rest：机器人还有rest要走
    /// aim：最终的目标是aim
    /// 1～n：数组大小，有哪些位置可以走
    /// 返回：从cur位置，经过rest步，最终能到达aim的方案数
    /// */
    public static int process1(int cur , int rest ,int aim,int n){
        if (rest ==0){
            return cur==aim?1:0;
        }
        //rest>0,还有部署要走！
        if (cur == 1 ){
            // 到了数组第一个位置时，只能往后走，走到2
            return process1(2,rest-1,aim,n);
        }else if (cur==n){
            // 到了数组最后的位置时，只能往前走，走到n-1
            return process1(n-1,rest-1,aim,n);
        }else {
            //在中间这段位置上
            return process1(cur+1,rest-1,aim,n)+process1(cur-1,rest-1,aim,n);
        }
    }


    /// 向动态规划转换，只是加了缓存的方式
    /// /*
    /// cur：机器人当前来到的位置是cur
    /// rest：机器人还有rest要走
    /// aim：最终的目标是aim
    /// 1～n：数组大小，有哪些位置可以走
    /// 返回：从cur位置，经过rest步，最终能到达aim的方案数
    /// */
    public static int ways2(int start ,int rest,int aim,int n){
        int[][] dp = new int[n+1][rest+1];
        for (int i = 0; i < n+1; i++) {
            for (int i1 = 0; i1 < rest+1; i1++) {
                dp[i][i1]=-1;
            }
        }
        // dp[][] 是缓存表
        // dp[cur][rest] == -1 -> process2(cur,rest)之前没有算过
        // dp[cur][rest] != -1 -> process2(cur,rest)之前已经算过了，直接返回这个值就可以
        // n+1 * rest+1
        return process2(start,rest,aim,n,dp);

    }


    /// 「傻缓存」缓存表，记录已经算过的值
    /// 根据process1 加了缓存表而来
    public static int process2(int cur , int rest ,int aim,int n,int[][] dp){
        if (dp[cur][rest]!=-1){
            return dp[cur][rest];
        }
        // 之前没有算过
        int ans =0;
        if (rest ==0){
            ans = cur==aim?1:0;
        }else if (cur==1){
            ans = process2(2,rest-1,aim,n,dp);
        } else if (cur==n) {
            ans = process2(n-1,rest-1,aim,n,dp);
        }else {
            ans = process2(cur+1,rest-1,aim,n,dp)+process2(cur-1,rest-1,aim,n,dp);
        }
        dp[cur][rest]=ans;
        return ans;
    }


    /// 动态规划表
    /// 动态规划只看结果，不看过程，是根据结果得出来的，不是根据过程推导出来的
    public static int ways3(int start ,int rest,int aim,int n){
        int [][] dp = new int[n+1][rest+1];
        dp[aim][0]=1;// 目标结果为1，第一列的其他行全部为0 全部为0
        for (int i = 1; i <= rest; i++) {
            dp[1][i]=dp[2][i-1];
            for (int cur = 2; cur <= n; cur++) {
                dp[cur][i]=dp[cur-1][i-1]+dp[cur+1][i-1];
            }
            dp[n][i]=dp[n-1][i-1];
        }
        return dp[start][rest];
    }


}
