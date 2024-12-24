package code10;

// 测试链接 : https://leetcode.com/problems/k-inverse-pairs-array/
// https://leetcode.cn/problems/k-inverse-pairs-array/
///定义了逆序对为对于整数数组 nums 中满足 0 <= i < j < nums.length 且 nums[i] > nums[j] 的整数对 [i, j]。
/// 给定两个整数 n 和 k，要求找出所有包含从 1 到 n 的数字，且恰好拥有 k 个逆序对的不同数组的个数，由于答案可能很大，需返回对 10^9 + 7 取余的结果。
/// 示例
/// 示例 1：
/// 输入：n = 3，k = 0
/// 输出：1
/// 解释：只有数组 [1,2,3] 满足包含 1 到 3 的整数且逆序对为 0 个。
/// 示例 2：
/// 输入：n = 3，k = 1
/// 输出：2
/// 解释：数组 [1,3,2] 和 [2,1,3] 都有 1 个逆序对。
/// 提示
/// 1 <= n <= 1000
/// 0 <= k <= 1000
public class Code03_KInversePairs {

	/// 动态规划
	public static int kInversePairs(int n, int k) {
		if (n < 1 || k < 0) {
			return 0;
		}
		int[][] dp = new int[n + 1][k + 1];
		dp[0][0] = 1;
		int mod = 1000000007;
		for (int i = 1; i <= n; i++) {
			dp[i][0] = 1;
			for (int j = 1; j <= k; j++) {
				dp[i][j] = (dp[i][j - 1] + dp[i - 1][j]) % mod;
				if (j >= i) {
					dp[i][j] = (dp[i][j] - dp[i - 1][j - i] + mod) % mod; // + mod 是为了防止负数，因为题目要求对 10^9 + 7 取余，所以这里要加 mod
				}
			}
		}
		return dp[n][k];
	}

	public static int kInversePairs2(int n, int k) {
		if (n < 1 || k < 0) {
			return 0;
		}
		int[][] dp = new int[n + 1][k + 1];
		dp[0][0] = 1;
		for (int i = 1; i <= n; i++) {
			dp[i][0] = 1;
			for (int j = 1; j <= k; j++) {
				dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
				if (j >= i) {
					dp[i][j] -= dp[i - 1][j - i];
				}
			}
		}
		return dp[n][k];
	}

}
