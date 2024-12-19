package code08;

// 本题测试链接 : https://leetcode.com/problems/container-with-most-water/
// https://leetcode.cn/problems/container-with-most-water/
/// 题目描述
/// 给定一个长度为 n 的整数数组 height，数组中的每个元素 height[i] 表示第 i 条垂线的高度，垂线的两个端点是 (i, 0) 和 (i, height[i])。
/// 要求找出两条垂线，使得它们与 x 轴构成的容器能容纳最多的水，返回容器可以储存的最大水量，且不能倾斜容器。
/// 示例
/// 示例 1：
/// 输入：[1,8,6,2,5,4,8,3,7]
/// 输出：49
/// 解释：通过图形展示了输入数组对应的垂线，以及能容纳水的最大值（蓝色部分）为 49 的情况。
/// 示例 2：
/// 输入：height = [1,1]
/// 输出：1
public class Code02_ContainerWithMostWater {

	/// 1.暴力解
	public static int maxArea1(int[] h) {
		int max = 0;
		int N = h.length;
		for (int i = 0; i < N; i++) { // h[i]
			for (int j = i + 1; j < N; j++) { // h[j]
				max = Math.max(max, Math.min(h[i], h[j]) * (j - i));
			}
		}
		return max;
	}

	/// 2.双指针解
	public static int maxArea2(int[] h) {
		int max = 0;
		int l = 0;
		int r = h.length - 1;
		while (l < r) {
			max = Math.max(max, Math.min(h[l], h[r]) * (r - l));
			if (h[l] > h[r]) {
				r--;
			} else {
				l++;
			}
		}
		return max;
	}

}
