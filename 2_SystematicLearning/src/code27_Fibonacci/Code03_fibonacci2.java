package code27_Fibonacci;

/// 第一年农场有1只成熟的母牛A，往后的每年：
/// 1.每一只成熟的母牛都会生一只母牛
/// 2.每一只新出生的母牛都在出生的第三年成熟
/// 3.每一只母牛永远不会死
/// 返回N年后牛的数量
/// 思路： 矩阵快速幂 F(n) = F(n-1)+F(n-3)
///     F(n-1) ： 去年有多少牛活下来
///     F(n-3) ： 去年有多少牛能生
/// 扩展：如果是牛五年后会死亡，F(n) = F(n-1)+F(n-3)-F(n-5) 「F(n-5)：去年死去的牛」
///
/// 公式： F(n) = F(n.....i) = F(i....1) * |矩阵|^(n-i)   「非常重要」
public class Code03_fibonacci2 {


    public static int f(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2 || n == 3) {
            return n;
        }
        // 这个是通过线性代数手动推算
        int[][] base = {
                {1, 1, 0},
                {0, 0, 1},
                {1, 0, 0}
        };
        int[][] res = matrixPower(base, n - 3);
        //这里的3 ，2，1 ：｜1，2，3，4，5，9......｜
        return 3 * res[0][0] + 2 * res[0][1] + res[0][2];
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
