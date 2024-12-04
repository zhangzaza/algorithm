package code09_PrefixTree_SortStability;

/**
 * 不基于比较的排序 一定要求数据要求特殊
 */
//计数排序
//计数排序的基本步骤
//1.确定范围：找到待排序数组中的最大值和最小值，以确定计数数组的大小。
//2.计数：遍历待排序数组，统计每个元素出现的次数，并存储在计数数组中。
//3.累加次数：修改计数数组，使其中每一元素等于之前所有元素之和。这一步的目的是确定每个元素在排序后数组中的正确位置。
//4.构建输出数组：从后向前遍历原数组，根据计数数组中的信息将每个元素放在输出数组的正确位置，并减少计数数组中的相应值。
//5.复制输出数组：将排序好的输出数组复制回原数组。

import java.util.Arrays;

/**
 * 时间复杂度和空间复杂度都是O(n)
 * */
public class Code03_countSort {

    //给定一组人的年龄，将这些人按照年龄升序排列。
    public static void ageSort(int[] ages) {
        int maxAge = 120; // 假设最大年龄为120
        int[] count = new int[maxAge + 1];

        // 计数每个年龄出现的次数
        for (int age : ages) {
            count[age]++;
        }

        // 输出排序结果
        int index = 0;
        for (int age = 0; age <= maxAge; age++) {
            while (count[age] > 0) {
                ages[index++] = age;
                count[age]--;
            }
        }
    }

    public static void main(String[] args) {
        int[] ages = {25, 30, 22, 30, 21, 25};
        ageSort(ages);
        System.out.println(Arrays.toString(ages)); // 输出: [21, 22, 25, 25, 30, 30]
    }


}
