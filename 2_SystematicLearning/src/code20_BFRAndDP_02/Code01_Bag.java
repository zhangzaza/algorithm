package code20_BFRAndDP_02;


///0-1 背包问题的动态规划解法
///0-1 背包问题是最常见和基础的形式，通常用动态规划来解决：
///
///定义状态:
///1.令 dp[i][w] 表示前 i 件物品中能在总重量不超过 w 的条件下获得的最大价值。
///状态转移方程:
///1.不选择第 i 个物品：dp[i][w] = dp[i-1][w]
///2.选择第 i 个物品：dp[i][w] = dp[i-1][w-weight[i]] + value[i]
///3.因此，dp[i][w] = max(dp[i-1][w], dp[i-1][w-weight[i]] + value[i])，前提是 w >= weight[i]。
///初始化:
///1.如果没有物品可选（即 i = 0），那么 dp[0][w] = 0，因为没有物品可以放入背包。
///2.如果背包容量为零（即 w = 0），那么 dp[i][0] = 0，因为背包不能容纳任何物品。
///计算结果:
///最终答案为 dp[n][W]，其中 n 是物品的总数，W 是背包的最大承重。

public class Code01_Bag {

    /*
    * 所有的货，重量和价值，都在w和v数组里
    * 为了方便，其中没有负数
    * bag是背包容量，不能超过这个载重
    * 返回：不超重的情况下，能够得到的最大价值
    * */


    /// 第一种：暴力递归
    public static int maxValue(int[] w, int[] v, int bag) {
        if (w==null||v==null||w.length!=v.length||w.length==0){
            return 0;
        }
        //尝试函数
        return  process(w,v,0,bag);
    }


    // 当前考虑到了index号货物，index 。。。 所有的货物可以自由选择
    // 做的选择不能超过背包容量
    // 返回最大值
    public static int process(int[] w, int[] v, int index, int rest) {
        //1.base case
        if (index==w.length){
            return 0;
        }
        if (rest<0){ // 可能存在0重量，但是有价值的货物
            return -1;
        }

        // 2.尝试所有选择
        // 2.1.不要当前的货物
        int p1 = process(w,v,index+1,rest);
        // 2.2.要当前的货物，但是要判断是否超过背包容量
        int p2 =0;
        int next = process(w,v,index+1,rest-w[index]);//如果返回的是-1，说明当前货物不能放入背包，不能选择
        if (next!=-1){
            p2 = v[index]+next;
        }

        // 3.返回最大值
        return Math.max(p1,p2);

    }


    /// 第二步：dp改进
    /// 尝试模型
    public static int dp(int[] w,int[] v,int bag){
        if (w==null||v==null||w.length!=v.length||w.length==0){
            return 0;
        }
        int N = w.length;
        // index 0~N
        // rest 0~bag
        int dp[][] = new int[N+1][bag+1];
        // dp[N][.....] =0 ，从「if (index==w.length) return 0;」得知
        for(int index =N-1;index>=0;index--){
            for(int rest=0;rest<=bag;rest++){
                int p1=dp[index+1][rest];
                int p2=0;
                int next=rest-w[index]<0?-1:dp[index+1][rest-w[index]];
                if (next!=-1){
                    p2=v[index]+next;
                }
                dp[index][rest]=Math.max(p1,p2);
            }
        }

        return dp[0][bag]; // 返回的是第一个要求的数值



    }


}
