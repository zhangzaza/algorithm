package code16;

import java.util.HashMap;
import java.util.HashSet;

// 这道题是一个小小的补充，课上没有讲
// 但是如果你听过体系学习班动态规划专题和本节课的话
// 这道题就是一道水题

/// 给定一个有正有负有0的数组arr
/// 给定一个整数K
/// 返回arr的子集是否能累加出k
/// 1.正常怎么做？
/// 2.如果arr中的数值很大，但是arr的长度不大，怎么做？
public class Code01_IsSum {

	/// 1.暴力递归方法
	public static boolean isSum1(int[] arr, int sum) {
		if (sum == 0) {
			return true;
		}
		if (arr == null || arr.length == 0) {
			return false;
		}
		return process1(arr, arr.length - 1, sum);
	}

	// 可以自由使用arr[0...i]上的数字，能不能累加得到sum
	/// 0...i ： i要或者不要两种情况，递归
	public static boolean process1(int[] arr, int i, int sum) {
		if (sum == 0) {
			return true;
		}
		if (i == -1) {
			return false;
		}
		return process1(arr, i - 1, sum) || process1(arr, i - 1, sum - arr[i]);
	}

	/// 2.记忆化搜索方法
	// 从暴力递归方法来，加了记忆化缓存，就是动态规划了
	public static boolean isSum2(int[] arr, int sum) {
		if (sum == 0) {
			return true;
		}
		if (arr == null || arr.length == 0) {
			return false;
		}
		return process2(arr, arr.length - 1, sum, new HashMap<>());
	}

	public static boolean process2(int[] arr, int i, int sum, HashMap<Integer, HashMap<Integer, Boolean>> dp) {
		if (dp.containsKey(i) && dp.get(i).containsKey(sum)) {
			return dp.get(i).get(sum);
		}
		boolean ans = false;
		if (sum == 0) {
			ans = true;
		} else if (i != -1) {
			ans = process2(arr, i - 1, sum, dp) || process2(arr, i - 1, sum - arr[i], dp);
		}
		if (!dp.containsKey(i)) {
			dp.put(i, new HashMap<>());
		}
		dp.get(i).put(sum, ans);
		return ans;
	}

	/// 3.经典动态规划
	/// 但是需要知道sum的取值范围
	public static boolean isSum3(int[] arr, int sum) {
		//一个数都不要
		if (sum == 0) {
			return true;
		}

		// sum != 0 并且arr 为 null
		if (arr == null || arr.length == 0) {
			return false;
		}

		// arr有数，sum不为0
		int min = 0;
		int max = 0;
		for (int num : arr) {
			min += num < 0 ? num : 0;
			max += num > 0 ? num : 0;
		}


		// min~max，sum不在这个范围内
		if (sum < min || sum > max) {
			return false;
		}

		// min <= sum <= max
		int N = arr.length;
		// dp[i][j]
		// 
		//  0   1   2   3  4    5   6    7 (实际的对应)
		// -7  -6  -5  -4  -3   -2  -1   0（想象中）
		// 
		// dp[0][-min] -> dp[0][7] -> dp[0][0]
		boolean[][] dp = new boolean[N][max - min + 1];
		// min 这里的min指的是所有的负数累加，如果没有负数就是0 ，所以 -min <= 0
		// dp[0][0] = true
		dp[0][-min] = true;
		// dp[0][arr[0]] = true
		dp[0][arr[0] - min] = true;
		for (int i = 1; i < N; i++) {
			for (int j = min; j <= max; j++) {
				// dp[i][j] = dp[i-1][j]
				dp[i][j - min] = dp[i - 1][j - min];
				// dp[i][j] |= dp[i-1][j - arr[i]]
				int next = (j - arr[i]) - min;
				dp[i][j - min] |= (next >= 0 && next <= max - min && dp[i - 1][next]);
			}
		}
		// dp[N-1][sum]
		return dp[N - 1][sum - min];
	}




	/// 4.分治方法「动态规划」
	// 分治的方法
	// 如果arr中的数值特别大，动态规划方法依然会很慢
	// 此时如果arr的数字个数不算多(40以内)，哪怕其中的数值很大，分治的方法也将是最优解
	public static boolean isSum4(int[] arr, int sum) {
		if (sum == 0) {
			return true;
		}
		if (arr == null || arr.length == 0) {
			return false;
		}
		if (arr.length == 1) {
			return arr[0] == sum;
		}
		int N = arr.length;
		int mid = N >> 1;
		HashSet<Integer> leftSum = new HashSet<>();
		HashSet<Integer> rightSum = new HashSet<>();


		// 0...mid-1
		process4(arr, 0, mid, 0, leftSum);

		// mid..N-1
		process4(arr, mid, N, 0, rightSum);

		// 单独查看，只使用左部分，能不能搞出sum
		// 单独查看，只使用右部分，能不能搞出sum
		// 左+右，联合能不能搞出sum
		// leftSum搞出所有累加和的时候，包含leftSum一个数也没有，就是0；同理rightSum也一样
		for (int l : leftSum) {// 遍历左部分的所有可能和：通过 for 循环遍历 leftSum 集合中的每个元素 l。
			if (rightSum.contains(sum - l)) {
				return true;
			}
		}

		return false;
	}

	// arr[0...i-1]决定已经做完了！形成的累加和是 pre
	// arr[i...end - 1] end(终止)  所有数字随意选择，
	// arr[0...end-1]所有可能的累加和存到ans里去
	// i开始，end结束，pre累加和，所有的累加和存到ans中
	public static void process4(int[] arr, int i, int end, int pre, HashSet<Integer> ans) {
		if (i == end) {
			ans.add(pre);
		} else {
			process4(arr, i + 1, end, pre, ans);
			process4(arr, i + 1, end, pre + arr[i], ans);
		}
	}

	// 为了测试
	// 生成长度为len的随机数组
	// 值在[-max, max]上随机
	public static int[] randomArray(int len, int max) {
		int[] arr = new int[len];
		for (int i = 0; i < len; i++) {
			arr[i] = (int) (Math.random() * ((max << 1) + 1)) - max;
		}
		return arr;
	}

	// 对数器验证所有方法
	public static void main(String[] args) {
		int N = 20;
		int M = 100;
		int testTime = 100000;
		System.out.println("测试开始");
		for (int i = 0; i < testTime; i++) {
			int size = (int) (Math.random() * (N + 1));
			int[] arr = randomArray(size, M);
			int sum = (int) (Math.random() * ((M << 1) + 1)) - M;
			boolean ans1 = isSum1(arr, sum);
			boolean ans2 = isSum2(arr, sum);
			boolean ans3 = isSum3(arr, sum);
			boolean ans4 = isSum4(arr, sum);
			if (ans1 ^ ans2 || ans3 ^ ans4 || ans1 ^ ans3) {
				System.out.println("出错了！");
				System.out.print("arr : ");
				for (int num : arr) {
					System.out.print(num + " ");
				}
				System.out.println();
				System.out.println("sum : " + sum);
				System.out.println("方法一答案 : " + ans1);
				System.out.println("方法二答案 : " + ans2);
				System.out.println("方法三答案 : " + ans3);
				System.out.println("方法四答案 : " + ans4);
				break;
			}
		}
		System.out.println("测试结束");
	}

}
