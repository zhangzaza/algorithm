package code13;

// 本题测试链接 : https://leetcode.com/problems/bricks-falling-when-hit/
/// 题目描述
/// 给定一个 m x n 的二进制网格 grid，其中 1 表示砖块，0 表示空的空间。砖块稳定的条件为：
/// 直接连接到网格的顶部；
/// 其四个相邻单元中至少有一个其他砖块是稳定的。
/// 同时给定一个数组 hits，表示一系列要进行的擦除操作。每次擦除 hits[i] = (rowᵢ, colᵢ) 位置的砖块（如果该位置有砖块），一些砖块可能因为此次擦除而不再稳定并掉落（一旦掉落会立即从网格中擦除，即不会落在其他稳定砖块上）。
/// 要求返回一个数组 result，其中 result[i] 是第 i 次擦除操作后掉落的砖块数量。注意，擦除操作可能指向没有砖块的位置，如果是这种情况，则不会有砖块掉落。
/// 示例
/// 示例 1：
/// 输入：grid = [[1,0,0,0],[1,1,1,0]]，hits = [[1,0]]
/// 输出：[2]
/// 解释：初始网格中，擦除 (1,0) 位置的砖块后，该位置及其下方的两块砖块不再稳定并掉落，最终网格变为 [[1,0,0,0],[0,0,0,0]]，所以结果为 [2]。
/// 示例 2：
/// 输入：grid = [[1,0,0,0],[1,1,0,0]]，hits = [[1,1],[1,0]]
/// 输出：[0,0]
/// 解释：第一次擦除 (1,1) 位置的砖块后，剩余砖块仍稳定；第二次擦除 (1,0) 位置的砖块后，剩余砖块也仍稳定，所以两次操作都没有砖块掉落，结果为 [0,0]。
/// 约束条件
/// m == grid.length
/// n == grid[i].length
/// 1 <= m, n <= 200
/// grid[i][j] 是 0 或 1
/// 1 <= hits.length <= 4 * 10^4
/// hits[i].length == 2
/// 0 <= xᵢ <= m - 1
/// 0 <= yᵢ <= n - 1
/// 所有 (xᵢ, yᵢ) 都是唯一的。
public class Code04_BricksFallingWhenHit {



	public static int[] hitBricks(int[][] grid, int[][] hits) {
		// 1.打中炮弹之后 调整为了2之后的 矩阵
		for (int i = 0; i < hits.length; i++) {
			if (grid[hits[i][0]][hits[i][1]] == 1) {
				grid[hits[i][0]][hits[i][1]] = 2;
			}
		}
		// 2.建立并查集合，里面进行 初始化空间和连接
		UnionFind unionFind = new UnionFind(grid);
		int[] ans = new int[hits.length];
		for (int i = hits.length - 1; i >= 0; i--) {
			// 3.倒序遍历打中炮弹的位置，更新天花板的连接砖块数量
			if (grid[hits[i][0]][hits[i][1]] == 2) {
				ans[i] = unionFind.finger(hits[i][0], hits[i][1]);
			}
		}
		return ans;
	}

	/// 并查集「类」
	public static class UnionFind {
		private int N;
		private int M;
		// 有多少块砖，连到了天花板上
		private int cellingAll;
		// 原始矩阵，因为炮弹的影响，1 -> 2
		private int[][] grid;
		// cellingSet[i] = true; i 是头节点，所在的集合是天花板集合
		private boolean[] cellingSet;
		private int[] fatherMap;
		private int[] sizeMap;
		private int[] stack;

		// 创建并查集合的时候调用
		public UnionFind(int[][] matrix) {
			// 1.初始化空间
			initSpace(matrix);
			// 2.初始化连接
			initConnect();
		}

		private void initSpace(int[][] matrix) {
			// 1.初始化原始矩阵
			grid = matrix;
			N = grid.length;
			M = grid[0].length;
			int all = N * M;
			cellingAll = 0;
			cellingSet = new boolean[all];
			fatherMap = new int[all];
			sizeMap = new int[all];
			stack = new int[all];
			for (int row = 0; row < N; row++) {
				for (int col = 0; col < M; col++) {
					if (grid[row][col] == 1) {
						int index = row * M + col;
						fatherMap[index] = index;
						sizeMap[index] = 1;
						if (row == 0) {
							cellingSet[index] = true;
							cellingAll++;
						}
					}
				}
			}
		}

		private void initConnect() {
			for (int row = 0; row < N; row++) {
				for (int col = 0; col < M; col++) {
					union(row, col, row - 1, col);
					union(row, col, row + 1, col);
					union(row, col, row, col - 1);
					union(row, col, row, col + 1);
				}
			}
		}

		// 代表点
		private int find(int row, int col) {
			int stackSize = 0;
			int index = row * M + col;
			while (index != fatherMap[index]) {
				stack[stackSize++] = index;
				index = fatherMap[index];
			}
			while (stackSize != 0) {
				fatherMap[stack[--stackSize]] = index;
			}
			return index;
		}

		private void union(int r1, int c1, int r2, int c2) {
			if (valid(r1, c1) && valid(r2, c2)) { // 检查有效性
				int father1 = find(r1, c1);
				int father2 = find(r2, c2);
				if (father1 != father2) {//不是一个代表点才会进行合并
					// 下述是并查集的常规操作
					int size1 = sizeMap[father1];
					int size2 = sizeMap[father2];
					boolean status1 = cellingSet[father1];
					boolean status2 = cellingSet[father2];
					if (size1 <= size2) {
						fatherMap[father1] = father2;
						sizeMap[father2] = size1 + size2;
						if (status1 ^ status2) {// 一个是天花板集合，一个不是天花板集合
							cellingSet[father2] = true;
							cellingAll += status1 ? size2 : size1;// 一个是天花板集合，一个不是天花板集合
						}
					} else {
						fatherMap[father2] = father1;
						sizeMap[father1] = size1 + size2;
						if (status1 ^ status2) {// 一个是天花板集合，一个不是天花板集合
							cellingSet[father1] = true;
							cellingAll += status1 ? size2 : size1;// 一个是天花板集合，一个不是天花板集合
						}
					}
				}
			}
		}

		private boolean valid(int row, int col) {
			return row >= 0 && row < N && col >= 0 && col < M && grid[row][col] == 1;
		}

		public int cellingNum() {
			return cellingAll;
		}

		public int finger(int row, int col) {
			grid[row][col] = 1;
			int cur = row * M + col;
			if (row == 0) {
				cellingSet[cur] = true;
				cellingAll++;
			}
			fatherMap[cur] = cur;
			sizeMap[cur] = 1;
			int pre = cellingAll;
			union(row, col, row - 1, col);
			union(row, col, row + 1, col);
			union(row, col, row, col - 1);
			union(row, col, row, col + 1);
			int now = cellingAll;

			// 返回打落的砖块数量
			if (row == 0) {//炮弹打在第0行「看图8」
				return now - pre;
			} else {//炮弹没有打在第0行「看图9」
				return now == pre ? 0 : now - pre - 1;
			}
		}
	}

}
