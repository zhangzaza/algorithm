package code05_MergeSort;


//使用非递归的方式 进行归并排序
//时间复杂O(n*logN)
public class Code02_mergeSortWhile {


    //这里的递归形式是从 小到大
    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int mergeSize = 1;
        int N = arr.length;

        //这里需要考虑 integer.max ，step可能会溢出
        //思路：
        //1.拿到步长
        //2.拿到 mid，判断mid是否大于数组
        //3.拿到 L，判断 L 是否小于数组
        //4.拿到 R，判断R是否小于数组，如果不小于并且有数，就取值余下的
        //5.对数组进行操作
        //6.操作完之后重新定义L
        //7.更新步长，防止溢出，重新进入循环

        //1.
        while (mergeSize < N) {
            int L = 0;
            //2.
            while (L < N) {
                //3.
               int mid = L+mergeSize-1;
               if(mid>=N){
                   break;
               }
               //4.
               int R=Math.min(mid+mergeSize,N-1);
               //5.
               merge(arr,L,mid,R);
               //6.
               L=R+1;
            }


            //7.
            if (mergeSize > (N / 2)) {//可能会溢出，在这里进行判断 , 但是step >=(N/2) 就会出错，如果刚刚好是Integer.max 那就会直接刚刚好溢出 到了 2^31
                break;
            } else {
                mergeSize *= 2;
            }
        }
    }


    private static void merge(int[] arr, int l, int mid, int r) {
        int[] help = new int[r - l + 1];
        int i = 0;
        int left = 0;
        int right = mid + 1;

        //如果数组的左右两边都有值
        while (left <= l && right <= mid) {
            help[i++] = help[left] < help[right] ? arr[left++] : arr[right++];
        }

        //下述两个while只会发生一个
        //如果只剩下数组的左边
        while (left <= l) {
            help[i++] = arr[left++];
        }
        //如果只剩下数组的右边
        while (right <= r) {
            help[i++] = arr[right++];
        }

        //将help数组移动进去
        for (i = 0; i < help.length; i++) {
            arr[l + i] = help[i];
        }


    }
}
