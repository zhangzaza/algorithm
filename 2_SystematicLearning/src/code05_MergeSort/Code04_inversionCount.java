package code05_MergeSort;


//逆序对问题：如果对于数组中的两个元素 arr[i] 和 arr[j]，满足 i < j 且 arr[i] > arr[j]，则称 (arr[i], arr[j]) 为一个逆序对。
//归并思路：
//遍历左边的数，找出右边有多少数比左边这个位置的数小
public class Code04_inversionCount {



    public static int rightMoreSum(int[] nums) {
        if (nums == null || nums.length == 0 || nums.length == 1) {
            return 0;
        }
        return process(nums, 0, nums.length - 1);
    }


    public static int process(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        //l<r
        int mid = l + ((r - l) >> 2);
        return process(arr, l, mid)
                +
                process(arr, mid + 1, r)
                +
                merge(arr, l, mid, r);
    }


    public static int merge(int[] arr, int L, int mid, int r) {
        int[] help = new int[r - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = mid + 1;
        int res = 0;
        while (p1 <= mid && p2 <= r) {
            //遍历左边的数，找出右边有多少数比左边这个位置的数小
            // 可以转换为
            //遍历右边的数，找出每个数，左边有多少数比他大
            res += arr[p1] > arr[p2] ? (mid - p1 +1)  : 0;
            help[i++] = arr[p2] > arr[p1] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }
        for (int i1 = 0; i1 < help.length; i1++) {
            arr[mid + i1] = help[i1];
        }
        return res;
    }


}
