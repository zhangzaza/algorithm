package code15;

//leetcode 122
/// 题目描述
/// 给定一个数组 prices，其中 prices[i] 表示某股票第 i 天的价格。设计一个算法来计算可以获得的最大利润，允许进行多次买卖股票（可以多次买入和卖出同一支股票），但在再次购买前必须出售掉之前的股票。
///
/// 示例
/// 示例 1：
/// 输入：prices = [7,1,5,3,6,4]
/// 输出：7
/// 解释：在第 2 天（股票价格 = 1）时买入，在第 3 天（股票价格 = 5）时卖出，利润为 5 - 1 = 4；在第 4 天（股票价格 = 3）时买入，在第 5 天（股票价格 = 6）时卖出，利润为 6 - 3 = 3。总利润为 4 + 3 = 7。
/// 示例 2：
/// 输入：prices = [1,2,3,4,5]
/// 输出：4
/// 解释：在第 1 天（股票价格 = 1）时买入，在第 5 天（股票价格 = 5）时卖出，总利润为 5 - 1 = 4。注意，不能在第 1 天买入，第 2 天卖出，然后再立即买入，因为这违反了 “再次购买前必须出售掉之前的股票” 的规则。
/// 示例 3：
/// 输入：prices = [7,6,4,3,1]
/// 输出：0
/// 解释：在这种情况下，没有进行任何交易，所以最大利润为 0。
///
/// 限制条件
/// 1 <= prices.length <= 3 * 10^4
/// 0 <= prices[i] <= 10^4
public class Code02_BestTimeToBuyAndSellStockII {

	public static int maxProfit(int[] prices) {
		if (prices == null || prices.length == 0) {
			return 0;
		}
		int ans = 0;
		for (int i = 1; i < prices.length; i++) {
            ans += Math.max(prices[i] - prices[i-1], 0);
		}
		return ans;
	}
	
}
