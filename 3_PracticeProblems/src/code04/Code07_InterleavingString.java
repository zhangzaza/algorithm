package code04;

// 本题测试链接 : https://leetcode.com/problems/interleaving-string/
// https://leetcode.cn/problems/interleaving-string/description/
///给定三个字符串 s1、s2、s3，判断 s3 是否是由 s1 和 s2 交错组成的。两个字符串 s 和 t 交错的定义为：
/// s = s₁ + s₂ +... + sₙ，t = t₁ + t₂ +... + tₘ，且 |n - m| <= 1。
/// 交错形式可以是 s₁ + t₁ + s₂ + t₂ + s₃ + t₃ +... 或者 t₁ + s₁ + t₂ + s₂ + t₃ + s₃ +...（其中 a + b 表示字符串 a 和 b 的连接）。
public class Code07_InterleavingString {

	public static boolean isInterleave(String s1, String s2, String s3) {
		if (s1 == null || s2 == null || s3 == null) {
			return false;
		}
		char[] str1 = s1.toCharArray();
		char[] str2 = s2.toCharArray();
		char[] str3 = s3.toCharArray();
		if (str3.length != str1.length + str2.length) {
			return false;
		}
		boolean[][] dp = new boolean[str1.length + 1][str2.length + 1];
		dp[0][0] = true;
		for (int i = 1; i <= str1.length; i++) {
			if (str1[i - 1] != str3[i - 1]) {
				break;
			}
			dp[i][0] = true;
		}
		for (int j = 1; j <= str2.length; j++) {
			if (str2[j - 1] != str3[j - 1]) {
				break;
			}
			dp[0][j] = true;
		}
		for (int i = 1; i <= str1.length; i++) {
			for (int j = 1; j <= str2.length; j++) {
				if (
						(str1[i - 1] == str3[i + j - 1] && dp[i - 1][j]) // 可能性1
						||
						(str2[j - 1] == str3[i + j - 1] && dp[i][j - 1]) // 可能性2
						) {
					dp[i][j] = true;
				}
			}
		}
		return dp[str1.length][str2.length];
	}

}
