package code22_BFRAndDP_04;


/// 给定一个二维组matrix，一个人必须从左上角出发，最后到达右下角
/// 沿途只可以向下或者向右走，沿途的数字都累加就是距离累加和
/// 返回最小距离累加和
public class Code01_matrixPathSum {


    /// 第一种：最经典的动态规划
    public static int minPathSum1(int [][] matrix){
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return 0;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        int [][] dp = new int[row][col];
        dp[0][0] = matrix[0][0];
        // 第一列
        for (int i = 1; i < row; i++) {
            dp[i][0] = dp[i - 1][0] + matrix[i][0];
        }
        // 第一行
        for (int j = 1; j < col; j++) {
            dp[0][j] = dp[0][j - 1] + matrix[0][j];
        }
        for (int i = 1; i < row; i++){
            for (int j = 1; j < col; j++){
                dp[i][j] = Math.min(dp[i - 1][j],dp[i][j - 1]) + matrix[i][j];
            }
        }
        return dp[row - 1][col - 1];
    }



    /// 第二种：动态规划「对空间进行压缩」
    public static int minPathSum2(int [][] matrix){
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return 0;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        int [] arr = new int[col];
        arr[0] = matrix[0][0];
        /// 第一行
        for (int i = 1; i < row; i++) {
            arr[i] += arr[i - 1] + matrix[0][i];
        }
        /// 从第二行开始
        for (int i = 1; i < row; i++) {
            //每行的开头只能从上一个加
            arr[0]+= matrix[i][0];
            for (int j = 1; j < col; j++) {
                arr[j] = Math.min(arr[j - 1],arr[j]) + matrix[i][j];
            }
        }
        return arr[col - 1];
    }


    /// 总结：
    ///1.只要依赖左边或者依赖上方，就可以压缩空间
    ///2.或者依赖上面，左上方，也可以进行压缩
    ///3.依赖左边，上边，左上角，也可以进行压缩

    /// 提出：如果是一百万列，但是只有四行，那需要怎么压缩？
    /// 行和列中，定义的一维需要选短的

    /// 重点：优化了存储空间，但是没有优化时间复杂度，是一个小技巧

}
