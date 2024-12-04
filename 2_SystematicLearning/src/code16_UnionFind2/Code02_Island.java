package code16_UnionFind2;


import code15_Greedy2_UnionFind1.Code05_UnionFind;

import java.util.ArrayList;
import java.util.List;

/// 岛问题
/// 给定一个二维数组matrix，里面的值是1或者0，1代表陆地，0代表水域。
/// 上下左右相邻的1认为是连成一片岛，求这个二维数组中岛的数量。
public class Code02_Island {


    /// 时间复杂度O(M*N)，空间复杂度O(1)
    public static int numIslands(char[][] board) {
        int islandNum = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '1') {
                    islandNum++;
                    infect(board, i, j);//这里感染了几次就是有几个岛屿
                }
            }
        }
        return islandNum;
    }

    public static void infect(char[][] board, int i, int j) {
        if (i < 0 || i >= board.length || j < 0 || j == board[0].length || board[i][j] != '1') {
            return;
        }
        board[i][j] = '2'; //防止递归走不完
        infect(board, i - 1, j);
        infect(board, i + 1, j);
        infect(board, i, j - 1);
        infect(board, i, j + 1);
    }


    /// 下述是使用 并查集 进行解决问题的代码
    /// 将 二维数组 转换成了 一维数组
    public static class UnionFind {
        private int[] parent;
        private int[] size;
        private int[] help;
        private int sets;
        private int col;


        public UnionFind(char[][] board) {
            col = board[0].length;
            sets = 0;
            int rows = board.length;
            int length = rows * col;
            parent = new int[length];
            size = new int[length];
            help = new int[length];
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < col; c++) {
                    if (board[r][c] == '1') {
                        int i = index(r, c);
                        parent[i] = i;
                        size[i] = 1;
                        sets++;
                    }
                }
            }
        }

        private int index(int rows, int c) {
            return rows * col + c;
        }


        private int find(int i) {
            int hi = 0;
            while (i != parent[i]) {
                help[hi++] = i;
                i = parent[i];
            }
            for (hi--; hi >= 0; hi--) {
                parent[help[hi]] = i;
            }
            return i;
        }

        public void union(int r1, int c1, int r2, int c2) {
            int i1 = index(r1, c1);
            int i2 = index(r2, c2);
            int f1 = find(i1);
            int f2 = find(i2);
            if (f1 != f2) {
                if (size[f1] > size[f2]) {
                    size[f1] += size[f2];
                    parent[f2] = f1;
                } else {
                    size[f2] += size[f1];
                    parent[f1] = f2;
                }
                sets--;
            }
        }
    }

    public static int numIslands2(char[][] board) {
        int row = board.length;
        int col = board[0].length;
        UnionFind unionFind = new UnionFind(board);

        for (int j = 0; j < col; j++) {
            if (board[0][j - 1] == '1' && board[0][j] == '1') {
                unionFind.union(0, j - 1, 0, j);
            }
        }

        for (int i = 1; i < row; i++){
            if (board[i - 1][0] == '1' && board[i][0] == '1'){
                unionFind.union(i - 1, 0, i, 0);
            }
        }


        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (board[i][j] == '1') {
                    if (board[i - 1][j] == '1') {
                        unionFind.union(i - 1, j, i, j);
                    }
                    if (board[i][j - 1] == '1') {
                        unionFind.union(i, j - 1,i, j);
                    }
                }
            }
        }


        return unionFind.sets;
    }


}
