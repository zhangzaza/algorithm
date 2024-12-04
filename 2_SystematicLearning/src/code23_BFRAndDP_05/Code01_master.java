package code23_BFRAndDP_05;


/// 给定3个参数，N，M，K
/// 怪兽有N 滴 血，等着英雄来砍自己
/// 英雄每一次打击，都会让怪兽流失【0～M】的血量
/// 到底流失多少？每一次在【0～M】伤的等概率的获得一个值
/// 求K次打击之后，英雄把怪兽砍死的概率
public class Code01_master {

    /// 第一种：暴力递归
    public static double right1(int N, int M, int K) {
        if (N < 1 || M < 1 || K < 1){
            return 0;
        }
        long all = (long) Math.pow(M+1,K);
        long kill = process1(N,M,K);
        return (double) kill / (double) all;
    }


    // 怪兽还剩N点血
    // 每次的伤害在「0～M」范围上
    // 还有K次可以砍
    // 返回砍死的情况数！
    public static long process1(int N, int M, int K) {
        // 如果提前砍死了，那我剩余的无论怎么砍，都是情况数
        if (N <= 0){
            return (long)Math.pow(M+1,K);
        }
        if (K == 0){
            return N <= 0 ? 1 : 0;
        }
        long res = 0;
        for (int i = 0; i <= M; i++) {
            res += process1(N - i, M, K - 1);
        }
        return res;
    }



    /// 第二种：动态规划 「包含了枚举」
    public static double dp1(int N,int M,int K){
        if (N < 1 || M < 1 || K < 1){
            return 0;
        }
        long all = (long) Math.pow(M+1,K);
        long[][] dp = new long[K+1][N+1];
        dp[0][0] = 1; // 表示最后一刀，并且剩余血量为0的时候，表示成功
        for (int restTimes = 1; restTimes <= K; restTimes++) {
            // 如果提前砍死了，那我剩余的无论怎么砍，都是情况数，还剩余restTimes次，那我剩余的都是情况数
            // M+1 是因为每次砍掉的血量在0~M之间，所以要加1
            dp[restTimes][0] = (long) Math.pow(M+1,restTimes);
            for (int hp = 1; hp <= N; hp++) {
                long ways=0;
                for (int i = 0; i <= M; i++) {
                   if (hp-i>=0){
                       ways += dp[restTimes-1][hp-i];
                   }else{//这里表示的是剩余血量减去伤害i，如果剩余血量小于0，那我剩余的都是情况数量[restTimess-1]
                       ways += (long)Math.pow(M+1,restTimes-1);
                   }
                }
                dp[restTimes][hp] = ways;
            }
        }
        long kill = dp[K][N];
        return (double) kill / (double) all;
    }


    /// 第三种：动态规划 「不包含枚举」
    public static double dp2(int N,int M,int K){
        if (N < 1 || M < 1 || K < 1){
            return 0;
        }
        long all = (long) Math.pow(M+1,K);
        long[][] dp = new long[K+1][N+1];
        dp[0][0] = 1; // 表示最后一刀，并且剩余血量为0的时候，表示成功
        for (int restTimes = 1; restTimes <= K; restTimes++) {
            dp[restTimes][0] = (long) Math.pow(M+1,restTimes);
            for (int hp = 1; hp <= N; hp++) {
                dp[restTimes][hp] = dp[restTimes-1][hp] + dp[restTimes][hp-1];
                if (hp-M-1>=0){
                    dp[restTimes][hp] -= dp[restTimes-1][hp-M-1];
                }else {
                    // 如果已经出格了，那肯定还要减去，因为 还要 砍 restTimes-1 次
                    dp[restTimes][hp] -= (long)Math.pow(M+1,restTimes-1);
                }
            }
        }
        long kill = dp[K][N];
        return (double) kill / (double) all;
    }


}
