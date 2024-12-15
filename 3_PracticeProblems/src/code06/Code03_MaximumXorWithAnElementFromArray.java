package code06;

/// 和code02_MaximumXorOfTwoNumbersInAnArray做题，但是要加入一个限制条件，就是只有小于m的数字是可以考虑异或
public class Code03_MaximumXorWithAnElementFromArray {

	public static class Node {
		public int min;//最小限制
		public Node[] nexts;

		public Node() {
			min = Integer.MAX_VALUE;
			nexts = new Node[2];
		}
	}

	public static class NumTrie {
		public Node head = new Node();

		public void add(int num) {
			Node cur = head;
			head.min = Math.min(head.min, num);
			for (int move = 30; move >= 0; move--) {
				int path = ((num >> move) & 1);
				cur.nexts[path] = cur.nexts[path] == null ? new Node() : cur.nexts[path];
				cur = cur.nexts[path];
				cur.min = Math.min(cur.min, num);
			}
		}

		// 这个结构中，已经收集了一票数字
		// 请返回哪个数字与X异或的结果最大，返回最大结果
		// 但是，只有<=m的数字，可以被考虑
		public int maxXorWithXBehindM(int x, int m) {
			if (head.min > m) {
				return -1;
			}
			// 一定存在某个数可以和x结合
			Node cur = head;
			int ans = 0;
			for (int move = 30; move >= 0; move--) {
				int path = (x >> move) & 1;
				// 期待遇到的东西
				int best = (path ^ 1);
				// 下面的best处理的是我马上遇到的东西
				// (cur.nexts[best] == null || cur.nexts[best].min > m) 表示不能往下走
				best ^= (cur.nexts[best] == null || cur.nexts[best].min > m) ? 1 : 0;

				// best已经变成了实际遇到的
				ans |= (path ^ best) << move;
				cur = cur.nexts[best];
			}
			return ans;
		}
	}


	public static int[] maximizeXor(int[] nums, int[][] queries) {
		int N = nums.length;
		NumTrie trie = new NumTrie();
		for (int i = 0; i < N; i++) {
			trie.add(nums[i]);
		}
		int M = queries.length;
		int[] ans = new int[M];
		for (int i = 0; i < M; i++) {
			ans[i] = trie.maxXorWithXBehindM(queries[i][0], queries[i][1]);
		}
		return ans;
	}

}
