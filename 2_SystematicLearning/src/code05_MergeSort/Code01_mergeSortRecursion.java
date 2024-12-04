package code05_MergeSort;


//递归排序 递归方式
//归并排序（Merge Sort）是一种基于分治法的比较排序算法。它的核心思想是将一个大问题递归地分解成更小的子问题，分别解决这些子问题后，再将它们合并成一个有序的结果。具体来说，归并排序将待排序的数组（或列表）分成两个子数组，分别对这两个子数组进行排序，然后将它们合并成一个有序的数组。
//
//归并排序的步骤
//1.分解（Divide）：
//将数组分成两个子数组，递归地对每个子数组进行归并排序。
//2.解决（Conquer）：
//当子数组的大小为1时，认为它们已经有序，可以直接返回。
//3.合并（Combine）：<merge>
//将两个有序的子数组合并成一个有序的数组。


//归并排序使用递归更好实现和理解
//归并排序的时间复杂度为O(nlogn)，n 是待排序元素的数量。
//具体分析如下
//1.分解阶段：每次将数组分成两半，分解的层数为logn。
//2.合并阶段：每一层的合并操作需要 O(n) 的时间。
//因此，总的时间复杂度为O(nlogn)。
public class Code01_mergeSortRecursion {


    //这里的递归形式是从 大到小
    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr,0,arr.length-1);
    }


    private static void process(int[] arr,int L,int R) {
        if (L==R) {
            return;
        }
        //变有序
        int mid = (L+R)/2;
        process(arr,L,mid-1);
        process(arr,mid+1,R);

        //数组变有序了之后，再直接进行下一步进行merger
        merger(arr,L,mid,R);
    }

    private static void merger(int[] arr, int l, int mid, int r) {
        int [] help = new int[r-l+1];
        int i = 0;
        int left =0;
        int right = mid+1;

        //如果数组的左右两边都有值
        while (left <= l && right <= mid) {
            help[i++]= help[left]<help[right]?arr[left++]:arr[right++];
        }

        //下述两个while只会发生一个
        //如果只剩下数组的左边
        while (left <= l) {
            help[i++]= arr[left++];
        }
        //如果只剩下数组的右边
        while (right <= r) {
            help[i++]= arr[right++];
        }

        //将help数组移动进去
        for (i = 0; i < help.length; i++) {
            arr[l+i] = help[i];
        }

    }


}
