package code10;

// 本题测试链接 : https://leetcode.com/problems/jump-game-ii/
// https://leetcode.cn/problems/jump-game-ii/
/// 给定一个长度为 n 的 0 索引整数数组 nums，初始位置在 nums[0]。
/// 数组中每个元素 nums[i] 表示从索引 i 向前跳转的最大长度（即可以跳转到 nums[i+j]，满足 0 <= j <= nums[i] 且 i + j < n），
/// 要求返回到达 nums[n-1] 的最小跳跃次数，题目保证可以到达 nums[n-1]。
///
/// 示例
/// 示例 1：
/// 输入：nums = [2,3,1,1,4]
/// 输出：2
/// 解释：从下标 0 跳到下标 1，跳 1 步，然后从下标 1 跳 3 步到达数组最后一个位置，最小跳跃次数为 2。
/// 示例 2：
/// 输入：nums = [2,3,0,1,4]
/// 输出：2
///
/// 提示
/// 1 <= nums.length <= 10^4
/// 0 <= nums[i] <= 1000
public class Code01_JumpGame {

	public static int jump(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int step = 0;
		int cur = 0;
		int next = 0;
		for (int i = 0; i < arr.length; i++) {
			if (cur < i) {
				step++;
				cur = next;
			}
			next = Math.max(next, i + arr[i]);
		}
		return step;
	}

}
