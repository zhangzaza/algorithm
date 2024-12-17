package code07;

import java.util.HashMap;


/// 题目描述
/// 给定一个字符串 str，一个整数 K，以及两个数组 parts 和 record，其中 parts 数组中的每个元素是一个字符串，record 数组中的每个元素是一个整数，表示 parts 中对应字符串的值。目标是将字符串 str 分割成最多 K 个子串，使得这些子串的值之和最大。
/// 输入
/// str：待分割的字符串。
/// K：允许的最大分割次数。
/// parts：可分割的部分数组。
/// record：每个部分对应的值数组。
/// 输出
/// 返回分割后的子串值之和的最大值。如果无法完成分割，则返回 -1。
public class Code06_SplitStringMaxValue {

	/// 1.暴力解
	public static int maxRecord1(String str, int K, String[] parts, int[] record) {
		if (str == null || str.length() == 0) {
			return 0;
		}
		HashMap<String, Integer> records = new HashMap<>();
		for (int i = 0; i < parts.length; i++) {
			records.put(parts[i], record[i]);
		}
		return process(str, 0, K, records);
	}


	//如果当前索引 index 等于字符串长度，检查剩余分割次数是否为 0，如果是则返回 0，否则返回 -1。
	//初始化结果变量 ans 为 -1。
	//遍历从 index 到字符串末尾的所有子串 first：
	//如果 first 在 records 中存在，递归调用 process 处理剩余部分。
	//更新 ans 为当前最大值。
	//返回 ans。
	public static int process(String str, int index, int rest, HashMap<String, Integer> records) {
		if (rest < 0) {
			return -1;
		}
		if (index == str.length()) {
			return rest == 0 ? 0 : -1;
		}
		int ans = -1;
		for (int end = index; end < str.length(); end++) {
			String first = str.substring(index, end + 1);
			int next = records.containsKey(first) ? process(str, end + 1, rest - 1, records) : -1;
			if (next != -1) {
				ans = Math.max(ans, records.get(first) + next);
			}
		}
		return ans;
	}

	/// 2.动态规划解
	public static int maxRecord2(String str, int K, String[] parts, int[] record) {
		if (str == null || str.length() == 0) {
			return 0;
		}
		HashMap<String, Integer> records = new HashMap<>();
		for (int i = 0; i < parts.length; i++) {
			records.put(parts[i], record[i]);
		}
		int N = str.length();
		int[][] dp = new int[N + 1][K + 1];
		for (int rest = 1; rest <= K; rest++) {
			dp[N][rest] = -1;
		}
		for (int index = N - 1; index >= 0; index--) {
			for (int rest = 0; rest <= K; rest++) {
				int ans = -1;
				for (int end = index; end < N; end++) {
					String first = str.substring(index, end + 1);
					int next = rest > 0 && records.containsKey(first) ? dp[end + 1][rest - 1] : -1;
					if (next != -1) {
						ans = Math.max(ans, records.get(first) + next);
					}
				}
				dp[index][rest] = ans;
			}
		}
		return dp[0][K];
	}

	/// 3.动态规划解 + 前缀树优化
	public static int maxRecord3(String s, int K, String[] parts, int[] record) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		TrieNode root = rootNode(parts, record);
		char[] str = s.toCharArray();
		int N = str.length;
		int[][] dp = new int[N + 1][K + 1];
		for (int rest = 1; rest <= K; rest++) {
			dp[N][rest] = -1;
		}
		for (int index = N - 1; index >= 0; index--) {
			for (int rest = 0; rest <= K; rest++) {
				int ans = -1;
				TrieNode cur = root;
				for (int end = index; end < N; end++) {
					int path = str[end] - 'a';
					if (cur.nexts[path] == null) {
						break;
					}
					cur = cur.nexts[path];
					int next = rest > 0 && cur.value != -1 ? dp[end + 1][rest - 1] : -1;
					if (next != -1) {
						ans = Math.max(ans, cur.value + next);
					}
				}
				dp[index][rest] = ans;
			}
		}
		return dp[0][K];
	}

	public static class TrieNode {
		public TrieNode[] nexts;
		public int value;

		public TrieNode() {
			nexts = new TrieNode[26];
			value = -1;
		}
	}

	public static TrieNode rootNode(String[] parts, int[] record) {
		TrieNode root = new TrieNode();
		for (int i = 0; i < parts.length; i++) {
			char[] str = parts[i].toCharArray();
			TrieNode cur = root;
			for (int j = 0; j < str.length; j++) {
				int path = str[j] - 'a';
				if (cur.nexts[path] == null) {
					cur.nexts[path] = new TrieNode();
				}
				cur = cur.nexts[path];
			}
			cur.value = record[i];
		}
		return root;
	}

	public static void main(String[] args) {
		String str = "abcdefg";
		int K = 3;
		String[] parts = { "abc", "def", "g", "ab", "cd", "efg", "defg" };
		int[] record = { 1, 1, 1, 3, 3, 3, 2 };
		System.out.println(maxRecord1(str, K, parts, record));
		System.out.println(maxRecord2(str, K, parts, record));
		System.out.println(maxRecord3(str, K, parts, record));
	}

}
