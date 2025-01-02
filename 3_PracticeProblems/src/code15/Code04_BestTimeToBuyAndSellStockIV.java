package code15;

//leetcode 188

/// 题目描述
/// 给定一个整数数组 prices，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。设计一个算法来计算所能获取的最大利润，最多可以完成 k 笔交易（多次买卖一支股票，必须在再次购买前出售掉之前的股票）。
///
/// 示例
/// 示例 1：
/// 输入：k = 2，prices = [2,4,1]
/// 输出：2
/// 解释：在第 1 天（股票价格 = 2）的时候买入，在第 2 天（股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4 - 2 = 2 。
/// 示例 2：
/// 输入：k = 2，prices = [3,2,6,5,0,3]
/// 输出：7
/// 解释：在第 2 天（股票价格 = 2）的时候买入，在第 3 天（股票价格 = 6）的时候卖出，利润 = 6 - 2 = 4 。随后，在第 5 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，利润 = 3 - 0 = 3 。
///
/// 限制条件
/// 0 <= k <= 100
/// 0 <= prices.length <= 1000
/// 0 <= prices[i] <= 1000
public class Code04_BestTimeToBuyAndSellStockIV {

	public static int maxProfit(int K, int[] prices) {

		if (prices == null || prices.length == 0) {
			return 0;
		}

		//1.次数很多
		int N = prices.length;
		if (K >= N / 2) {
			return allTrans(prices);
		}

		//2.开始dp
		int[][] dp = new int[K + 1][N];
		int ans = 0;
		for (int tran = 1; tran <= K; tran++) {
			int pre = dp[tran][0];
			int best = pre - prices[0];
			for (int index = 1; index < N; index++) {
				pre = dp[tran - 1][index];
				dp[tran][index] = Math.max(dp[tran][index - 1], prices[index] + best);
				best = Math.max(best, pre - prices[index]);
				ans = Math.max(dp[tran][index], ans);
			}
		}
		return ans;
	}

	public static int allTrans(int[] prices) {
		int ans = 0;
		for (int i = 1; i < prices.length; i++) {
			ans += Math.max(prices[i] - prices[i - 1], 0);
		}
		return ans;
	}


	public static int maxProfit2(int K, int[] arr) {
		if (arr == null || arr.length == 0 || K < 1) {
			return 0;
		}
		int N = arr.length;
		if (K >= N / 2) {
			return allTrans(arr);
		}
		int[][] dp = new int[N][K + 1];
		// dp[...][0] = 0。 0次交易
		// dp[0][...] = arr[0.0]  只在0位置进行交易

		for (int j = 1; j <= K; j++) {  //横向是大循环，向下是小循环 看图12

			// dp[1][j] 初始化第一个位置
			int p1 = dp[0][j];
			int best = Math.max(dp[1][j - 1] - arr[1], dp[0][j - 1] - arr[0]); //拿出公共的部分 arr[1] 看图14
			dp[1][j] = Math.max(p1, best + arr[1]); // 加上公共的部分 arr[1]

			// dp[1][j] 准备好一些枚举，接下来准备好的枚举
			for (int i = 2; i < N; i++) {
				p1 = dp[i - 1][j];// 依赖的上一个位置 ，例如 dp[2][3]依赖dp[1][3]
				int newP = dp[i][j - 1] - arr[i]; //我自己这个位置专属的 减
				best = Math.max(newP, best); // 因为每次多一行，就会多一行减，所以要比大小，找出最大值，需要注意的是：这时候已经提取出来了公共的部分best
				dp[i][j] = Math.max(p1, best + arr[i]); //最后加上best并且与p1，比大小
			}
		}
		return dp[N - 1][K];
	}

}
