package code14;

// 测试链接：https://leetcode.com/problems/first-missing-positive/
// https://leetcode.cn/problems/first-missing-positive/
/// 题目描述
/// 给定一个未排序的整数数组 nums，要求找出其中没有出现的最小正整数，并且需要实现时间复杂度为 O(N) 且仅使用常数级别额外空间的解决方案。
///
/// 示例
/// 示例 1：
/// 输入：nums = [1,2,0]
/// 输出：3
/// 解释：在范围 [1,2] 内的数字都在数组中，所以缺失的最小正整数是 3。
/// 示例 2：
/// 输入：nums = [3,4,-1,1]
/// 输出：2
/// 解释：1 在数组中，但 2 缺失。
/// 示例 3：
/// 输入：nums = [7,8,9,11,12]
/// 输出：1
/// 解释：最小的正数 1 未出现在数组中。
///
/// 提示
/// 1 <= nums.length <= 10^5
/// -2^31 <= nums[i] <= 2^31 - 1
public class Code06_MissingNumber {

	/// 根据每个位置上的数字得是  「？= index + 1」 来区分 L 和 R
	/// L之前为 用过的， R之后的为垃圾区， L～R为有效区
	public static int firstMissingPositive(int[] arr) {
		// l是盯着的位置
		// 0 ~ L-1有效区
		int L = 0;
		int R = arr.length;
		while (L != R) {
			if (arr[L] == L + 1) {//有效区左边界往外扩
				L++;
			} else if (
					arr[L] <= L  // 1.用不到这个数字，可能是负数
							|| arr[L] > R  // 2.大于最好预想 ，大于 index+1 了，用不到
							|| arr[arr[L] - 1] == arr[L] // 3.重复的数字
			) { // 垃圾的情况
				swap(arr, L, --R);
			} else { // 有效的情况
				swap(arr, L, arr[L] - 1);
			}
		}
		return L + 1;
	}

	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

}
