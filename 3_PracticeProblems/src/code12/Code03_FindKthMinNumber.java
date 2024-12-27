package code12;

// 本题测试链接 : https://leetcode.com/problems/median-of-two-sorted-arrays/
// https://leetcode.cn/problems/median-of-two-sorted-arrays/
/// 1.给定两个正序（从小到大）数组 nums1（大小为 m）和 nums2（大小为 n），需要找出并返回这两个正序数组的中位数。要求算法的时间复杂度为 。
///
/// 2. 示例
/// 示例 1：
/// 输入：nums1 = [1,3]，nums2 = [2]
/// 输出：2.00000
/// 解释：合并数组为 [1,2,3]，中位数是 2。
/// 示例 2：
/// 输入：nums1 = [1,2]，nums2 = [3,4]
/// 输出：2.50000
/// 解释：合并数组为 [1,2,3,4]，中位数是 (2 + 3) / 2 = 2.5。
///
/// 3. 提示
/// nums1.length == m
/// nums2.length == n
/// 0 <= m <= 1000
/// 0 <= n <= 1000
/// 1 <= m + n <= 2000
/// -10^6 <= nums1[i], nums2[i] <= 10^6
public class Code03_FindKthMinNumber {

	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int size = nums1.length + nums2.length;
		boolean even = (size & 1) == 0;
		if (nums1.length != 0 && nums2.length != 0) {
			if (even) {
				return (double) (findKthNum(nums1, nums2, size / 2) + findKthNum(nums1, nums2, size / 2 + 1)) / 2D;
			} else {
				return findKthNum(nums1, nums2, size / 2 + 1);
			}
		} else if (nums1.length != 0) {
			if (even) {
				return (double) (nums1[(size - 1) / 2] + nums1[size / 2]) / 2;
			} else {
				return nums1[size / 2];
			}
		} else if (nums2.length != 0) {
			if (even) {
				return (double) (nums2[(size - 1) / 2] + nums2[size / 2]) / 2;
			} else {
				return nums2[size / 2];
			}
		} else {
			return 0;
		}
	}

	// 进阶问题 : 在两个都有序的数组中，找整体第K小的数
	// 可以做到O(log(Min(M,N)))
	public static int findKthNum(int[] arr1, int[] arr2, int kth) {
		int[] longs = arr1.length >= arr2.length ? arr1 : arr2;
		int[] shorts = arr1.length < arr2.length ? arr1 : arr2;
		int l = longs.length;
		int s = shorts.length;

		if (kth <= s) { // 1)  k < s  第一种情况
			return getUpMedian(shorts, 0, kth - 1, longs, 0, kth - 1);
		}

		if (kth > l) { // 3) k > l 第三种情况
			if (shorts[kth - l - 1] >= longs[l - 1]) {
				return shorts[kth - l - 1];
			}
			if (longs[kth - s - 1] >= shorts[s - 1]) {
				return longs[kth - s - 1];
			}
			return getUpMedian(shorts, kth - l, s - 1, longs, kth - s, l - 1);
		}

		// 2)  s < k <= l 第二种情况
		// 先单独判断 longs[kth - s - 1] >= shorts[s - 1] 的位置，是否满足，满足就返回该答案
		if (longs[kth - s - 1] >= shorts[s - 1]) {
			return longs[kth - s - 1];
		}
		return getUpMedian(shorts, 0, s - 1, longs, kth - s, kth - 1);
	}

	
	
	/// 等长的字符串寻找中点「leetcode上这个原型直接过」
	// A[s1...e1]
	// B[s2...e2]
	// 一定等长！
	// 返回整体的，上中位数！8（4） 10（5） 12（6）
	public static int getUpMedian(int[] A, int s1, int e1, int[] B, int s2, int e2) {
		int mid1 = 0;
		int mid2 = 0;
		while (s1 < e1) {
			// mid1 = s1 + (e1 - s1) >> 1
			mid1 = (s1 + e1) / 2;
			mid2 = (s2 + e2) / 2;

			// 两个中点相等
			if (A[mid1] == B[mid2]) {
				return A[mid1];
			}

			// 两个中点一定不等！
			if (((e1 - s1 + 1) & 1) == 1) { // 奇数长度
				if (A[mid1] > B[mid2]) {
					if (B[mid2] >= A[mid1 - 1]) {
						return B[mid2];
					}
					e1 = mid1 - 1;
					s2 = mid2 + 1;
				} else { // A[mid1] < B[mid2]
					if (A[mid1] >= B[mid2 - 1]) {
						return A[mid1];
					}
					e2 = mid2 - 1;
					s1 = mid1 + 1;
				}
			} else { // 偶数长度
				if (A[mid1] > B[mid2]) {
					e1 = mid1;
					s2 = mid2 + 1;
				} else {
					e2 = mid2;
					s1 = mid1 + 1;
				}
			}
		}
		return Math.min(A[s1], B[s2]);
	}

}
