package code02;

// 本题测试链接 : https://leetcode.com/problems/shortest-unsorted-continuous-subarray/
/// 给定一个整数数组nums，你需要找到一个连续的子数组，只要对这个子数组进行升序排序，整个数组就会变得升序有序。返回这个子数组的最短长度。
/// 时间复杂度O(N),空间复杂度O(1)
public class Code06_MinLengthForSort {

	public static int findUnsortedSubarray(int[] nums) {
		if (nums == null || nums.length < 2) {
			return 0;
		}
		int N = nums.length;
		int right = -1;
		int max = Integer.MIN_VALUE;

		// 从左往右滑一遍
		for (int i = 0; i < N; i++) {
			if (max > nums[i]) {
				right = i;
			}
			max = Math.max(max, nums[i]);
		}
		int min = Integer.MAX_VALUE;
		int left = N;

		// 从右往左滑一遍
		for (int i = N - 1; i >= 0; i--) {
			if (min < nums[i]) {
				left = i;
			}
			min = Math.min(min, nums[i]);
		}
		return Math.max(0, right - left + 1);
	}

}
