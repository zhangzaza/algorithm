package code16_UnionFind2;


import java.util.ArrayList;
import java.util.List;

/// 岛屿问题 ： 并查集
/// leetCode 305 「hard」
/// 测试链接：https://leetcode.com/problems/number-of-islands-ii/
public class Code03_Island2 {



    public static List<Integer> numIslands(int m, int n, int[][] positions) {
        List<Integer> ans = new ArrayList<>();
        UnionFind unionFind = new UnionFind(m, n);
        for (int[] position : positions) {
            ans.add(unionFind.connect(position[0], position[1]));
        }
        return ans;
    }



    public static class UnionFind {
        private int[] parent; // 每个节点的父节点
        private int[] size;   // 每个集合的大小
        private int[] help;   // 辅助数组用于路径压缩
        private final int row; // 网格的行数
        private final int col; // 网格的列数
        private int sets;     // 当前连通分量的数量

        // 构造函数，初始化并查集
        public UnionFind(int m, int n) {
            row = m;
            col = n;
            sets = 0;
            int len = row * col; // 总的单元格数量
            parent = new int[len];
            size = new int[len];
            help = new int[len];
        }

        // 将二维网格的坐标转换为一维数组的索引
        public int index(int r, int c) {
            return r * col + c;
        }

        // 寻找节点 i 的根节点，并进行路径压缩
        public int find(int i) {
            int hi = 0; // 用于记录路径压缩所需的路径数组索引
            while (i != parent[i]) { // 如果 i 不是根节点
                help[hi++] = i; // 记录路径上的节点
                i = parent[i];  // 更新 i 为其父节点
            }
            for (int j = 0; j < hi; j++) { // 路径压缩，将路径上的所有节点直接指向根节点
                parent[help[j]] = i;
            }
            return i;
        }

        // 连接 (r, c) 位置的陆地，如果它是新的陆地，则将其与周围的陆地连接
        public int connect(int r, int c) {
            int index = index(r, c); // 获取该位置在一维数组中的索引
            if (size[index] == 0) { // 如果该位置尚未初始化（即为新陆地）
                parent[index] = index; // 初始化自身为父节点
                size[index] = 1;       // 初始化大小为1
                sets++;                // 增加连通分量计数
                // 尝试连接上下左右的邻居
                union(r-1, c, r, c);
                union(r+1, c, r, c);
                union(r, c-1, r, c);
                union(r, c+1, r, c);
            }
            return sets; // 返回当前的连通分量数量
        }

        // 合并两个位置的集合
        public void union(int r1, int c1, int r2, int c2) {
            // 检查边界条件，确保位置在网格内
            if (r1 < 0 || r1 == row || r2 < 0 || r2 == row || c1 < 0 || c1 == col || c2 < 0 || c2 == col) {
                return;
            }
            int i1 = index(r1, c1); // 获取位置 (r1, c1) 的一维索引
            int i2 = index(r2, c2); // 获取位置 (r2, c2) 的一维索引
            if (size[i1] == 0 || size[i2] == 0) { // 如果两个位置中有一个不是陆地，则不进行合并
                return;
            }

            int f1 = find(i1); // 找到位置 i1 的根节点
            int f2 = find(i2); // 找到位置 i2 的根节点
            if (f1 != f2) { // 如果它们不在同一个集合中
                // 按大小合并，将小的集合合并到大的集合中
                if (size[f1] >= size[f2]) {
                    size[f1] += size[f2];
                    parent[f2] = f1;
                } else {
                    size[f2] += size[f1];
                    parent[f1] = f2;
                }
                sets--; // 合并成功后，连通分量数量减少
            }
        }
    }



}
