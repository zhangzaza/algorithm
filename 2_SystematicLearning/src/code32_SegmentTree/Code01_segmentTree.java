package code32_SegmentTree;


/// 线段树（Segment Tree）是一种高级数据结构，常用于处理数组区间上的动态查询和更新操作。
/// 其主要功能是在数组的一段连续区间上进行快速的查询和修改操作，例如求区间和、区间最小值、区间最大值等。
/// 时间复杂度： O(logn)
public class Code01_segmentTree {


    /// 线段树：
    /// 1.树中的每个位置代表的是每个数组中某个区间的和
    /// 2.父节点为 i，左节点：2i，右节点：2i+1
    /// 3. 1中的树 是一个数组 长度为 4n
    /// 时间复杂度：O(logn)

    public static class SegmentTree {

        //arr[] 为原序列的信息从0开始，但在arr里从1开始的
        //sum[] 模拟线段树维护区间和
        //lazy[] 为累加和懒惰标记
        //change[] 为更新值
        //update[] 为更新懒惰标记
        private int MAXN;
        private int[] arr;
        private int[] sum;
        private int[] lazy;
        private int[] change;
        private boolean[] update;

        public SegmentTree(int[] originArr) {
            MAXN = originArr.length + 1;
            arr = new int[MAXN]; //arr[0] 不用 从1开始使用
            for (int i = 0; i < MAXN; i++) {
                arr[i] = originArr[i-1];
            }
            sum = new int[MAXN << 2];// 用来支持脑补概念中，某一个范围的累加和信息
            lazy = new int[MAXN << 2];// 用来支持脑补概念中，某一个范围没有放下传达的叠加任务
            change = new int[MAXN << 2];// 用来支持脑补概念中，某一个概念有没有更新操作的任务
            update = new boolean[MAXN << 2];// 用来支持脑补概念中，某一个范围更新任务，更新成了什么
        }

        private void pushDown(int rt,int ln,int rn){
            if (update[rt]){
                update[rt << 1] = true;
                update[rt << 1 | 1]= true;
                change[rt << 1] = change[rt];
                change[rt << 1 | 1] = change[rt];
                lazy[rt << 1] = 0;
                lazy[rt << 1 | 1] = 0;
                sum[rt <<1 ]= change[rt] * ln;
                sum[rt << 1 | 1] = change[rt] * rn;
                update[rt] = false;
            }
            if (lazy[rt] != 0){
                lazy[rt << 1] += lazy[rt];
                sum[rt << 1] += lazy[rt] * ln;
                lazy[rt << 1 | 1] += lazy[rt];
                sum[rt << 1 | 1] += lazy[rt] * rn;
                lazy[rt] = 0;
            }
        }

        private void pushUp(int rt){
            sum[rt] = sum[rt << 1] + sum[rt << 1 | 1];
        }

        //在初始化阶段，先把sum数组，填好
        //在arr[l～r]范围上，去build，1～N
        //rt：这个范围在sum中的下标
        public void build(int l,int r,int rt){
            if (l==r){
                sum[rt] = arr[l];
                return;
            }
            int mid = l + ((r-l)>>1);
            build(l,mid,rt<<1);
            build(mid+1,r,rt<<1|1);
            pushUp(rt);
        }


        //L...R,C 任务
        //rt，l～r
        public void add(int L,int R,int C,int l,int r,int rt){
            //任务如果把此事的范围全包了
            if (L <= l && r <= R){
                sum[rt] += C * (r - l + 1);
                lazy[rt] += C;
                return;
            }
            //任务没有把你全包
            //l r mid=(r+l)/2
            int mid =(r+l)>>1;
            pushDown(rt,mid-l+1,r-mid);
            //L~R
            if (L <= mid){
                add(L,R,C,l,mid,rt<<1);
            }
            if (R > mid){
                add(L,R,C,mid+1,r,rt<<1|1);
            }
            pushUp(rt);
        }


        // L~R 所有的值变成了C
        // l~r rt
        public void update(int L,int R,int C,int l,int r,int rt){
            if (L <= l && r <= R){
                update[rt] = true;
                change[rt] = C;
                sum[rt] = C * (r - l + 1);
                lazy[rt] = 0;
                return;
            }
            // 当前任务躲不掉，无法懒更新，要往下发
            int mid = (r + l) >> 1;
            pushDown(rt,mid-l+1,r-mid);
            if (L <= mid){
                update(L,R,C,l,mid,rt<<1);
            }
            if (R > mid){
                update(L,R,C,mid+1,r,rt<<1|1);
            }
            pushUp(rt);

        }

        //1~6 累加和是多少？ 1～8 rt
        public long query(int L,int R ,int l,int r,int rt){
            if (L <= l && r <= R){
                return sum[rt];
            }
            int mid = (r + l) >> 1;
            pushDown(rt,mid-l+1,r-mid);
            long ans = 0;
            if (L <= mid){
                ans += query(L,R,l,mid,rt<<1);
            }
            if (R > mid){
                ans += query(L,R,mid+1,r,rt<<1|1);
            }
            return ans;
        }

    }


}
