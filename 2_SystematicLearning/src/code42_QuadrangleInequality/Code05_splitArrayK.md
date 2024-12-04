// 分割数组的最大值(画匠问题)
// 给定一个非负整数数组 nums 和一个整数 m
// 你需要将这个数组分成 m 个非空的连续子数组。
// 设计一个算法使得这 m 个子数组各自和的最大值最小。


分割数组的最大值问题（也称为“画匠问题”）要求将数组分成 \( m \) 个非空的连续子数组，使得这些子数组的最大和尽可能小。为了实现这一目标，可以使用二分查找结合贪心策略。以下是如何描述这个过程：

### 问题理解

我们给定一个数组 `nums` 和一个整数 `m`，需要找到一种分割方式，使得分割成的每个子数组的和的最大值尽可能小。

### 二分查找的思路

1. **搜索区间**：
    - 最小可能的最大值 \( \text{low} \)：数组中最大的元素。这是因为每个子数组必须至少包含一个元素，最大子数组和不能小于数组中最大值。
    - 最大可能的最大值 \( \text{high} \)：整个数组的和。如果没有分割，整个数组就是一个子数组，其和就是最大和。

2. **定义判定函数**：
    - 对于给定的中间值 \( \text{mid} \)，检查能否将数组分割成 \( m \) 个子数组，使得每个子数组的和不超过 \( \text{mid} \)。
    - 这可以通过贪心算法实现：从头到尾遍历数组，累积当前子数组的和，当超出 \( \text{mid} \) 时，开始一个新子数组，并计数。

3. **二分查找主循环**：
    - 使用二分查找在 \([ \text{low}, \text{high} ]\) 范围内搜索最小的可行最大和。
    - 计算中点 \( \text{mid} \)。
    - 使用判定函数检查是否可以在最大和为 \( \text{mid} \) 的情况下将数组分割成 \( m \) 个或更少的子数组。
    - 如果可以，则收缩右边界 \( \text{high} = \text{mid} - 1 \)，尝试更小的最大和。
    - 如果不可以，则需要更大的最大和，调整左边界 \( \text{low} = \text{mid} + 1 \)。

4. **返回结果**：
    - 在循环结束时，\( \text{low} \) 将是最小的可能最大和的值。

### 实现代码

```java
public class SplitArrayLargestSum {

    public static int splitArray(int[] nums, int m) {
        int max = 0;
        long sum = 0;
        
        // 计算最大值和总和
        for (int num : nums) {
            max = Math.max(max, num);
            sum += num;
        }
        
        // 二分查找
        long low = max, high = sum;
        while (low < high) {
            long mid = low + (high - low) / 2;
            if (canSplit(nums, m, mid)) {
                high = mid; // mid是可行解，尝试更小的
            } else {
                low = mid + 1; // mid不可行，需要更大的最大和
            }
        }
        
        return (int) low;
    }
    
    // 判断能否将数组分割成不超过m个子数组，且每个子数组和不超过maxSum
    private static boolean canSplit(int[] nums, int m, long maxSum) {
        int count = 1;
        long currentSum = 0;
        
        for (int num : nums) {
            currentSum += num;
            if (currentSum > maxSum) {
                count++;
                currentSum = num;
                if (count > m) {
                    return false; // 超过了允许的分割次数
                }
            }
        }
        
        return true;
    }

    public static void main(String[] args) {
        int[] nums = {7, 2, 5, 10, 8};
        int m = 2;
        System.out.println("最小的最大子数组和: " + splitArray(nums, m));
    }
}
```

### 代码说明

- **初始化**：首先计算数组中的最大值 `max` 和数组的总和 `sum`，用于定义二分查找的初始范围。
- **二分查找**：在 `[max, sum]` 范围内使用二分查找来确定最小可能的最大子数组和。
- **判定函数 `canSplit`**：这一函数使用贪心策略检查是否可以将数组分割成不超过 \( m \) 个子数组，使每个子数组和不超过 `maxSum`。
- **返回结果**：最终的 `low` 即为满足条件的最小最大子数组和。

这种方法结合了二分查找和贪心算法，以高效地找到最佳分割方案。