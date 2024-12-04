package code27_Fibonacci;

/// 给定一个数N，想象只由0和1 两种字符，组成的所有长度为N的字符串
/// 如果某个字符串，任何0字符的左边都有1紧挨着，认为这个字符串达标
/// 返回有多少达标的字符串
///
/// 思路：
/// 1.现在需要给定一个数 N ，要去实现一个f(N-1) 的函数
/// 2.当 在 N字符串为1 的时候，(N-1) 有多少字符串达标，就返回多少
/// F(N-1) = F(N-2) + F(N-3)
///
/// 当 N 为 1 的时候才可以对以下进行判断
/// 1. N-1 = 1 则满足 f(N-2) 的潜台词
/// 2. N-1 = 0 则满足 f(N-3) 的潜台词
/// 「潜台词：f(i) 表示 还有 i个格子要去填写，但是i前面的那个位置必须是1」
public class Code04_fibonacci3 {

    public static int f1(int n){
        if (n < 1){
            return 0;
        }
        if (n == 1 || n == 2){
            return 1;
        }
        int[][] base = {{1,1},{1,0}};
        int [][] res = matrixPower(base,n-2);
        return res[0][0]+res[1][0];
    }


    public static int[][] matrixPower(int[][] m, int p) {
        int[][] res = new int[m.length][m[0].length];
        for (int i = 0; i < res.length; i++) {
            res[i][i] = 1;
        }

        // res = 矩阵中的1
        int[][] tmp = m; //矩阵1次方
        for (; p != 0; p >>= 1) {
            if ((p & 1) != 0) {
                res = mul(res, tmp);
            }
            tmp = mul(tmp, tmp);
        }
        return res;
    }

    // 矩阵相乘
    public static int[][] mul(int[][] m1, int[][] m2) {
        int[][] res = new int[m1.length][m2[0].length];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m2[0].length; j++) {
                for (int k = 0; k < m2.length; k++) {
                    res[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return res;
    }


}
