package code11;

import java.util.ArrayList;
import java.util.List;

// 本题测试链接 : https://leetcode.com/problems/palindrome-partitioning-ii/
// https://leetcode.cn/problems/palindrome-partitioning-ii/
/// 1.给定一个字符串 s，要求将其分割成一些子串，使得每个子串都是回文串，返回满足条件的最少分割次数。
///
/// 2. 示例
/// 示例 1：
/// 输入：s = "aab"
/// 输出：1
/// 解释：只需一次分割成 ["aa","b"] 这两个回文子串。
/// 示例 2：
/// 输入：s = "a"
/// 输出：0
/// 示例 3：
/// 输入：s = "ab"
/// 输出：1
///
/// 3. 提示
/// 1 <= s.length <= 2000
/// s 仅由小写英文字母组成

public class Code02_PalindromePartitioningII {

	/// 问题一：一个字符串至少要切几刀能让切出来的字串都是回文串
	// 测试链接只测了本题的第一问，直接提交可以通过
	public static int minCut(String s) {
		if (s == null || s.length() < 2) {
			return 0;
		}
		char[] str = s.toCharArray();
		int N = str.length;

		//判断每个范围内的字符是否是回文串
		boolean[][] checkMap = createCheckMap(str, N);
		int[] dp = new int[N + 1];
		dp[N] = 0;
		for (int i = N - 1; i >= 0; i--) {
			if (checkMap[i][N - 1]) {
				dp[i] = 1;
			} else {

				//还要几刀
				int next = Integer.MAX_VALUE;
				for (int j = i; j < N; j++) {
					if (checkMap[i][j]) {
						next = Math.min(next, dp[j + 1]);
					}
				}

				dp[i] = 1 + next;
			}
		}
		return dp[0] - 1;//部分数-1 = 刀数
	}

	public static boolean[][] createCheckMap(char[] str, int N) {
		boolean[][] ans = new boolean[N][N];
		for (int i = 0; i < N - 1; i++) {
			//对角线都是ture
			ans[i][i] = true;
			//倒数第二条对角线，两个字符相等就是true，两个字符不相等就是false
			ans[i][i + 1] = str[i] == str[i + 1];
		}
		ans[N - 1][N - 1] = true;//因为上面的循环这个位置没有进行判断

		for (int i = N - 3; i >= 0; i--) {
			for (int j = i + 2; j < N; j++) {
				ans[i][j] = str[i] == str[j] && ans[i + 1][j - 1];
			}
		}
		return ans;
	}


	/// 问题二：返回问题一的其中一种划分结果「回溯」
	// 本题第二问，返回其中一种结果
	public static List<String> minCutOneWay(String s) {
		List<String> ans = new ArrayList<>();
		if (s == null || s.length() < 2) {
			ans.add(s);
		} else {
			char[] str = s.toCharArray();
			int N = str.length;
			boolean[][] checkMap = createCheckMap(str, N);
			int[] dp = new int[N + 1];
			dp[N] = 0;
			for (int i = N - 1; i >= 0; i--) {
				if (checkMap[i][N - 1]) {
					dp[i] = 1;
				} else {
					int next = Integer.MAX_VALUE;
					for (int j = i; j < N; j++) {
						if (checkMap[i][j]) {
							next = Math.min(next, dp[j + 1]);
						}
					}
					dp[i] = 1 + next;
				}
			}
			// dp[i]  (0....5) 回文！  dp[0] == dp[6] + 1
			//  (0....5)   6
			for (int i = 0, j = 1; j <= N; j++) {
				if (checkMap[i][j - 1] && dp[i] == dp[j] + 1) {
					ans.add(s.substring(i, j));
					i = j;
				}
			}
		}
		return ans;
	}


	/// 问题三：返回问题一的所有划分结果
	// 本题第三问，返回所有结果
	public static List<List<String>> minCutAllWays(String s) {
		List<List<String>> ans = new ArrayList<>();
		if (s == null || s.length() < 2) {
			List<String> cur = new ArrayList<>();
			cur.add(s);
			ans.add(cur);
		} else {
			char[] str = s.toCharArray();
			int N = str.length;
			boolean[][] checkMap = createCheckMap(str, N);
			int[] dp = new int[N + 1];
			dp[N] = 0;
			for (int i = N - 1; i >= 0; i--) {
				if (checkMap[i][N - 1]) {
					dp[i] = 1;
				} else {
					int next = Integer.MAX_VALUE;
					for (int j = i; j < N; j++) {
						if (checkMap[i][j]) {
							next = Math.min(next, dp[j + 1]);
						}
					}
					dp[i] = 1 + next;
				}
			}
			process(s, 0, 1, checkMap, dp, new ArrayList<>(), ans);
		}
		return ans;
	}

	// s[0....i-1]  存到path里去了
	// s[i..j-1] 考察的分出来的第一份
	public static void process(String s, int i, int j, boolean[][] checkMap, int[] dp, 
			List<String> path,
			List<List<String>> ans) {
		if (j == s.length()) { // s[i...N-1]。 已经来到了终止位置
			if (checkMap[i][j - 1] && dp[i] == dp[j] + 1) { // 1.回文，并且 +1 关系
				path.add(s.substring(i, j));//添加一个路径
				ans.add(copyStringList(path));//拷贝放到ans中
				path.remove(path.size() - 1);//还原现场
			}
		} else {// s[i...j-1]。 还有路径
			if (checkMap[i][j - 1] && dp[i] == dp[j] + 1) {// 1.回文，并且 +1 关系
				path.add(s.substring(i, j));
				process(s, j, j + 1, checkMap, dp, path, ans); // 跑后续的process
				path.remove(path.size() - 1);
			}
			process(s, i, j + 1, checkMap, dp, path, ans);// 1.回文，并且 +1 关系 这两个关系检查不差，就单纯的 j++
		}
	}

	public static List<String> copyStringList(List<String> list) {
		List<String> ans = new ArrayList<>();
		for (String str : list) {
			ans.add(str);
		}
		return ans;
	}

	public static void main(String[] args) {
		String s = null;
		List<String> ans2 = null;
		List<List<String>> ans3 = null;

		System.out.println("本题第二问，返回其中一种结果测试开始");
		s = "abacbc";
		ans2 = minCutOneWay(s);
		for (String str : ans2) {
			System.out.print(str + " ");
		}
		System.out.println();

		s = "aabccbac";
		ans2 = minCutOneWay(s);
		for (String str : ans2) {
			System.out.print(str + " ");
		}
		System.out.println();

		s = "aabaa";
		ans2 = minCutOneWay(s);
		for (String str : ans2) {
			System.out.print(str + " ");
		}
		System.out.println();
		System.out.println("本题第二问，返回其中一种结果测试结束");
		System.out.println();
		System.out.println("本题第三问，返回所有可能结果测试开始");
		s = "cbbbcbc";
		ans3 = minCutAllWays(s);
		for (List<String> way : ans3) {
			for (String str : way) {
				System.out.print(str + " ");
			}
			System.out.println();
		}
		System.out.println();

		s = "aaaaaa";
		ans3 = minCutAllWays(s);
		for (List<String> way : ans3) {
			for (String str : way) {
				System.out.print(str + " ");
			}
			System.out.println();
		}
		System.out.println();

		s = "fcfffcffcc";
		ans3 = minCutAllWays(s);
		for (List<String> way : ans3) {
			for (String str : way) {
				System.out.print(str + " ");
			}
			System.out.println();
		}
		System.out.println();
		System.out.println("本题第三问，返回所有可能结果测试结束");
	}

}
