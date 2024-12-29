package code13;

// 本题测试链接 : https://leetcode.com/problems/scramble-string/
/// 题目描述
/// 定义了一种扰乱字符串的算法：对于长度大于 1 的字符串，先在随机下标处分割成两个非空子字符串，然后随机决定是交换这两个子字符串还是保持顺序不变，接着在两个子字符串上继续递归执行此算法。
/// 给定两个长度相等的字符串 s1 和 s2，判断 s2 是否是 s1 通过上述扰乱算法得到的扰乱字符串，若是则返回 true，否则返回 false。
///
/// 示例
/// 示例 1：
/// 输入：s1 = "great"，s2 = "rgeat"
/// 输出：true
/// 解释：详细展示了 s1 可能通过扰乱算法得到 s2 的一种情形，如分割、随机决定操作（保持或交换子字符串顺序）以及递归执行等过程，最终得到与 s2 相同的结果字符串，所以认为 s2 是 s1 的扰乱字符串。
/// 示例 2：
/// 输入：s1 = "abcde"，s2 = "caebd"
/// 输出：false
/// 示例 3：
/// 输入：s1 = "a"，s2 = "a"
/// 输出：true
///
/// 提示
/// s1.length == s2.length
/// 1 <= s1.length <= 30
public class Code03_ScrambleString {

	/// 1.暴力递归
	public static boolean isScramble0(String s1, String s2) {
		if ((s1 == null && s2 != null) || (s1 != null && s2 == null)) {
			return false;
		}
		if (s1 == null && s2 == null) {
			return true;
		}
		if (s1.equals(s2)) {
			return true;
		}
		char[] str1 = s1.toCharArray();
		char[] str2 = s2.toCharArray();
		if (!sameTypeSameNumber(str1, str2)) {// 先判断是否是同一种字符，并且数量相等
			return false;
		}
		return process0(str1, 0, str1.length - 1, str2, 0, str2.length - 1);
	}

	// str1[L1...R1] str2[L2...R2] 是否互为玄变串
	// 一定保证这两段是等长的！
	public static boolean process0(char[] str1, int L1, int R1, char[] str2, int L2, int R2) {
		if (L1 == R1) {
			return str1[L1] == str2[L2];
		}
		for (int leftEnd = L1; leftEnd < R1; leftEnd++) {//leftEnd 不能等于 R1，等于的话一部分的数量就变成了0
			boolean p1 = process0(str1, L1, leftEnd, str2, L2, L2 + leftEnd - L1)
					&& process0(str1, leftEnd + 1, R1, str2, L2 + leftEnd - L1 + 1, R2);
			boolean p2 = process0(str1, L1, leftEnd, str2, R2 - (leftEnd - L1), R2)
					&& process0(str1, leftEnd + 1, R1, str2, L2, R2 - (leftEnd - L1) - 1);
			if (p1 || p2) {
				return true;
			}
		}
		return false;
	}

	public static boolean sameTypeSameNumber(char[] str1, char[] str2) {
		if (str1.length != str2.length) {
			return false;
		}
		int[] map = new int[256];
		for (int i = 0; i < str1.length; i++) {
			map[str1[i]]++;
		}
		for (int i = 0; i < str2.length; i++) {
			if (--map[str2[i]] < 0) {
				return false;
			}
		}
		return true;
	}


	/// 2.对尝试模型进行下标参数的优化
	public static boolean isScramble1(String s1, String s2) {
		if ((s1 == null && s2 != null) || (s1 != null && s2 == null)) {
			return false;
		}
		if (s1 == null && s2 == null) {
			return true;
		}
		if (s1.equals(s2)) {
			return true;
		}
		char[] str1 = s1.toCharArray();
		char[] str2 = s2.toCharArray();
		if (!sameTypeSameNumber(str1, str2)) {
			return false;
		}
		int N = s1.length();
		return process1(str1, str2, 0, 0, N);// N为数组的长度
	}


	// 返回str1[从L1开始往右长度为size的子串]和str2[从L2开始往右长度为size的子串]是否互为旋变字符串
	// 在str1中的这一段和str2中的这一段一定是等长的，所以只用一个参数size
	public static boolean process1(char[] str1, char[] str2, int L1, int L2, int size) {
		if (size == 1) {
			return str1[L1] == str2[L2];
		}
		// 枚举每一种情况，有一个计算出互为旋变就返回true。都算不出来最后返回false
		for (int leftPart = 1; leftPart < size; leftPart++) {
			if (
			// 如果1左对2左，并且1右对2右
			(process1(str1, str2, L1, L2, leftPart)
					&& process1(str1, str2, L1 + leftPart, L2 + leftPart, size - leftPart)) ||
			// 如果1左对2右，并且1右对2左
					(process1(str1, str2, L1, L2 + size - leftPart, leftPart)
							&& process1(str1, str2, L1 + leftPart, L2, size - leftPart))) {
				return true;
			}
		}
		return false;
	}



	/// 3.傻缓存

	public static boolean isScramble2(String s1, String s2) {
		if ((s1 == null && s2 != null) || (s1 != null && s2 == null)) {
			return false;
		}
		if (s1 == null && s2 == null) {
			return true;
		}
		if (s1.equals(s2)) {
			return true;
		}
		char[] str1 = s1.toCharArray();
		char[] str2 = s2.toCharArray();
		if (!sameTypeSameNumber(str1, str2)) {
			return false;
		}
		int N = s1.length();
		int[][][] dp = new int[N][N][N + 1];
		// dp[i][j][k] = 0 processDP(i,j,k)状态之前没有算过的
		// dp[i][j][k] = -1 processDP(i,j,k)状态之前算过的,返回值是false
		// dp[i][j][k] = 1 processDP(i,j,k)状态之前算过的,返回值是true
		return process2(str1, str2, 0, 0, N, dp);
	}

	public static boolean process2(char[] str1, char[] str2, int L1, int L2, int size, int[][][] dp) {
		if (dp[L1][L2][size] != 0) {
			return dp[L1][L2][size] == 1;
		}
		boolean ans = false;
		if (size == 1) {
			ans = str1[L1] == str2[L2];
		} else {
			for (int leftPart = 1; leftPart < size; leftPart++) {
				if ((process2(str1, str2, L1, L2, leftPart, dp)
						&& process2(str1, str2, L1 + leftPart, L2 + leftPart, size - leftPart, dp))
						|| (process2(str1, str2, L1, L2 + size - leftPart, leftPart, dp)
								&& process2(str1, str2, L1 + leftPart, L2, size - leftPart, dp))) {
					ans = true;
					break;
				}
			}
		}
		dp[L1][L2][size] = ans ? 1 : -1;
		return ans;
	}




	/// 4.动态规划
	public static boolean isScramble3(String s1, String s2) {
		if ((s1 == null && s2 != null) || (s1 != null && s2 == null)) {
			return false;
		}
		if (s1 == null && s2 == null) {
			return true;
		}
		if (s1.equals(s2)) {
			return true;
		}
		char[] str1 = s1.toCharArray();
		char[] str2 = s2.toCharArray();
		if (!sameTypeSameNumber(str1, str2)) {
			return false;
		}
		int N = s1.length();
		boolean[][][] dp = new boolean[N][N][N + 1];
		for (int L1 = 0; L1 < N; L1++) {
			for (int L2 = 0; L2 < N; L2++) {
				dp[L1][L2][1] = str1[L1] == str2[L2];
			}
		}
		// 第一层for循环含义是：依次填size=2层、size=3层..size=N层，每一层都是一个二维平面
		// 第二、三层for循环含义是：在具体的一层，整个面都要填写，所以用两个for循环去填一个二维面
		// L1的取值氛围是[0,N-size]，因为从L1出发往右长度为size的子串，L1是不能从N-size+1出发的，这样往右就不够size个字符了
		// L2的取值范围同理
		// 第4层for循环完全是递归函数怎么写，这里就怎么改的
		for (int size = 2; size <= N; size++) {
			for (int L1 = 0; L1 <= N - size; L1++) {
				for (int L2 = 0; L2 <= N - size; L2++) {
					for (int leftPart = 1; leftPart < size; leftPart++) {
						if ((dp[L1][L2][leftPart] && dp[L1 + leftPart][L2 + leftPart][size - leftPart])
								|| (dp[L1][L2 + size - leftPart][leftPart] && dp[L1 + leftPart][L2][size - leftPart])) {
							dp[L1][L2][size] = true;
							break;
						}
					}
				}
			}
		}
		return dp[0][0][N];
	}

}
