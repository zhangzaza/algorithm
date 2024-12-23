package code09;

import java.util.Arrays;
import java.util.Comparator;

// 本题测试链接 : https://leetcode.com/problems/russian-doll-envelopes/
// https://leetcode.cn/problems/russian-doll-envelopes/
///给定一个二维整数数组 envelopes，其中每个元素 envelopes[i] = [wᵢ, hᵢ] 表示第 i 个信封的宽度和高度。当一个信封的宽度和高度都比另一个信封大时，该信封可以放入另一个信封中，如同俄罗斯套娃。要求计算最多能有多少个信封组成一组 “俄罗斯套娃” 信封（不允许旋转信封）。
/// 2. 示例
/// 示例 1：输入 envelopes = [[5,4],[6,4],[6,7],[2,3]]，输出 3，解释为最多信封个数为 3，组合为 [2,3] => [5,4] => [6,7]。
/// 示例 2：输入 envelopes = [[1,1],[1,1],[1,1]]，输出 1。
/// 3. 提示
/// 1 <= envelopes.length <= 10^5。
/// envelopes[i].length == 2。
/// 1 <= wᵢ, hᵢ <= 10^5。
public class Code04_EnvelopesProblem {

	public static int maxEnvelopes(int[][] matrix) {
		Envelope[] arr = sort(matrix);
		int[] ends = new int[matrix.length];
		ends[0] = arr[0].h;
		int right = 0;
		int l = 0;
		int r = 0;
		int m = 0;
		for (int i = 1; i < arr.length; i++) {
			l = 0;
			r = right;
			while (l <= r) {
				m = (l + r) / 2;
				if (arr[i].h > ends[m]) {
					l = m + 1;
				} else {
					r = m - 1;
				}
			}
			right = Math.max(right, l);
			ends[l] = arr[i].h;
		}
		return right + 1;
	}

	public static class Envelope {
		public int l;
		public int h;

		public Envelope(int weight, int hight) {
			l = weight;
			h = hight;
		}
	}

	public static class EnvelopeComparator implements Comparator<Envelope> {
		@Override
		public int compare(Envelope o1, Envelope o2) {
			return o1.l != o2.l ? o1.l - o2.l : o2.h - o1.h;
		}
	}

	public static Envelope[] sort(int[][] matrix) {
		Envelope[] res = new Envelope[matrix.length];
		for (int i = 0; i < matrix.length; i++) {
			res[i] = new Envelope(matrix[i][0], matrix[i][1]);
		}
		Arrays.sort(res, new EnvelopeComparator());
		return res;
	}

}
