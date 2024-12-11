package code04;

// 本题测试链接 : https://leetcode.com/problems/maximum-subarray/
/// 返回一个数组中，子数组最大的累加和
public class Code02_SubArrayMaxSum {

	public static int maxSubArray(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int max = Integer.MIN_VALUE;
		int cur = 0;
		for (int i = 0; i < arr.length; i++) {
			cur += arr[i];
			max = Math.max(max, cur);
			cur = cur < 0 ? 0 : cur;
		}
		return max;
	}

	/// 图片中的方法
	public static int maxSubArray2(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		// 上一步，dp的值
		// dp[0]
		int pre = arr[0];
		int max = arr[0];
		for (int i = 1; i < arr.length; i++) {
			pre = Math.max(arr[i], arr[i] + pre);
			max =  Math.max(max, pre);
		}
		return max;
	}

}
