package code09;

import java.util.HashMap;


/// 定义何为step sum？
/// 比如 680，680+68+6=754，680的step sum 叫做 754
/// 给定一个正数num，判断它是不是某个数的step sum
///
/// 思路：公式法，二分

public class Code05_IsStepSum {


	/// 1.使用二分法
	/// 在 0～stepSum 这个范围内进行求证，时间复杂度O( (log 2 stepSum) * (log 10 stepSum) )
	public static boolean isStepSum(int stepSum) {
		int L = 0;
		int R = stepSum;
		int M = 0;
		int cur = 0;
		while (L <= R) {
			M = L + ((R - L) >> 1);
			cur = stepSum(M);
			if (cur == stepSum) {
				return true;
			} else if (cur < stepSum) {
				L = M + 1;
			} else {
				R = M - 1;
			}
		}
		return false;
	}

	public static int stepSum(int num) {
		int sum = 0;
		while (num != 0) {
			sum += num;
			num /= 10;
		}
		return sum;
	}

	// for test
	public static HashMap<Integer, Integer> generateStepSumNumberMap(int numMax) {
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i <= numMax; i++) {
			map.put(stepSum(i), i);
		}
		return map;
	}

	// for test
	public static void main(String[] args) {
		int max = 1000000;
		int maxStepSum = stepSum(max);
		HashMap<Integer, Integer> ans = generateStepSumNumberMap(max);
		System.out.println("测试开始");
		for (int i = 0; i <= maxStepSum; i++) {
			if (isStepSum(i) ^ ans.containsKey(i)) {
				System.out.println("出错了！");
			}
		}
		System.out.println("测试结束");
	}

}
