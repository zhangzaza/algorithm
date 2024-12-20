package code08;

import java.util.Arrays;

/// 给定一个矩阵matrix，值有正，负，0
/// 蛇可以空降最左列的任何一个位置，原始增长zhi为0
/// 蛇每一步可以选择右上，右，右下三个方向的任何一个前进
/// 沿途的数字累加起来，作为增长值；但是蛇一旦增长值为负数，就会死去
/// 蛇有一种能力，可以使用一次：把某个格子里的数字变成相反数
/// 蛇可以走到任何格子的时候停止
/// 返回蛇能获得的最大增长值

public class Code04_SnakeGame {

	/// 1.1.暴力递归
	public static int zuo(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return 0;
		}
		int ans = 0;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				Info cur = f(matrix, i, j);
				ans = Math.max(ans, Math.max(cur.no, cur.yes));
			}
		}
		return ans;
	}

	public static class Info {
		public int no;
		public int yes;

		public Info(int n, int y) {
			no = n;
			yes = y;
		}
	}

	// 蛇从某一个最左列，且最优的空降点降落
	// 沿途走到(i,j)必须停！
	// 返回，一次能力也不用，获得的最大成长值
	// 返回，用了一次能力，获得的最大成长值
	// 如果蛇从某一个最左列，且最优的空降点降落，不用能力，怎么都到不了(i,j)，那么no = -1
	// 如果蛇从某一个最左列，且最优的空降点降落，用了一次能力，怎么都到不了(i,j)，那么yes = -1
	public static Info f(int[][] matrix, int i, int j) {
		if (j == 0) { // 最左列
			int no = Math.max(matrix[i][0], -1);
			int yes = Math.max(-matrix[i][0], -1);
			return new Info(no, yes);
		}
		// j > 0 不在最左列
		int preNo = -1;
		int preYes = -1;

		//从左边来的
		Info pre = f(matrix, i, j - 1);
		preNo = Math.max(pre.no, preNo);
		preYes = Math.max(pre.yes, preYes);

		//从左上来的
		if (i > 0) {
			pre = f(matrix, i - 1, j - 1);
			preNo = Math.max(pre.no, preNo);
			preYes = Math.max(pre.yes, preYes);
		}

		//从左下来的
		if (i < matrix.length - 1) {
			pre = f(matrix, i + 1, j - 1);
			preNo = Math.max(pre.no, preNo);
			preYes = Math.max(pre.yes, preYes);
		}

		// preNo == -1说明一次能力没用都到不了你 ； Math.max(-1, preNo + matrix[i][j]) 指的是如果到了现在这个点值小于-1，那么就返回-1，如果不是就返回这个值
		int no = preNo == -1 ? -1 : (Math.max(-1, preNo + matrix[i][j]));

		// 能力只有一次，是之前用的！ ；和上面代码一样的逻辑
		int p1 = preYes == -1 ? -1 : (Math.max(-1, preYes + matrix[i][j]));

		// 能力只有一次，就当前用！ ； 在当前位置用这个这个能力
		int p2 = preNo == -1 ? -1 : (Math.max(-1, preNo - matrix[i][j]));

		// 如果上述的三种情况都小于-1，就返回-1，否则就返回这个值
		int yes = Math.max(Math.max(p1, p2), -1);
		return new Info(no, yes);
	}



	/// 1.2.第二种暴力递归解答
	public static int walk1(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return 0;
		}
		int res = Integer.MIN_VALUE;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				int[] ans = process(matrix, i, j);
				res = Math.max(res, Math.max(ans[0], ans[1]));
			}
		}
		return res;
	}

	// 从假想的最优左侧到达(i,j)的旅程中
	// 0) 在没有使用过能力的情况下，返回路径最大和，没有可能到达的话，返回负
	// 1) 在使用过能力的情况下，返回路径最大和，没有可能到达的话，返回负
	public static int[] process(int[][] m, int i, int j) {
		if (j == 0) { // (i,j)就是最左侧的位置
			return new int[] { m[i][j], -m[i][j] };
		}
		int[] preAns = process(m, i, j - 1);
		// 所有的路中，完全不使用能力的情况下，能够到达的最好长度是多大
		int preUnuse = preAns[0];
		// 所有的路中，使用过一次能力的情况下，能够到达的最好长度是多大
		int preUse = preAns[1];
		if (i - 1 >= 0) {
			preAns = process(m, i - 1, j - 1);
			preUnuse = Math.max(preUnuse, preAns[0]);
			preUse = Math.max(preUse, preAns[1]);
		}
		if (i + 1 < m.length) {
			preAns = process(m, i + 1, j - 1);
			preUnuse = Math.max(preUnuse, preAns[0]);
			preUse = Math.max(preUse, preAns[1]);
		}
		// preUnuse 之前旅程，没用过能力
		// preUse 之前旅程，已经使用过能力了
		int no = -1; // 之前没使用过能力，当前位置也不使用能力，的最优解
		int yes = -1; // 不管是之前使用能力，还是当前使用了能力，请保证能力只使用一次，最优解
		if (preUnuse >= 0) {
			no = m[i][j] + preUnuse;
			yes = -m[i][j] + preUnuse;
		}
		if (preUse >= 0) {
			yes = Math.max(yes, m[i][j] + preUse);
		}
		return new int[] { no, yes };
	}




	/// 2.记忆话搜索
	public static int walk2(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return 0;
		}
		int max = Integer.MIN_VALUE;
		int[][][] dp = new int[matrix.length][matrix[0].length][2];
		for (int i = 0; i < dp.length; i++) {
			dp[i][0][0] = matrix[i][0];
			dp[i][0][1] = -matrix[i][0];
			max = Math.max(max, Math.max(dp[i][0][0], dp[i][0][1]));
		}
		for (int j = 1; j < matrix[0].length; j++) {
			for (int i = 0; i < matrix.length; i++) {
				int preUnuse = dp[i][j - 1][0];
				int preUse = dp[i][j - 1][1];
				if (i - 1 >= 0) {
					preUnuse = Math.max(preUnuse, dp[i - 1][j - 1][0]);
					preUse = Math.max(preUse, dp[i - 1][j - 1][1]);
				}
				if (i + 1 < matrix.length) {
					preUnuse = Math.max(preUnuse, dp[i + 1][j - 1][0]);
					preUse = Math.max(preUse, dp[i + 1][j - 1][1]);
				}
				dp[i][j][0] = -1;
				dp[i][j][1] = -1;
				if (preUnuse >= 0) {
					dp[i][j][0] = matrix[i][j] + preUnuse;
					dp[i][j][1] = -matrix[i][j] + preUnuse;
				}
				if (preUse >= 0) {
					dp[i][j][1] = Math.max(dp[i][j][1], matrix[i][j] + preUse);
				}
				max = Math.max(max, Math.max(dp[i][j][0], dp[i][j][1]));
			}
		}
		return max;
	}

	public static int[][] generateRandomArray(int row, int col, int value) {
		int[][] arr = new int[row][col];
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				arr[i][j] = (int) (Math.random() * value) * (Math.random() > 0.5 ? -1 : 1);
			}
		}
		return arr;
	}

	public static void main(String[] args) {
		int N = 7;
		int M = 7;
		int V = 10;
		int times = 1000000;
		for (int i = 0; i < times; i++) {
			int r = (int) (Math.random() * (N + 1));
			int c = (int) (Math.random() * (M + 1));
			int[][] matrix = generateRandomArray(r, c, V);
			int ans1 = zuo(matrix);
			int ans2 = walk2(matrix);
			if (ans1 != ans2) {
				for (int j = 0; j < matrix.length; j++) {
					System.out.println(Arrays.toString(matrix[j]));
				}
				System.out.println("Oops   ans1: " + ans1 + "   ans2:" + ans2);
				break;
			}
		}
		System.out.println("finish");
	}

}
