package code41_SubarraysSum;

/// 给定一个正方形矩阵maxtrix，原地调整成顺时针90度转动的样子
/// a b c     g d a
/// d e f     h e b
/// g h i     i f c
public class Code06_matrix90 {

    public static void rotate(int[][] matrix) {
        int a = 0;
        int b = 0;
        int c = matrix.length - 1;
        int d = matrix[0].length - 1;
        while (a < c) {
            rotateEdge(matrix, a++, b++, c--, d--);
        }
    }

    public static void rotateEdge(int[][] matrix, int a, int b, int c, int d) {
        int temp = 0;
        // 进行位置交换
        for (int i = 0; i < d - b; i++) {
            temp = matrix[a][b + i];
            matrix[a][b + i] = matrix[c - i][b];
            matrix[c - i][b] = matrix[c][d - i];
            matrix[c][d - i] = matrix[a + i][d];
            matrix[a + i][d] = temp;
        }
    }


}
