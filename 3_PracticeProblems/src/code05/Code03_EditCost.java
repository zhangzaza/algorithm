package code05;
/// 问题描述
/// 给定两个字符串 str1 和 str2，以及插入、删除、替换一个字符的代价分别为 insertCost、deleteCost、replaceCost，求将 str1 转换为 str2 的最小代价。
/// 示例
/// 假设 str1 = "horse"，str2 = "ros"，插入一个字符的代价 insertCost = 1，删除一个字符的代价 deleteCost = 2，替换一个字符的代价 replaceCost = 3。
public class Code03_EditCost {


	/// 1.使用动态规划
	public static int minCost1(String s1, String s2, int ic, int dc, int rc) {
		if (s1 == null || s2 == null) {
			return 0;
		}
		char[] str1 = s1.toCharArray();
		char[] str2 = s2.toCharArray();
		int N = str1.length + 1;
		int M = str2.length + 1;
		int[][] dp = new int[N][M];
		// dp[0][0] = 0
		for (int i = 1; i < N; i++) {
			dp[i][0] = dc * i;
		}
		for (int j = 1; j < M; j++) {
			dp[0][j] = ic * j;
		}
		//四种情况进行对比拿取最小值
		for (int i = 1; i < N; i++) {
			for (int j = 1; j < M; j++) {
				dp[i][j] = dp[i - 1][j - 1] + (str1[i - 1] == str2[j - 1] ? 0 : rc);//这里包含了两种情况
				dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + ic);
				dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + dc);
			}
		}
		return dp[N - 1][M - 1];
	}


	/// 2.进行了空间压缩，因为只依赖上一行的数据，所以只保存上一行的数据，不需要保存整个二维数组。
	public static int minCost2(String str1, String str2, int ic, int dc, int rc) {
		if (str1 == null || str2 == null) {
			return 0;
		}
		char[] chs1 = str1.toCharArray();
		char[] chs2 = str2.toCharArray();
		char[] longs = chs1.length >= chs2.length ? chs1 : chs2;
		char[] shorts = chs1.length < chs2.length ? chs1 : chs2;
		if (chs1.length < chs2.length) {
			int tmp = ic;
			ic = dc;
			dc = tmp;
		}
		int[] dp = new int[shorts.length + 1];
		for (int i = 1; i <= shorts.length; i++) {
			dp[i] = ic * i;
		}
		for (int i = 1; i <= longs.length; i++) {
			int pre = dp[0];
			dp[0] = dc * i;
			for (int j = 1; j <= shorts.length; j++) {
				int tmp = dp[j];
				if (longs[i - 1] == shorts[j - 1]) {
					dp[j] = pre;
				} else {
					dp[j] = pre + rc;
				}
				dp[j] = Math.min(dp[j], dp[j - 1] + ic);
				dp[j] = Math.min(dp[j], tmp + dc);
				pre = tmp;
			}
		}
		return dp[shorts.length];
	}


	// for test
	public static void main(String[] args) {
		String str1 = "ab12cd3";
		String str2 = "abcdf";
		System.out.println(minCost1(str1, str2, 5, 3, 2));
		System.out.println(minCost2(str1, str2, 5, 3, 2));

		str1 = "abcdf";
		str2 = "ab12cd3";
		System.out.println(minCost1(str1, str2, 3, 2, 4));
		System.out.println(minCost2(str1, str2, 3, 2, 4));

		str1 = "";
		str2 = "ab12cd3";
		System.out.println(minCost1(str1, str2, 1, 7, 5));
		System.out.println(minCost2(str1, str2, 1, 7, 5));

		str1 = "abcdf";
		str2 = "";
		System.out.println(minCost1(str1, str2, 2, 9, 8));
		System.out.println(minCost2(str1, str2, 2, 9, 8));

	}

}
