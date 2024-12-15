package code06;

// 本题测试链接 : https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/
///题目描述
/// 给定一个整数数组 nums，要求返回 nums[i] XOR nums[j] 的最大运算结果，其中 0 ≤ i ≤ j < n（n 为数组 nums 的长度）。
/// 示例
/// 示例 1：
/// 输入：nums = [3,10,5,25,2,8]
/// 输出：28
/// 解释：最大运算结果是 5 XOR 25 = 28。
/// 示例 2：
/// 输入：nums = [14,70,53,83,49,91,36,80,92,51,66,70]（此处未给出输出结果，可能是文档未完整显示）
///
/// 提示
/// 1 <= nums.length <= 2 * 10^5。    「可以看出时间复杂度O(N^2)肯定过不了」
/// 0 <= nums[i] <= 2^31 - 1
public class Code02_MaximumXorOfTwoNumbersInAnArray {

	public static class Node {
		public Node[] nexts = new Node[2];
	}


	/// 前缀树结构
	public static class NumTrie {
		public Node head = new Node();

		public void add(int newNum) {
			Node cur = head;
			for (int move = 31; move >= 0; move--) {
				int path = ((newNum >> move) & 1);
				cur.nexts[path] = cur.nexts[path] == null ? new Node() : cur.nexts[path];
				cur = cur.nexts[path];
			}
		}

		public int maxXor(int sum) {
			Node cur = head;
			int res = 0;
			for (int move = 31; move >= 0; move--) {
				int path = (sum >> move) & 1;
				int best = move == 31 ? path : (path ^ 1);
				best = cur.nexts[best] != null ? best : (best ^ 1);
				res |= (path ^ best) << move;
				cur = cur.nexts[best];
			}
			return res;
		}
	}

	public static int findMaximumXOR(int[] arr) {
		if (arr == null || arr.length < 2) {
			return 0;
		}
		int max = Integer.MIN_VALUE;
		NumTrie numTrie = new NumTrie();
		numTrie.add(arr[0]);
		// 这里和第一道题不一样的就是针对的是某一个数字，而不是所有的和
		for (int i = 1; i < arr.length; i++) {
			max = Math.max(max, numTrie.maxXor(arr[i]));
			numTrie.add(arr[i]);
		}
		return max;
	}

}
