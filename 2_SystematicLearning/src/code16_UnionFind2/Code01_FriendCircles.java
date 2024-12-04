package code16_UnionFind2;

//leetCode 547 原题
//测试链接：https://leetcode.com/problems/friend-circles/
/// 有n城市。其中一些是连接的，而另一些则没有。如果a城市与b城市直接连接， b城市与c城市直接连接，则a城市与c城市间接连接。
/// 给定一个nxn矩阵isConnected其中，如果i th城市和j th城市直接相连，则 isConnected[i isConnected[i][j] = 1 ，否则isConnected[i][j] = 0 。
/// 返回省份总数。
///
/// 说明：
/// 假设我们有 4 个城市，连接关系如下：
///
/// 城市 0 连接城市 1
/// 城市 1 连接城市 2
/// 城市 2 不连接城市 3
/// 城市 3 独立
/// 可以用一个 4x4 的邻接矩阵表示如下：
///
/// isConnected = [
///   [1, 1, 0, 0],
///   [1, 1, 1, 0],
///   [0, 1, 1, 0],
///   [0, 0, 0, 1]
/// ]
/// 图示
/// 我们可以将这些城市的连接关系画成图，得到以下结构：
///
///     0
///     |
///     1
///     |
///     2
///
/// 3
/// 在这个图中：
///
/// 城市 0、1 和 2 形成了一个连通分量（省份）。
/// 城市 3 是另一个独立的连通分量（省份）。
public class Code01_FriendCircles{

    public static class UnionFind{
        // parent[i] = k，表示i的父节点是k
        private int[] parent;
        //size[i] = k，表示i所在的集合的元素个数
        private int[] size;
        //辅助结构
        private int[] help;
        //一共有多少个集合
        private int sets;

        public UnionFind(int n){
            parent = new int[n];
            size = new int[n];
            help = new int[n];
            sets = n;
            for(int i = 0; i < n; i++){
                parent[i] = i;
                size[i] = 1;
            }
        }


        // 从i开始一直往上，往上道不能再往上，代表节点，返回
        // 这个过程要做路径压缩
        private  int find(int i){
            int hi=0;
            while (i !=parent[i]){
                help[hi++] = i;
                i = parent[i];
            }
            for (hi--; hi >= 0; hi--) {
                parent[help[hi]] = i;
            }
            return i;
        }


        public void union(int i, int j){
            int f1 = find(i);
            int f2 = find(j);
            //合并操作
            if (f1 != f2){
                if (size[f1] > size[f2]){
                    size[f1] += size[f2];
                    parent[f2] = f1;
                }else {
                    size[f2] += size[f1];
                    parent[f1] = f2;
                }
            }
            sets--;
        }

        public int sets(){
            return sets;
        }

    }


    public static int findCircleNum(int[][] M){
        int N=M.length;
        //{0} {1} {2} {N-1}
        UnionFind unionFind = new UnionFind(N);
        //只需要遍历一半即可
        for (int i = 0; i < N; i++) {
            for (int i1 = i+1; i1 < N; i1++) {
                if (M[i][i1] == 1){
                    unionFind.union(i,i1);
                }
            }
        }
        return unionFind.sets();
    }




}
