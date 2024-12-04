package code41_SubarraysSum;

/// 给定一个整数组成的无序数组arr，值可能是正，可能是负，可能0
/// 给定一个整数值K
/// 找到arr的所有子数组里，哪个子数组的累加和 <= K，并且是长度最大的，返回其长度
public class Code03_getMaxLength3 {

    public static int maxLengthAwesome(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[] minSums = new int[arr.length];
        int[] minSumEnds = new int[arr.length];
        minSums[arr.length - 1] = arr[arr.length - 1];
        minSumEnds[arr.length - 1] = arr.length - 1;
        for (int i = arr.length - 2; i >= 0; i--) {
            if (minSums[i + 1] < 0) {
                minSums[i] = arr[i] + minSums[i + 1];
                minSumEnds[i] = minSumEnds[i + 1];
            } else {
                minSums[i] = arr[i];
                minSumEnds[i] = i;
            }
        }

        int end = 0;//迟迟扩不进来那一块的开头位置 0~end-1 end 「0～13 end=14」
        int sum = 0;//若干块窗口累加和
        int ans = 0;//数量
        // i是窗口的最左的位置，end扩出来的最右有效块儿的最后一个位置的，再下一个位置
        // end也是一块儿的开始位置
        // 窗口：[i~end)
        for (int i = 0; i < arr.length; i++) {
            // while 循环结束之后：
            // 1）如果以i开头的情况下，累加和 <= k 的最长子数组是 arr[i....end-1]，看看这个子数组长度能不能更新res；
            // 2）如果以i开头的情况下，累加和 <= k 的最长子数组比 arr[i....end]短，更新还是不更新res都不会影响最终结果；
            while (end < arr.length && sum + minSums[end] <= k) {
                sum += minSums[end];
                end = minSumEnds[end] + 1;
            }
            ans = Math.max(ans, end - i);
            if (end >i){// 还有窗口，哪怕窗口没有数字[i~end) [4,4)
                sum -= arr[i];
            }else {// i==end ，即将i++，i>end,此时窗口概念维持不住了，所以end跟着i一起走
                end = i + 1;
            }
        }
        return ans;

    }

}
