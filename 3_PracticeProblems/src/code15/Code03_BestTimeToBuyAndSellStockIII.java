package code15;

//leetcode 123
/// 题目描述
/// 给定一个数组 prices，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。设计一个算法来计算所能获取的最大利润，最多可以完成「两笔」 交易（必须在再次购买前出售掉之前的股票）。
/// 需要注意的是，不能同时参与多笔交易（即必须在再次购买前出售掉之前的股票）。
///
/// 示例
/// 示例 1：
/// 输入：prices = [3,3,5,0,0,3,1,4]
/// 输出：6
/// 解释：在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3 - 0 = 3 。随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天（股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4 - 1 = 3 。
/// 示例 2：
/// 输入：prices = [1,2,3,4,5]
/// 输出：4
/// 解释：在第 1 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 5）的时候卖出，这笔交易所能获得利润 = 5 - 1 = 4 。注意不能在第 1 天和第 2 天接连购买股票，然后再将它们卖出。因为这样属于同时参与了多笔交易，是不被允许的。
/// 示例 3：
/// 输入：prices = [7,6,4,3,1]
/// 输出：0
/// 解释：在这种情况下，没有交易完成，所以最大利润为 0。
///
/// 限制条件
/// 1 <= prices.length <= 10^5
/// 0 <= prices[i] <= 10^4
///
/// 解题思路
/// 可以使用动态规划来解决此问题。定义状态 dp[i][j][0/1]，其中 i 表示天数，j 表示交易次数，0/1 表示是否持有股票，通过状态转移方程来计算每个状态下的最大利润。
/// 也可以通过分析股票价格的变化趋势，结合贪心思想来计算最大利润，但相对动态规划可能较难理解和实现。
public class Code03_BestTimeToBuyAndSellStockIII {

	public static int maxProfit(int[] prices) {
		if (prices == null || prices.length < 2) {
			return 0;
		}
		int ans = 0;
		int doneOnceMinusBuyMax = -prices[0];
		int doneOnceMax = 0;
		int min = prices[0];
		for (int i = 1; i < prices.length; i++) {
			min = Math.min(min, prices[i]);
			ans = Math.max(ans, doneOnceMinusBuyMax + prices[i]);
			doneOnceMax = Math.max(doneOnceMax, prices[i] - min);
			doneOnceMinusBuyMax = Math.max(doneOnceMinusBuyMax, doneOnceMax - prices[i]);
		}
		return ans;
	}

}
