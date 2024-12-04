package code22_BFRAndDP_04;

/// 给定5个参数，N,M,row,col,k
/// 表示在N*M的区域上，醉汉Bob初始在(row,col)位置
/// Bob一共要迈出k步，且每步都会等概率向上下左右四个方向走一个单位
/// 任何时候Bob只要离开N*M的区域，就直接死亡
/// 返回K步之后，Bob还在N*M的区域的概率
public class Code05_WalkToDie {

    public static double livePossibility1(int N, int M, int row, int col, int K) {
        return process(N, M, row, col, K)/Math.pow(4,K);
    }

    /// 第一种：暴力递归
    /// 目前在(row,col)位置，还剩rest步，走完了如果还在棋盘中就获得1个生存点，返回总的生存点数
    public static long process (int N, int M, int row, int col, int rest) {
        if (row < 0 || col < 0 || row >= N || col >= M){
            return 0;
        }
        //还在棋盘中
        if (rest == 0){
            return 1;
        }
        //还在棋盘中，还有步数要走
        long up = process(N,M,row-1,col,rest-1);
        long down = process(N,M,row+1,col,rest-1);
        long left = process(N,M,row,col-1,rest-1);
        long right = process(N,M,row,col+1,rest-1);
        return up+down+left+right;
    }


    /// 第二种：改动态规划
    public static double livePossibility2(int N, int M, int row, int col, int K) {
        long[][][] dp=new long[N][M][K+1];
        // 先画出生存的区域
        for (int i = 0; i < N; i++){
            for (int j = 0; j < M; j++){
                dp[i][j][0]=1;
            }
        }

        //层数
        for (int rest = 1; rest <= K; rest++){
            //平面区域属
            for (int i = 0; i < N; i++){
                for (int j = 0; j < M; j++){
                    dp[i][j][rest] += pick(dp,N,M,i-1,j,rest-1);// 上
                    dp[i][j][rest] += pick(dp,N,M,i+1,j,rest-1);// 下
                    dp[i][j][rest] +=pick(dp,N,M,i,j-1,rest-1);// 左
                    dp[i][j][rest] += pick(dp,N,M,i,j+1,rest-1);// 右
                }
            }
        }
        return (double) dp[row][col][K]/Math.pow(4,K);
    }


    public static long pick(long[][][]dp,int N,int M,int r,int c,int rest){
        if (r < 0 || c < 0 || r >= N || c >= M){
            return 0;
        }
        return dp[r][c][rest];
    }




}
