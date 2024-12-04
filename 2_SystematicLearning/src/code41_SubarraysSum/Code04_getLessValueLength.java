package code41_SubarraysSum;

import java.util.Deque;
import java.util.LinkedList;

/// 给定一个数组arr，给定一个值v，求子数组平均值 <= v的最长子数组长度
public class Code04_getLessValueLength {

    /// 中文注释说明
    /// 前缀和的计算：通过 prefixSum 数组，快速获取任意子数组的和。这一步通过累积当前元素值得到。
    /// 变换条件：为了比较平均值是否小于等于  v
    /// 我们变换条件为：prefixSum[j] - v * j ≤ prefixSum[i] - v * i。这样处理有助于简化问题。
    /// 单调队列的使用：我们利用双端队列来维护一个有序的索引序列，确保队列中的前缀和变换值保持非递减。这种方法允许我们在常数时间内找到当前最优的起始索引 i。
    /// 最大长度计算：在遍历过程中，通过队列头部的索引值来计算可能的最大子数组长度，并更新 maxLength。
    public static int longestSubarray(int[] arr, double v) {
        int n = arr.length;

        // 初始化前缀和数组
        double[] prefixSum = new double[n + 1];
        prefixSum[0] = 0; // 为了更方便地处理索引，初始化为0

        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + arr[i];
        }

        // 双端队列，用来维护前缀和的索引
        Deque<Integer> deque = new LinkedList<>();
        int maxLength = 0;

        for (int j = 0; j <= n; j++) {
            // 计算变换后的当前值
            double currentValue = prefixSum[j] - v * j;

            // 保持队列中的前缀和变换值非递减
            // 如果队列尾部的索引对应的值大于当前值，则弹出队尾
            while (!deque.isEmpty() && (prefixSum[deque.peekLast()] - v * deque.peekLast()) > currentValue) {
                deque.pollLast();
            }

            // 计算满足条件的最长子数组的长度
            // 队列头部的索引是当前最优的起始索引
            if (!deque.isEmpty()) {
                maxLength = Math.max(maxLength, j - deque.peekFirst());
            }

            // 将当前索引加入队列末尾
            deque.offerLast(j);
        }

        return maxLength;
    }


}
