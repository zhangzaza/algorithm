package code12;

import java.util.HashMap;
import java.util.HashSet;

// 本题测试链接 : https://leetcode.com/problems/longest-consecutive-sequence/
// https://leetcode.cn/problems/longest-consecutive-sequence/
/// 1. 提供了一个未排序的整数数组 nums，要求找出其中数字连续的最长序列的长度，并且需设计时间复杂度为O(n)的算法来解决此问题。
/// 这里所说的数字连续序列不要求在原数组中连续排列。
/// 2. 示例展示
/// 示例 1：
/// 输入：nums = [100,4,200,1,3,2]
/// 输出：4
/// 解释：最长连续序列为 [1,2,3,4]，其长度为 4。
/// 示例 2：
/// 输入：nums = [0,3,7,2,5,8,4,6,0,1]
/// 输出：9
/// 3. 提示信息
/// 数组 nums 的长度范围是 0 <= nums.length <= 10^5。
/// 数组中元素的取值范围为 -10^9 <= nums[i] <= 10^9。
public class Code03_LongestConsecutive {

	// 课上讲的解法
	public static int longestConsecutive(int[] nums) {
		HashMap<Integer, Integer> map = new HashMap<>();
		int len = 0;
		for (int num : nums) {
			if (!map.containsKey(num)) {
				map.put(num, 1);
				int preLen = map.containsKey(num - 1) ? map.get(num - 1) : 0;
				int posLen = map.containsKey(num + 1) ? map.get(num + 1) : 0;
				int all = preLen + posLen + 1;
				map.put(num - preLen, all);
				map.put(num + posLen, all);
				len = Math.max(len, all);
			}
		}
		return len;
	}

	// 补充一个两张表：头表、尾表。非常好理解的方法
	// 不是最优解，但是好理解
	public static int longestConsecutive2(int[] nums) {
		HashMap<Integer, Integer> headMap = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> tailMap = new HashMap<Integer, Integer>();
		HashSet<Integer> visited = new HashSet<>();
		for (int num : nums) {
			if (!visited.contains(num)) {
				visited.add(num);
				headMap.put(num, 1);
				tailMap.put(num, 1);
				if (tailMap.containsKey(num - 1)) {
					int preLen = tailMap.get(num - 1);
					int preHead = num - preLen;
					headMap.put(preHead, preLen + 1);
					tailMap.put(num, preLen + 1);
					headMap.remove(num);
					tailMap.remove(num - 1);
				}
				if (headMap.containsKey(num + 1)) {
					int preLen = tailMap.get(num);
					int preHead = num - preLen + 1;
					int postLen = headMap.get(num + 1);
					int postTail = num + postLen;
					headMap.put(preHead, preLen + postLen);
					tailMap.put(postTail, preLen + postLen);
					headMap.remove(num + 1);
					tailMap.remove(num);
				}
			}
		}
		int ans = 0;
		for (int len : headMap.values()) {
			ans = Math.max(ans, len);
		}
		return ans;
	}

}
