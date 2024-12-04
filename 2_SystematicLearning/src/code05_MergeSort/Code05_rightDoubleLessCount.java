package code05_MergeSort;

//对于数组中的每个元素 arr[i]，我们需要找到其右侧所有元素 arr[j] (j > i) 中，满足 arr[j] * 2 < arr[i] 的元素，并返回这样的总次数。
public class Code05_rightDoubleLessCount {



    public static int rightDoubleLessCount(int[] nums) {
        if (nums == null || nums.length == 0 || nums.length == 1) {
            return 0;
        }
        return process(nums, 0, nums.length - 1);
    }


    public static int process(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        int mid = l + ((r - l) >> 2);
        return process(arr, l, mid)
                +
                process(arr, mid + 1, r)
                +
                merge(arr, l, mid, r);
    }


    public static int merge(int[] arr, int L, int mid, int r) {
        int res = 0;

        //比较的时候没有回退，所以这里的时间复杂度是O(N)
        int windowR=mid+1;
        for (int i = L; i <= mid; i++) {
            // 统计右侧有多少数的两倍小于当前元素
            while (windowR <= r && arr[i] > (arr[windowR]<<1)) {
                windowR++;
            }
            res +=windowR-(mid+1);
        }




        int[] help = new int[r - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= r) {
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
