package code16;

import java.util.Arrays;

/// 题目描述：给定一个已排序的正整数数组 nums 和一个正整数 n ，要通过向 nums 数组添加一些元素，使得可以用 nums 中的元素累加表示出 1 到 n 之间的任意整数，求最少需要添加的元素个数 。
/// 例如，原数组是 [1, 3]，n = 6，那最少添加 1 个元素（比如添加 2），就能凑出 1 到 6 间所有数。
///
/// 示例：
/// 示例 1：输入 nums = [1,3]，n = 6，输出 1。原因在于已有 1 和 3，添加 2 后，就能组合出 1 到 6 之间的任意数。
/// 示例 2：输入 nums = [1,5,10]，n = 20，输出 2 。依靠现有数字，补充合适数字，进而达成目标范围。
/// 示例 3：输入 nums = [1,2,2]，n = 5，输出 0，因为原数组元素已经能表示出 1 到 5 之间的所有整数。
///
/// 提示：
/// 1 <= nums.length <= 1000
/// 1 <= nums[i] <= 10^4
///
/// nums 是升序排列的
/// 1 <= n <= 2^31 - 1
public class Code03_MinPatches {

	/// 1.排序，按照2的方法进行排序，并已经执行了aim
	// arr请保证有序，且正数  1~aim
	public static int minPatches(int[] arr, int aim) {
		int patches = 0; // 缺多少个数字「递增的」
		long range = 0; // 已经完成了1 ~ range的目标

		// 一样的条件，先排队
		Arrays.sort(arr);
		for (int i = 0; i != arr.length; i++) {
			// arr[i]
			// 要求：1 ~ arr[i]-1 范围被搞定！
			while (arr[i] - 1 > range) { // arr[i] 1 ~ arr[i]-1
				range += range + 1; // range + 1 是缺的数字
				patches++;
				//只要超过补丁数，就返回添加了的数量
				if (range >= aim) {
					return patches;
				}
			}
			// 要求被满足了！
			range += arr[i];
			if (range >= aim) {
				return patches;
			}
		}

		//数组用完了，但是还是没有
		while (aim >= range + 1) {
			range += range + 1; //range = range + (range+1) 并不是range =range +1
			patches++;
		}
		return patches;
	}



	//  range是int类型，不是long类型
	// 是一个得瑟的方法，除非题目有明确的规定，不然下述的这个方法没必要去写
	public static int minPatches2(int[] arr, int K) {
		int patches = 0; // 缺多少个数字
		int range = 0; // 已经完成了1 ~ range的目标
		for (int i = 0; i != arr.length; i++) {
			// 1~range
			// 1 ~ arr[i]-1
			while (arr[i] > range + 1) { // arr[i] 1 ~ arr[i]-1

				if (range > Integer.MAX_VALUE - range - 1) {
					return patches + 1;
				}

				range += range + 1; // range + 1 是缺的数字
				patches++;
				if (range >= K) {
					return patches;
				}
			}

			if (range > Integer.MAX_VALUE - arr[i]) {
				return patches;
			}
			range += arr[i];
			if (range >= K) {
				return patches;
			}

		}
		while (K >= range + 1) {
			if (K == range && K == Integer.MAX_VALUE) {
				return patches;
			}
			if (range > Integer.MAX_VALUE - range - 1) {
				return patches + 1;
			}
			range += range + 1;
			patches++;
		}
		return patches;
	}




	public static void main(String[] args) {
		int[] test = { 1, 2, 31, 33 };
		int n = 2147483647;
		System.out.println(minPatches(test, n));

	}

}
