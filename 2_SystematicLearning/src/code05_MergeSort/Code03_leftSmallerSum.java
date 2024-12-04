package code05_MergeSort;


//数组左侧小于自己的数累加和问题:
//给定一个数组 arr，对于数组中的每一个元素 arr[i]，计算它左侧所有比它小的元素的累加和。
//最终返回一个新的数组，其中的每个元素表示相应位置元素的累加和。
public class Code03_leftSmallerSum {


    public static int leftSmallerSum(int[] nums) {
        if (nums == null || nums.length == 0 || nums.length == 1) {
            return 0;
        }
        return process(nums, 0, nums.length - 1);
    }

    //arr[L....R] 既要排好序，也要求小和返回
    //所有merge 时，产生小和，累加
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

    //递归排序 改进 「去除1️⃣2️⃣3️⃣，下面就是递归的merge」
    //主要就是2️⃣
    //更换思路：✨求最小累计和可以换做，求数组上某个数右边有多少个数比他大就乘以几，这里算出来是直接对应的是这个数求整个数组累计和过程中要被计算几次
    public static int merge(int[] arr, int L, int mid, int r) {
        int[] help = new int[r - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = mid + 1;
        int res = 0;//1️⃣
        while (p1 <= mid && p2 <= r) {
            //拷贝右组的时候不产生小和，拷贝左组的时候产生小和
            res += arr[p1] > arr[p2] ? (r - p2 + 1) * arr[p1] : 0;//2️⃣
            //只有等于或者大于的时候，才移动右组，找到 arr[p1] > arr[p2]
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
        return res;//3️⃣
    }


}
