package code13;

// 本题测试链接 : https://leetcode.com/problems/super-washing-machines/
// https://leetcode.cn/problems/super-washing-machines/
/// 假设有 n 台超级洗衣机排成一排，每台洗衣机初始时可能有一定数量的衣物或为空。
/// 每一步操作可以选择任意 m（1 <= m <= n）台洗衣机，将每台所选洗衣机中的一件衣服送到相邻的洗衣机。
/// 给定一个整数数组 machines 表示从左至右每台洗衣机中的衣物数量，要求计算出使所有洗衣机中剩余衣物数量相等的最少操作步数，如果无法实现则返回 -1。
///
/// 示例
/// 示例 1：
/// 输入：machines = [1,0,5]
/// 输出：3
/// 解释：通过三步操作可使衣物数量相等，如第一步将最后一台洗衣机的一件衣服送到中间洗衣机，第二步再从中间洗衣机向两边各送一件衣服，第三步继续调整使三台洗衣机衣物数量均为 2。
/// 示例 2：
/// 输入：machines = [0,3,0]
/// 输出：2
/// 解释：经过两步操作可达成目标，先从中间洗衣机向两边各送一件衣服，再进行一次调整。
/// 示例 3：
/// 输入：machines = [0,2,0]
/// 输出：-1
/// 解释：无法让三台洗衣机剩余衣物数量相同。
///
/// 提示
/// n == machines.length
/// 1 <= n <= 10^4
/// 0 <= machines[i] <= 10^5
public class Code02_SuperWashingMachines {

	public static int findMinMoves(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int size = arr.length;
		int sum = 0;
		for (int i = 0; i < size; i++) {
			sum += arr[i];
		}
		if (sum % size != 0) {
			return -1;
		}
		int avg = sum / size;
		int leftSum = 0;
		int ans = 0;
		for (int i = 0; i < arr.length; i++) {
			int leftRest = leftSum - i * avg;
			int rightRest = (sum - leftSum - arr[i]) - (size - i - 1) * avg;
			if (leftRest < 0 && rightRest < 0) {
				ans = Math.max(ans, Math.abs(leftRest) + Math.abs(rightRest));
			} else {
				ans = Math.max(ans, Math.max(Math.abs(leftRest), Math.abs(rightRest)));
			}
			leftSum += arr[i];
		}
		return ans;
	}

}
