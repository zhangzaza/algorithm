package code12;

import java.util.Arrays;

// 本题测试链接 : https://leetcode.com/problems/permutation-in-string/
/// https://leetcode.cn/problems/permutation-in-string/
/// 给定两个字符串 s1 和 s2，需要编写一个函数判断 s2 是否包含 s1 的排列。也就是说，判断 s1 的某种排列是否是 s2 的子串，若包含则返回 true，否则返回 false。
///
/// 示例
/// 示例 1：
/// 输入：s1 = "ab"，s2 = "eidbaooo"
/// 输出：true
/// 解释：s2 包含 s1 的排列之一 "ba"。
/// 示例 2：
/// 输入：s1 = "ab"，s2 = "eidboaoo"
/// 输出：false
///
/// 提示
/// 1 <= s1.length, s2.length <= 10^4
/// s1 和 s2 仅包含小写字母。
public class Code01_ContainAllCharExactly {

	/// 1.方法1：暴力解法，枚举所有子串，判断是否包含s1，时间复杂度O(N^2)
	public static int containExactly1(String s, String a) {
		if (s == null || a == null || s.length() < a.length()) {
			return -1;
		}
		char[] aim = a.toCharArray();
		Arrays.sort(aim);
		String aimSort = String.valueOf(aim);
		for (int L = 0; L < s.length(); L++) {
			for (int R = L; R < s.length(); R++) {
				char[] cur = s.substring(L, R + 1).toCharArray();
				Arrays.sort(cur);
				String curSort = String.valueOf(cur);
				if (curSort.equals(aimSort)) {
					return L;
				}
			}
		}
		return -1;
	}

	/// 2.方法2：滑动窗口，时间复杂度O(N)
	public static int containExactly2(String s, String a) {
		if (s == null || a == null || s.length() < a.length()) {
			return -1;
		}
		char[] str = s.toCharArray();
		char[] aim = a.toCharArray();
		for (int L = 0; L <= str.length - aim.length; L++) {
			if (isCountEqual(str, L, aim)) {
				return L;
			}
		}
		return -1;
	}

	public static boolean isCountEqual(char[] str, int L, char[] aim) {
		int[] count = new int[256];
		for (int i = 0; i < aim.length; i++) {
			count[aim[i]]++;
		}
		for (int i = 0; i < aim.length; i++) {
			if (count[str[L + i]]-- == 0) {
				return false;
			}
		}
		return true;
	}



	/// 3.最优解
	public static int containExactly3(String s1, String s2) {
		if (s1 == null || s2 == null || s1.length() < s2.length()) {
			return -1;
		}
		char[] str2 = s2.toCharArray();
		int M = str2.length;
		int[] count = new int[256];
		for (int i = 0; i < M; i++) {
			count[str2[i]]++;
		}
		int all = M;
		char[] str1 = s1.toCharArray();
		int R = 0;
		// 0~M-1
		for (; R < M; R++) { // 最早的M个字符，让其窗口初步形成
			if (count[str1[R]]-- > 0) {
				all--;
			}
		}
		// 窗口初步形成了，并没有判断有效无效，决定下一个位置一上来判断
		// 接下来的过程，窗口右进一个，左吐一个
		for (; R < str1.length; R++) {
			if (all == 0) { // R-1
				return R - M;
			}
			if (count[str1[R]]-- > 0) {
				all--;
			}
			if (count[str1[R - M]]++ >= 0) {
				all++;
			}
		}
		return all == 0 ? R - M : -1;
	}

	// for test
	public static String getRandomString(int possibilities, int maxSize) {
		char[] ans = new char[(int) (Math.random() * maxSize) + 1];
		for (int i = 0; i < ans.length; i++) {
			ans[i] = (char) ((int) (Math.random() * possibilities) + 'a');
		}
		return String.valueOf(ans);
	}

	public static void main(String[] args) {
		int possibilities = 5;
		int strMaxSize = 20;
		int aimMaxSize = 10;
		int testTimes = 500000;
		System.out.println("test begin, test time : " + testTimes);
		for (int i = 0; i < testTimes; i++) {
			String str = getRandomString(possibilities, strMaxSize);
			String aim = getRandomString(possibilities, aimMaxSize);
			int ans1 = containExactly1(str, aim);
			int ans2 = containExactly2(str, aim);
			int ans3 = containExactly3(str, aim);
			if (ans1 != ans2 || ans2 != ans3) {
				System.out.println("Oops!");
				System.out.println(str);
				System.out.println(aim);
				System.out.println(ans1);
				System.out.println(ans2);
				System.out.println(ans3);
				break;
			}
		}
		System.out.println("test finish");

	}

}
