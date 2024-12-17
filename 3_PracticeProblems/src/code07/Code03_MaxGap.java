package code07;

// 测试链接 : https://leetcode.com/problems/maximum-gap/
/// 给定一个数组arr，返回如果排序之后，相邻两数的最大差值 要求：时间复杂度O(N)
/// 思路：用了桶排序的思想，并使用桶排序排除了不是答案的选择；空间复杂度O(N+1)
public class Code03_MaxGap {

	public static int maximumGap(int[] nums) {
		if (nums == null || nums.length < 2) {
			return 0;
		}
		int len = nums.length;
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;

		// 遍历一遍找到最大值和最小值
		for (int i = 0; i < len; i++) {
			min = Math.min(min, nums[i]);
			max = Math.max(max, nums[i]);
		}

		// 如果最大值和最小值相等，说明数组中只有一个数，直接返回0
		if (min == max) {
			return 0;
		}

		// 不止一种树，min～max一定有range，len个数据，准备 len+1 个桶
		boolean[] hasNum = new boolean[len + 1]; // hasNum[i] i号桶是否进来过数字
		int[] maxs = new int[len + 1]; // maxs[i] i号桶中最大的数
		int[] mins = new int[len + 1]; // mins[i] i号桶中最小的数

		// 遍历一遍数组，确定每个桶的最小值和最大值
		int bid = 0; //桶号
		for (int i = 0; i < len; i++) {
			bid = bucket(nums[i], len, min, max);
			mins[bid] = hasNum[bid] ? Math.min(mins[bid], nums[i]) : nums[i];
			maxs[bid] = hasNum[bid] ? Math.max(maxs[bid], nums[i]) : nums[i];
			hasNum[bid] = true;
		}

		// 遍历一遍，求出相邻两数的最大差值
		int res = 0;
		int lastMax = maxs[0];
		int i = 1;
		for (; i <= len; i++) {
			if (hasNum[i]) {
				res = Math.max(res, mins[i] - lastMax);
				lastMax = maxs[i];
			}
		}
		return res;
	}


	// 进几号桶 公式
	// 当前的数是num，分成了 len+1 个桶，min是最小值，max是最大值
	// 返回num该进第几号桶
	public static int bucket(int num, int len, int min, int max) {
		// 一个桶的范围
		double range = (double) (max - min) / (double) len;
		// num和min之间的距离
		double distance = (double) (num - min);
		// 返回桶的编号，向下取整
		return (int) (distance / range);
	}

}
