package code11;

import java.util.ArrayList;
import java.util.List;

// 本题测试链接 : https://leetcode.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/
// https://leetcode.cn/problems/minimum-insertion-steps-to-make-a-string-palindrome/
/// 给定一个字符串 s，每次操作可以在字符串的任意位置插入任意字符，要求返回使 s 成为回文串的最少操作次数。回文串是正读和反读都相同的字符串。
///
/// 示例
/// 示例 1：
/// 输入：s = "zzazz"
/// 输出：0
/// 解释：该字符串本身就是回文串，无需插入字符。
/// 示例 2：
/// 输入：s = "mbadm"
/// 输出：2
/// 解释：可以将字符串变为 "mbdadbm" 或者 "mdbabdm"，最少需要插入 2 个字符。
/// 示例 3：
/// 输入：s = "leetcode"
/// 输出：5
/// 解释：插入 5 个字符后可变为 "leetcodocteel"。
///
/// 提示
/// 1 <= s.length <= 500
/// s 中所有字符都是小写字母。
public class Code01_MinimumInsertionStepsToMakeAStringPalindrome {

	/// 问题一：一个字符串至少需要添加多少个字符能整体变成回文串
	// 测试链接只测了本题的第一问，直接提交可以通过
	public static int minInsertions(String s) {
		if (s == null || s.length() < 2) {
			return 0;
		}
		char[] str = s.toCharArray();
		int N = str.length;
		int[][] dp = new int[N][N];
		for (int i = 0; i < N - 1; i++) {
			dp[i][i + 1] = str[i] == str[i + 1] ? 0 : 1;
		}

		// 从N-3开始填写
		for (int i = N - 3; i >= 0; i--) {
			for (int j = i + 2; j < N; j++) {
				dp[i][j] = Math.min(dp[i][j - 1], dp[i + 1][j]) + 1;
				if (str[i] == str[j]) {
					dp[i][j] = Math.min(dp[i][j], dp[i + 1][j - 1]);
				}
			}
		}
		return dp[0][N - 1];
	}

	///问题二：返回问题一的其中一种添加结果
	/// 思路：1.回溯路径
	// 本题第二问，返回其中一种结果
	public static String minInsertionsOneWay(String s) {
		if (s == null || s.length() < 2) {
			return s;
		}
		char[] str = s.toCharArray();
		int N = str.length;
		int[][] dp = new int[N][N];

		// 倒数第一条对角线是默认值0
		// 填写倒数第二条对角线
		for (int i = 0; i < N - 1; i++) {
			dp[i][i + 1] = str[i] == str[i + 1] ? 0 : 1;
		}
		for (int i = N - 3; i >= 0; i--) {
			for (int j = i + 2; j < N; j++) {
				dp[i][j] = Math.min(dp[i][j - 1], dp[i + 1][j]) + 1;
				if (str[i] == str[j]) {
					dp[i][j] = Math.min(dp[i][j], dp[i + 1][j - 1]);
				}
			}
		}
		
		int L = 0;
		int R = N - 1;
		char[] ans = new char[N + dp[L][R]];
		int ansl = 0;
		int ansr = ans.length - 1;
		while (L < R) {
			if (dp[L][R - 1] == dp[L][R] - 1) {//向左走
				ans[ansl++] = str[R];
				ans[ansr--] = str[R--];
			} else if (dp[L + 1][R] == dp[L][R] - 1) {//向右走
				ans[ansl++] = str[L];
				ans[ansr--] = str[L++];
			} else {//斜着走
				ans[ansl++] = str[L++];
				ans[ansr--] = str[R--];
			}
		}
		if (L == R) {
			ans[ansl] = str[L];
		}
		return String.valueOf(ans);
	}

	/// 问题三：返回问题一的所有可能结果
	// 本题第三问，返回所有可能的结果
	public static List<String> minInsertionsAllWays(String s) {
		List<String> ans = new ArrayList<>();
		if (s == null || s.length() < 2) {
			ans.add(s);
		} else {
			char[] str = s.toCharArray();
			int N = str.length;
			int[][] dp = new int[N][N];

			// 倒数第一条对角线是默认值0
			// 填写倒数第二条对角线
			for (int i = 0; i < N - 1; i++) {
				dp[i][i + 1] = str[i] == str[i + 1] ? 0 : 1;
			}

			// 从N-3开始填写
			for (int i = N - 3; i >= 0; i--) {
				for (int j = i + 2; j < N; j++) {
					dp[i][j] = Math.min(dp[i][j - 1], dp[i + 1][j]) + 1;
					if (str[i] == str[j]) {
						dp[i][j] = Math.min(dp[i][j], dp[i + 1][j - 1]);
					}
				}
			}
			int M = N + dp[0][N - 1];
			char[] path = new char[M];
			process(str, dp, 0, N - 1, path, 0, M - 1, ans);
		}
		return ans;
	}

	// 当前来到的动态规划中的格子，(L,R)
	// path ....  [pl....pr] ....
	// str：原始的字符串
	// dp：动态规划的结果
	// path：当前已经形成的结果
	// ans：所有可能的结果
	public static void process(char[] str, int[][] dp, int L, int R, char[] path, int pl, int pr, List<String> ans) {
		//base case
		if (L >= R) { // L > R  L==R
			if (L == R) {
				path[pl] = str[L];
			}
			ans.add(String.valueOf(path));

		} else {
			//向左走
			if (dp[L][R - 1] == dp[L][R] - 1) {
				path[pl] = str[R];
				path[pr] = str[R];
				process(str, dp, L, R - 1, path, pl + 1, pr - 1, ans);
			}

			//向下走
			if (dp[L + 1][R] == dp[L][R] - 1) {
				path[pl] = str[L];
				path[pr] = str[L];
				process(str, dp, L + 1, R, path, pl + 1, pr - 1, ans);
			}

			//斜着走。也包含了两者已经相邻但是L< R
			if (str[L] == str[R] && (L == R - 1 || dp[L + 1][R - 1] == dp[L][R])) {
				path[pl] = str[L];
				path[pr] = str[R];
				process(str, dp, L + 1, R - 1, path, pl + 1, pr - 1, ans);
			}
		}
	}

	public static void main(String[] args) {
		String s = null;
		String ans2 = null;
		List<String> ans3 = null;

		System.out.println("本题第二问，返回其中一种结果测试开始");
		s = "mbadm";
		ans2 = minInsertionsOneWay(s);
		System.out.println(ans2);

		s = "leetcode";
		ans2 = minInsertionsOneWay(s);
		System.out.println(ans2);

		s = "aabaa";
		ans2 = minInsertionsOneWay(s);
		System.out.println(ans2);
		System.out.println("本题第二问，返回其中一种结果测试结束");

		System.out.println();

		System.out.println("本题第三问，返回所有可能的结果测试开始");
		s = "mbadm";
		ans3 = minInsertionsAllWays(s);
		for (String way : ans3) {
			System.out.println(way);
		}
		System.out.println();

		s = "leetcode";
		ans3 = minInsertionsAllWays(s);
		for (String way : ans3) {
			System.out.println(way);
		}
		System.out.println();

		s = "aabaa";
		ans3 = minInsertionsAllWays(s);
		for (String way : ans3) {
			System.out.println(way);
		}
		System.out.println();
		System.out.println("本题第三问，返回所有可能的结果测试结束");
	}

}
