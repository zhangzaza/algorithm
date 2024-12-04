package code25_windowsMaxMin;

import java.util.LinkedList;

/// 假设一个固定大小为W的窗口，依次划过arr
/// 返回每一个滑出状况的最大值
/// 例如：arr=[4,3,5,4,3,3,6,7],W=3
/// 返回：[5,5,5,4,6,7]
public class Code01_SortArrMax {

    /// 暴力递归的对数器方法
    public static int[] process(int[] arr,int w){
        if (arr==null || w<1 || arr.length<w){
            return null;
        }
        int N =arr.length;
        int[] res = new int[N-w+1];//存放最大值的数组
        int index=0;
        int L =0;
        int R=w-1;
        while (R<N){
            int max = arr[L];
            for (int i = L+1; i <= R; i++) {
                max = Math.max(max,arr[i]);
            }
            res[index++]=max;
            L++;
            R++;
        }
        return res;
    }


    /// 双端队列实现的 方法解
    public static int[] getMaxWindow(int[] arr,int w){
        if (arr==null || w<1 || arr.length<w){
            return null;
        }
        // 双端队列
        LinkedList<Integer> deque = new LinkedList<>();

        // 目标的数组，和 为该数组服务的res
        int [] res = new int[arr.length-w+1];
        int index=0;

        // 双端队列的入队和出队
        for (int R=0;R<arr.length;R++){
            //1. 如果当前元素大于队尾元素，则将队尾元素出队，并且加入该元素
            while (!deque.isEmpty() && arr[R]>=arr[deque.peekLast()]){
                deque.pollLast();
            }
            deque.addLast(R);
            //2.如果头元素在窗口之外，则将队头元素出队
            if (deque.peekFirst()==R-w){
                deque.pollFirst();
            }
            //3.如果当前窗口已经成立，则将队头元素加入结果数组中
            if (R>=w-1){
                res[index++]=arr[deque.peekFirst()];
            }
        }
        return res;
    }


}
