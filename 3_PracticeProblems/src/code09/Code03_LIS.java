package code09;

// 本题测试链接 : https://leetcode.com/problems/longest-increasing-subsequence
// https://leetcode.cn/problems/longest-increasing-subsequence
///给定一个整数数组 nums，需要找到其中最长严格递增子序列的长度。子序列是由原数组派生而来，在不改变其余元素顺序的情况下删除（或不删除）数组中的元素所得到的序列。例如，[3,6,2,7] 是 [0,3,1,6,2,2,7] 的子序列。
/// 示例
/// 示例 1：
/// 输入：nums = [10,9,2,5,3,7,101,18]
/// 输出：4
/// 解释：最长递增子序列是 [2,3,7,101]，其长度为 4。
/// 示例 2：
/// 输入：nums = [0,1,0,3,2,3]
/// 输出：4
/// 示例 3：
/// 输入：nums = [7,7,7,7,7,7,7]
/// 输出：1
/// 提示
/// 1 <= nums.length <= 2500
/// -10^4 <= nums[i] <= 10^4
public class Code03_LIS {

	public static int lengthOfLIS(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		// ends数组
		// ends[i]表示 : 目前所有长度为i+1的递增子序列的最小结尾
		int[] ends = new int[arr.length];
		// 根据含义, 一开始ends[0] = arr[0]
		ends[0] = arr[0];
		// ends有效区范围是0...right，right往右为无效区
		// 所以一开始right = 0, 表示有效区只有0...0范围
		int right = 0;
		// 最长递增子序列的长度
		// 全局变量，抓取每一步的答案，取最大的结果
		int max = 1;
		for (int i = 1; i < arr.length; i++) {
			int l = 0;
			int r = right;
			// 在ends[l...r]范围上二分
			// 如果 当前数(arr[i]) > ends[m]，砍掉左侧
			// 如果 当前数(arr[i]) <= ends[m]，砍掉右侧
			// 整个二分就是在ends里寻找 >= 当前数(arr[i])的最左位置
			// 就是从while里面出来时，l所在的位置。
			// 如果ends中不存在 >= 当前数(arr[i])的情况，将返回有效区的越界位置
			// 也就是从while里面出来时，l所在的位置，是有效区的越界位置
			// 比如 : ends = { 3, 5, 9, 12, 再往右无效}
			// 如果当前数为8, 从while里面出来时，l将来到2位置
			// 比如 : ends = { 3, 5, 9, 12, 再往右无效}
			// 如果当前数为13, 从while里面出来时，l将来到有效区的越界位置，4位置
			while (l <= r) {
				int m = (l + r) / 2;
				if (arr[i] > ends[m]) {
					l = m + 1;
				} else {
					r = m - 1;
				}
			}
			// 从while里面出来，看l的位置
			// 如果l比right大，说明扩充了有效区，那么right变量要随之变大
			// 如果l不比right大，说明l没有来到有效区的越界位置，right不变
			right = Math.max(right, l);
			// l的位置，就是当前数应该填到ends数组里的位置
			ends[l] = arr[i];
			// 更新全局变量
			max = Math.max(max, l + 1);
		}
		return max;
	}

}