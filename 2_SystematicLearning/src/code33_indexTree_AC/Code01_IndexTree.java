package code33_indexTree_AC;

///IndexTree
/// 特点：
/// 1.支持区间查询
/// 2.没有线段树那么强，但是非常容易改成一维，二维，三维的结构
/// 3.支持单点更新
///
/// 特点：支持快速的单点更新，区间更新慢，「线段树比IndexTree强一些」
public class Code01_IndexTree {

    /// 思路：
    /// 1.前缀和数组，就能马上知道[L.....R]区间和  {有一个问题，如果arr发生变化，前缀和会发生很大的变化}
    /// 2.前缀和数组 结构看图
    /// 3.add的话就每个数进行累加，某个数字改变的话，就进行买个进行更改「看图」
    /// 时间复杂度：O(logN)

    public static class IndexTree{

        private int[] tree;
        private int N;

        //0位置弃而不用
        public IndexTree(int size){
            N = size;
            tree = new int[N+1];
        }


        //1~index 累加和是多少？
        public int sum(int index){
            int ret =0;
            while(index>0){
                ret += tree[index];
                index -= index & -index;
            }
            return ret;
        }


        // index & ~index : 提取出index最右侧的1出来
        // index ：          0011001000
        // index & ～index ：0000001000
        public void add(int index ,int d){
            while(index <= N){
                tree[index] += d;
                index += index & -index;
            }
        }


    }

}
