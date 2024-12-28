package code12;

// 测试链接 : https://leetcode.com/problems/regular-expression-matching/
// https://leetcode.cn/problems/regular-expression-matching/
/// 1. 题目背景
/// 给定一个字符串 s 和一个字符规律 p，需要实现一个支持特殊字符 .（匹配任意单个字符）和 *（匹配零个或多个前面的那一个元素）的正则表达式匹配功能，
/// 并且要求匹配是针对整个字符串 s，而不是部分字符串。
///
/// 2. 示例解释
/// 示例 1：
/// 输入 s = "aa"，p = "a"，输出为 false，因为 "a" 无法匹配 "aa" 整个字符串。
/// 示例 2：
/// 输入 s = "aa"，p = "a*"，输出为 true，由于 * 的作用，"a*" 可以匹配 "aa"，这里 * 表示 a 可以重复零次或多次，在此例中重复了一次。
/// 示例 3：
/// 输入 s = "ab"，p = ".*"，输出为 true，因为 ".*" 表示可匹配零个或多个任意字符，"ab" 符合该匹配规则。
///
/// 注意：a* 可以表示 0 个 字符
///
/// 3. 输入限制
/// 字符串 s 的长度范围是 1 <= s.length <= 20，且只包含 a - z 的小写字母。
/// 字符规律 p 的长度范围是 1 <= p.length <= 20，只包含 a - z 的小写字母以及特殊字符 . 和 *，并且保证每次出现 * 时，前面都匹配到有效的字符。
public class Code04_RegularExpressionMatch {

	// 暴力递归
	public static boolean isMatch1(String str, String pat) {
		char[] s = str.toCharArray();
		char[] p = pat.toCharArray();
		return process1(s, p, 0, 0);
	}

	// s[i....]能不能被p[j....]完全匹配出来
	public static boolean process1(char[] s, char[] p, int i, int j) {
		if (i == s.length) {
			// s没了
			if (j == p.length) {
				// 如果p也没了，返回true
				return true;
			} else {
				// 如果p[j+1]是*，那么p[j..j+1]可以消掉，然后看看p[j+2....]是不是都能消掉
				return j + 1 < p.length && p[j + 1] == '*' && process1(s, p, i, j + 2);
			}
		} else if (j == p.length) {
			// s有
			// p没了
			return false;
		} else {
			if (j + 1 == p.length || p[j + 1] != '*') {
				// s[i....]
				// p[j....]
				// 如果p[j+1]不是*，那么当前的字符必须能匹配：(s[i] == p[j] || p[j] == '?')
				// 同时，后续也必须匹配上：process1(s, p, i + 1, j + 1);
				return (s[i] == p[j] || p[j] == '.') && process1(s, p, i + 1, j + 1);
			} else {
				// s[i....]
				// p[j....]
				// 如果p[j+1]是*
				// 选择1: 当前p[j..j+1]是x*，就是不让它搞定s[i]，那么继续 : process1(s, p, i, j + 2)
				boolean p1 = process1(s, p, i, j + 2);
				// 选择2: 当前p[j..j+1]是x*，如果可以搞定s[i]，那么继续 : process1(s, p, i + 1, j)
				// 如果可以搞定s[i] : (s[i] == p[j] || p[j] == '.')
				// 继续匹配 : process1(s, p, i + 1, j)
				boolean p2 = (s[i] == p[j] || p[j] == '.') && process1(s, p, i + 1, j);
				// 两个选择，有一个可以搞定就返回true，都无法搞定返回false
				return p1 || p2;
			}
		}
	}

	// 记忆化搜索
	public static boolean isMatch2(String str, String pat) {
		char[] s = str.toCharArray();
		char[] p = pat.toCharArray();
		int n = s.length;
		int m = p.length;
		// dp[i][j] == 0，表示没算过
		// dp[i][j] == 1，表示没算过答案是true
		// dp[i][j] == 2，表示没算过答案是false
		int[][] dp = new int[n + 1][m + 1];
		return process2(s, p, 0, 0, dp);
	}

	public static boolean process2(char[] s, char[] p, int i, int j, int[][] dp) {
		if (dp[i][j] != 0) {
			return dp[i][j] == 1;
		}
		boolean ans;
		if (i == s.length) {
			if (j == p.length) {
				ans = true;
			} else {
				ans = j + 1 < p.length && p[j + 1] == '*' && process2(s, p, i, j + 2, dp);
			}
		} else if (j == p.length) {
			ans = false;
		} else {
			if (j + 1 == p.length || p[j + 1] != '*') {
				ans = (s[i] == p[j] || p[j] == '.') && process2(s, p, i + 1, j + 1, dp);
			} else {
				ans = process2(s, p, i, j + 2, dp) || ((s[i] == p[j] || p[j] == '.') && process2(s, p, i + 1, j, dp));
			}
		}
		dp[i][j] = ans ? 1 : 2;
		return ans;
	}

	// 动态规划
	public static boolean isMatch3(String str, String pat) {
		char[] s = str.toCharArray();
		char[] p = pat.toCharArray();
		int n = s.length;
		int m = p.length;
		boolean[][] dp = new boolean[n + 1][m + 1];
		dp[n][m] = true;
		for (int j = m - 2; j >= 0; j--) {
			dp[n][j] = p[j + 1] == '*' && dp[n][j + 2];
		}
		for (int i = n - 1; i >= 0; i--) {
			for (int j = m - 1; j >= 0; j--) {
				if (j + 1 == p.length || p[j + 1] != '*') {
					dp[i][j] = (s[i] == p[j] || p[j] == '.') && dp[i + 1][j + 1];
				} else {
					dp[i][j] = dp[i][j + 2] || ((s[i] == p[j] || p[j] == '.') && dp[i + 1][j]);
				}
			}
		}
		return dp[0][0];
	}

}
