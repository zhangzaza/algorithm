package code25_windowsMaxMin;

import java.util.LinkedList;

/// 给定一个正数数组arr，和一个整数num
/// 某个arr中的子数组sub，如果想达标，必须满足：sub中最大值-sub中最小值<=num「子数组为连续不断」
/// 返回arr中达标子数组的数量
public class Code02_SubArrLessThanNum {

    /// 暴力的对数器方法
    public static int right(int[] arr,int sum){
        if (arr==null || arr.length==0 || sum<0){
            return 0;
        }
        int N = arr.length;
        int count =0 ;
        for (int L = 0; L < N; L++){
            for(int R = L; R < N; R++){
                int max =arr[L];
                int min =arr[L];
                for (int i = L+1; i <= R; i++){
                    max = Math.max(max,arr[i]);
                    min = Math.min(min,arr[i]);
                }
                if (max-min<=sum){
                    count++;
                }
            }
        }
        return count;
    }

    /// 思路：
    /// 1.L从0开始，R往右走，如果符合条件，R++ ，记录数量 R-L+1
    /// 2.L从1开始，R继续从上次达标的位置往右走，如果符合条件，R++，记录数量 R-L+1
    /// 「因为 1.的窗口 包含了 2.的窗口 所以  1.的最后达标位置是『0....R』，与之对应2.在上述位置中肯定也达标，所以R从1.不达标的位置进行判断」
    public static int num(int[] arr,int sum){
        if (arr==null || arr.length==0 || sum<0){
            return 0;
        }
        int N = arr.length;
        int count =0;
        // 双端队列 存储下标, 最大值的滑动窗口/最小值的滑动窗口
        LinkedList<Integer> maxWindow = new LinkedList<>();
        LinkedList<Integer> minWindow = new LinkedList<>();

        int R=0;
        for (int L = 0; L < N; L++){
            // [L......R) 「R初次不达标的时候停止」
            while (R<N){
                // 1.删除 最大值中滑动窗口的尾部元素
                while (!maxWindow.isEmpty() && arr[maxWindow.peekLast()]<=arr[R]){
                    maxWindow.pollLast();
                }
                maxWindow.addLast(R);
                // 2.删除 最小值中滑动窗口的尾部元素
                while (!minWindow.isEmpty() && arr[minWindow.peekLast()]>=arr[R]){
                    minWindow.pollLast();
                }
                minWindow.addLast(R);
                // 3.如果满足条件，R向右边进行移动
                if (arr[maxWindow.peekFirst()]-arr[minWindow.peekFirst()]>sum){
                    break;
                }else{
                    R++;
                }
            }
            count += R-L;
            // 更新最大值：L向右移动，窗口缩小，判断最大值是否是头元素，是的话进行删除
            if (maxWindow.peekFirst()==L){
                maxWindow.pollFirst();
            }
            // 更新最小值：L向右移动，窗口缩小，判断最小值是否是头元素，是的话进行删除
            if (minWindow.peekFirst()==L){
                minWindow.pollFirst();
            }
        }
        return count;
    }

}
