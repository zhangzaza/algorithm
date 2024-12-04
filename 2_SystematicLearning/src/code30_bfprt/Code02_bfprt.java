package code30_bfprt;


import java.util.Arrays;

/// 在无序数组中求第K个小的数字
/// 不用概率解决
public class Code02_bfprt {

    /// 思路：
    /// 1.逻辑上五个数为一组，直到数组结束 ,数组长度为N
    /// 2.对这么多的五个数为一组进行小组内排序 ， 时间复杂度是O(N)
    /// 3.找到 中位数 ，组成新数组 m 长度为 N/5
    /// 4.对 m 进行排序，找到中位数，就是 N/10 的数 ，为 h 「h就是我们的划分数」
    /// 5.用h进行划分 及  <h ... =h .... >h
    /// 没有命中k，再继续进行重复

    /// 主方法：在无序数组中寻找第 K 小的数字
    public static int findKthSmallest(int[] arr, int k) {
        if (arr == null || arr.length < k) {
            throw new IllegalArgumentException("Invalid input");
        }
        return bfprt(arr, 0, arr.length - 1, k - 1);
    }

    // BFPRT 主体实现
    private static int bfprt(int[] arr, int left, int right, int k) {
        if (left == right) {
            return arr[left];
        }
        // L.....R 每五个数一组
        // 每一个小组内部排好序
        // 小组的中位数组成新数组
        // 这个新数组的中位数返回
        int pivot = medianOfMedians(arr, left, right);
        int[] pivotRange = partition(arr, left, right, pivot);
        if (k >= pivotRange[0] && k <= pivotRange[1]) {
            return arr[k];
        } else if (k < pivotRange[0]) {
            return bfprt(arr, left, pivotRange[0] - 1, k);
        } else {
            return bfprt(arr, pivotRange[1] + 1, right, k);
        }
    }

    // 找到中位数数组的中位数
    private static int medianOfMedians(int[] arr, int left, int right) {
        int num = right - left + 1;
        int offset = num % 5 == 0 ? 0 : 1;
        int[] medians = new int[num / 5 + offset];
        for (int i = 0; i < medians.length; i++) {
            int start = left + i * 5;
            // L.....L+4
            // L+5.....L+9
            // L+10.....L+14
            int end = Math.min(start + 4, right);
            medians[i] = getMedian(arr, start, end);
        }
        // arr中找到中位数
        return bfprt(medians, 0, medians.length - 1, medians.length / 2);
    }

    // 在一个小组中找到中位数
    // 使用插入排序，找到常数时间最好的排序即可，因为只有五个数
    private static int getMedian(int[] arr, int left, int right) {
        insertSort(arr, left, right);
        return arr[(left + right) / 2];
    }
    public static void insertSort(int[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            for (int j = i; j > left && arr[j] < arr[j - 1]; j--) {
                swap(arr, j, j - 1);
            }
        }
    }

    // 根据划分数 pivot 对数组进行划分
    private static int[] partition(int[] arr, int left, int right, int pivot) {
        int less = left - 1;
        int more = right + 1;
        int cur = left;
        while (cur < more) {
            if (arr[cur] < pivot) {
                swap(arr, ++less, cur++);
            } else if (arr[cur] > pivot) {
                swap(arr, --more, cur);
            } else {
                cur++;
            }
        }
        return new int[]{less + 1, more - 1};
    }

    // 辅助交换函数
    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


    /// 面试题：
    /// 1.为什么是5？：因为是五个人发明的，所以是5
    /// 2.为什么会使用bfprt？：因为bfprt是一种思想，因为他确定了能淘汰一定量的数字，进行划分


}
