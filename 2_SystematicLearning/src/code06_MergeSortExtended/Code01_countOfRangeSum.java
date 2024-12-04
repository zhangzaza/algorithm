package code06_MergeSortExtended;


//测试链接：https://leetcode.com/problems/count-of-range-sum/
//具体问题：给顶一个数组arr，两个整数lower和upper，返回arr中有多少个字数组的累计和在「lower，upper」范围上的
//
//思路：
//1.生成前缀和数组。「如果暴力求和只是用这个，复杂度是O(N^2)」
//
//2.前缀和数组中，原题目可以变成 「这是思路」
//   index为0结尾的数组中有多少个子数组在[lower,upper]中，「字数组为 0-0」
//   index为1结尾的数组中有多少个子数组在[lower,upper]中，「字数组为 0-1，1-1」
//   index为2结尾的数组中有多少个子数组在[lower,upper]中，「字数组为 0-2，1-2，2-2」
//    .....
//   index为 arr.length 结尾的数组中有多少个子数组在[lower,upper]中，「字数组为 0-arr.length，1-arr.length，2-arr.length,....(arr.length-1)-arr.length,arr.length-arr.length 」
//「再对上面的数进行累加，如何累加呢？」
// 转换 index为n结尾的数组中有多少个字数组在[lower,upper]中？在求这个问题时，index为x，x<n ,给出的要求为[lower，upper]，则 sum[x]上的数应在范围[sum[n]-upper,sum[n]-lower]
//
//3.转换为归并排序
// 在以 index <=R 的子数组中求 有多少个子组数，第一次 L为0，mid=（L+R）/2
// process(L ,mid) + process(R+mid) + merge(L,R) 「解释： 左边有多少？+ 右边有多少？+ L到R中有多少」 如此反复递归
//
//4.对processCount中merge进行修改，process返回int
//目的：遍历右数组 ，查看以右数组 以 index为n 为底的数 前有多少数满足 [sum[n]-upper,sum[n]-lower]
//注意：因为左数组有序，右数组有序，所以两边都有序 「实现指针不回退，实现了时间复杂度O(N*logN)」
//
//5.processCount的merge计数中有两个index指针 L R
//需要计算有多少满足条件 [-1，2]的子数组
//举例sums ：[2,5,8,9,11,15] | [6,7,7,8,10,11] ，设定一个ans=0
//对于6来说 L为 0，R为0，需要找到 [6-2,6-(-1)] 为 [4,7], L向右移动，R向右移动，L为1，R也为1，个数为 ans+= L-R+1=1
//对于7来说 L为 1，R为1，需要找到 [5,8],L向右移动，R向右移动，L为1，R也为2，个数为 ans+=L-R+1=3
// ....「以此类推，L和R指针是不回退的」
//求出这里的累计和ans返回

public class Code01_countOfRangeSum {

    public static int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        //1.求出前缀和数组
        int[] sum = new int[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }

        return processCount(sum,0,nums.length-1,lower,upper);

    }

    //arr[L....R] 已经不传进来了，只传进来sum(前缀和数组)
    //在原始的arr[L...R]中，有多少个字数组累加和在[lower，upper]上
    public static int processCount(int[] sums,int L,int R, int lower, int upper) {
        if(L==R){
            if (sums[L] >=lower && sums[L]<=upper) {
                return 1;
            }else {
                return 0;
            }
        }

        int mid = (L+R)/2;
        //3.4.
        int leftCounts= processCount(sums,L,mid,lower,upper);
        int rightCounts =processCount(sums,mid+1,R,lower,upper);
        int totalCounts= merge(sums,L,mid,R,lower,upper);
        return totalCounts+leftCounts+rightCounts;

    }


    public static int merge(int[] sums,int L,int mid,int R,int lower,int upper) {
        //5.
        int ans=0;
        int windowL=L;
        int windowR=L;
        for (int i = mid+1; i < R; i++) {
            long min =sums[i]-upper;
            long max =sums[i]-lower;
            while (windowR<=mid && sums[windowR]<=max) {
                windowR++;
            }
            while (windowL<=mid && sums[windowL]<min) {//这里不包括等于
                windowL++;
            }
            //这里不用 windowR-windowL +1 ，是因为上述的条件 [sum[n]-upper,sum[n]-lower+1)这个区间
            ans +=windowR-windowL;
        }

        //经典递归排序
        int [] help = new int[L-R+1];
        int i = 0;
        int left =0;
        int right = mid+1;
        while (left <= L && right <= mid) {
            help[i++]= help[left]<help[right]?sums[left++]:sums[right++];
        }
        while (left <= L) {
            help[i++]= sums[left++];
        }
        while (right <= R) {
            help[i++]= sums[right++];
        }
        for (i = 0; i < help.length; i++) {
            sums[L+i] = help[i];
        }

        return ans;

    }



}
