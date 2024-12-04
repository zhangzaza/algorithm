package code27_Fibonacci;


/// 求斐波那契数列矩阵乘法的方法
/// 1.斐波那契数列的线性求解 (O(N)) 的方法非常好理解
/// 2.同时利用线性代数，也可以改写出另一种表示。｜F(N),F(N-1)｜ = |F(2),F(1)|
/// 3.求出这个二阶矩阵，今儿最快求出这个二阶矩阵的 (N-2) 次方
public class Code02_fibonacci1 {

    //O(logN)
    public static int f3(int n){
        if (n<1){
            return 0;
        }
        if (n==1 || n==2){
            return 1;
        }
        // [1,1]
        // [1,0]
        int[][] base = {{1,1},{1,0}};
        int [][] res = matrixPower(base,n-2);
        return res[0][0]+res[1][0];
    }

    public static int[][] matrixPower(int[][] m,int p){
        int[][] res =new int[m.length][m[0].length];
        for (int i = 0; i < res.length; i++) {
            res[i][i]=1;
        }

        // res = 矩阵中的1
        int [][] tmp =m; //矩阵1次方
        for(;p!=0;p>>=1){
            if ((p&1)!=0){
                res = mul(res,tmp);
            }
            tmp = mul(tmp,tmp);
        }
        return res;
    }

    // 矩阵相乘
    public static int[][] mul(int[][] m1,int[][] m2){
        int[][] res = new int[m1.length][m2[0].length];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m2[0].length; j++) {
                for (int k = 0; k < m2.length; k++) {
                    res[i][j]+=m1[i][k]*m2[k][j];
                }
            }
        }
        return res;
    }

}
