package code42_QuadrangleInequality;

/// 题目一：
/// 给定一个非负数组arr，长度为N
/// 那么有N-1种方案可以把arr切成左右两部分
/// 每一种方案都有，min{左部分累加和，右部分累加和}
/// 求那么多方案中，min{左部分累加和，右部分累加和}的最大值是多少？
/// 整个过程要求时间复杂度O(N)
///
/// 需要返回一个数组 ，这个数组的所有长度 就是 N-1，每个位置都是上述问题的 0～index「当前位置」 所对应的答案


public class Code02_twoSideSumArr {

    // 求原来的数组arr中，arr[L....R]的累加和
    public static int sum(int[] arr, int L, int R) {
        return arr[R+1]-arr[L];
    }


    public static int[] bestSplit(int[] arr){
        if (arr == null || arr.length == 0){
            return new int[0];
        }

        int N = arr.length;
        int[] ans = new int[N];

        ans[0] =0;
        //arr = {5,3,1,3}
        //       0 1 2 3
        //sum ={0,5,8,9,12}
        //      0 1 2 3 4
        //0~2 -> sum[3] - sum[0]
        //1~3 -> sum[4] - sum[1]
        int [] sum = new int[N+1];
        for (int i = 1; i <= N; i++) {
            sum[i+1] = sum[i] + arr[i];
        }
        //最优划分
        //0~range-1上，最优划分是左部分[0~best) 右部分[best+1 ~ range-1]
        int best =0;
        for (int range = 1; range < N; range++) {
           while(best +1 < range){
               int before =Math.min(sum(sum,0,best),sum(sum,best+1,range));
               int after = sum(sum,0,best +1)-sum(sum,best+2,range);
               // 注意，一定要是>=,只会>会出错
               // 课上会讲解
               if (after >= before){
                   best++;
               }else {
                   break;
               }
           }
           ans[range] = Math.min(sum(sum,0,best),sum(sum,best+1,range));
        }
        return ans;
    }



}
