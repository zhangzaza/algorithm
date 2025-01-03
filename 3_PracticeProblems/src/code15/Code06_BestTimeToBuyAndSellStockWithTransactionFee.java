package code15;

//leetcode 714
/// 题目描述
/// 给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 。
/// 设计一个算法计算出最大利润，在满足以下约束条件下可以尽可能地完成更多的交易（多次买卖一支股票）：
/// 卖出股票后，无法在第二天买入股票（即有一天的冷冻期）。
/// 必须在再次购买前出售掉之前的股票。
///
/// 示例
/// 示例 1：
/// 输入：prices = [1,2,3,0,2]
/// 输出：3
/// 解释：对应的交易状态为: [买入.卖出,冷冻期,买入,卖出]
/// 示例 2：
/// 输入：prices = [1]
/// 输出：0
///
/// 限制条件
/// 1 <= prices.length <= 5000
/// 0 <= prices[i] <= 1000
public class Code06_BestTimeToBuyAndSellStockWithTransactionFee {

	//1.输入检查：如果数组为空或长度小于2，直接返回0。
	//2.初始化变量：
	//	bestbuy 表示到目前为止最优的买入状态（即最低的买入成本）。
	//	bestsell 表示到目前为止最优的卖出状态（即最高的卖出收益）。
	//3.遍历数组：从第1天开始遍历每一天的价格：
	//	计算当天买入后的最大收益 curbuy。
	//	计算当天卖出后的最大收益 cursell。
	//	更新 bestbuy 和 bestsell。
	//4.返回结果：最终返回 bestsell，即最大利润。
	public static int maxProfit(int[] arr, int fee) {
		if (arr == null || arr.length < 2) {
			return 0;
		}
		int N = arr.length;
		// 0..0   0 -[0] - fee
		int bestbuy = -arr[0] - fee;
		// 0..0  卖  0
		int bestsell = 0;
		for (int i = 1; i < N; i++) {
			// 来到i位置了！
			// 如果在i必须买  收入 - 批发价 - fee
			int curbuy = bestsell - arr[i] - fee;

			// 如果在i必须卖  整体最优（收入 - 良好批发价 - fee）
			int cursell = bestbuy + arr[i];
			bestbuy = Math.max(bestbuy, curbuy);
			bestsell = Math.max(bestsell, cursell);
		}
		return bestsell;
	}

}
