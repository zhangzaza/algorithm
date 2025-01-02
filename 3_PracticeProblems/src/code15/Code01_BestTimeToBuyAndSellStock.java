package code15;

// leetcode 121

/// 1. 题目描述
/// 给定一个数组 prices，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。要求设计一个算法来计算在最多只允许进行一次交易（即买入和卖出一支股票）的情况下，所能获取的最大利润。
///
/// 2. 示例
/// 示例 1：
/// 输入：prices = [7,1,5,3,6,4]
/// 输出：5
/// 解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6 - 1 = 5。注意利润不能是 7 - 1 = 6，因为卖出价格需要大于买入价格；同时，不能在买入前卖出股票。
/// 示例 2：
/// 输入：prices = [7,6,4,3,1]
/// 输出：0
/// 解释：在这种情况下，没有交易完成，所以最大利润为 0。
///
/// 3. 限制条件
/// 1 <= prices.length <= 10^5
/// 0 <= prices[i] <= 10^4
public class Code01_BestTimeToBuyAndSellStock {

	public static int maxProfit(int[] prices) {
		if (prices == null || prices.length == 0) {
			return 0;
		}
		// 必须在0时刻卖掉，[0] - [0]
		int ans = 0;
		// arr[0...0]
		int min = prices[0];
		for (int i = 1; i < prices.length; i++) {
			min = Math.min(min, prices[i]);
			ans = Math.max(ans, prices[i] - min);
		}
		return ans;
	}

}
