package code21_BFRAndDP_03;


/// 请同学们自行搜索或者想象一个象棋的棋盘
/// 然后把整个象棋放入第一个象限，棋盘的最左下脚是(0,0)位置
/// 那么整个棋盘就是横坐标上9条线，纵坐标上10条线的区域
/// 给你 三个 x，y，k
/// 返回 “马” 从(0,0)位置出发，必须走k步
/// 最后落在(x,y)上的方法数有多少种？
public class Code02_Chess {

    /// 第一种：暴力递归
    public static int ways(int x, int y, int k) {
        return process(0, 0, k, x, y);
    }

    /// 当前位置为(x,y),还剩rest步，从(x，y)出发，有多少种方法跳到(a,b)
    public static int process(int x, int y, int rest, int a, int b) {
        // 越界，棋盘是10*9
        if (x < 0 || x > 9 || y < 0 || y > 8) {
            return 0;
        }
        if (rest == 0) {
            return x == a && y == b ? 1 : 0;
        }
        int ways = process(x + 2, y + 1, rest - 1, a, b);
        ways += process(x + 1, y + 2, rest - 1, a, b);
        ways += process(x - 1, y + 2, rest - 1, a, b);
        ways += process(x - 2, y + 1, rest - 1, a, b);
        ways += process(x - 2, y - 1, rest - 1, a, b);
        ways += process(x - 2, y - 2, rest - 1, a, b);
        ways += process(x + 1, y - 2, rest - 1, a, b);
        ways += process(x + 2, y - 2, rest - 1, a, b);
        return ways;
    }


    /// 第二种：动态规划 「『三维动态规划』」
    /// 1.(x,y) 坐标为一层，代表一个位置
    /// 2.rest坐标为z轴坐标
    public static int dp(int x, int y, int k) {
        int[][][]dp = new int[10][9][k+1];
        dp[x][y][0] =1;//其他坐标轴都为0 ,这里表示我的目标位，0层的x，y位置
        for(int rest=1;rest<=k;rest++){
            for(int i=0;i<10;i++){
                for(int j=0;j<9;j++){
                    int ways = pick(dp,x + 2, y + 1, rest - 1);
                    ways += pick(dp,x + 1, y + 2, rest - 1);
                    ways += pick(dp,x - 1, y + 2, rest - 1);
                    ways += pick(dp,x - 2, y + 1, rest - 1);
                    ways += pick(dp,x - 2, y - 1, rest - 1);
                    ways += pick(dp,x - 2, y - 2, rest - 1);
                    ways += pick(dp,x + 1, y - 2, rest - 1);
                    ways += pick(dp,x + 2, y - 2, rest - 1);
                    dp[i][j][rest] = ways;
                }
            }
        }
        return dp[0][0][k];//这里表示我的初始位置，k层的x，y位置
    }

    /// 将上面的逻辑判断拿出来
    public static int pick(int[][][]dp,int x,int y,int rest){
        if (x < 0 || x > 9 || y < 0 || y > 8){
            return 0;
        }
        return dp[x][y][rest];
    }



}
