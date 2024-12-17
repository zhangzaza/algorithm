package code07;

// 本题测试链接 : https://leetcode.com/problems/binary-tree-cameras/
/// 相机最小覆盖问题
///
public class Code02_MinCameraCover {

	public static class TreeNode {
		public int value;
		public TreeNode left;
		public TreeNode right;
	}


	/// 1.二叉度信息转移「时间复杂度O(N),二叉树的递归序遍历」
	public static int minCameraCover1(TreeNode root) {
		Info data = process1(root);
		return (int) Math.min(data.uncovered + 1, Math.min(data.coveredNoCamera, data.coveredHasCamera));
	}

	// 潜台词：x是头节点，x下方的点都被覆盖的情况下
	public static class Info {
		public long uncovered; // x没有被覆盖，x为头的树至少需要几个相机
		public long coveredNoCamera; // x被相机覆盖，但是x没相机，x为头的树至少需要几个相机
		public long coveredHasCamera; // x被相机覆盖了，并且x上放了相机，x为头的树至少需要几个相机

		public Info(long un, long no, long has) {
			uncovered = un;
			coveredNoCamera = no;
			coveredHasCamera = has;
		}
	}

	// 所有可能性都穷尽了
	public static Info process1(TreeNode X) {
		// 空树就是不能被覆盖，并且不能放置相机
		if (X == null) { // base case
			return new Info(Integer.MAX_VALUE, 0, Integer.MAX_VALUE);
		}

		Info left = process1(X.left);
		Info right = process1(X.right);
		// x uncovered x自己不被覆盖，x下方所有节点，都被覆盖
		// 左孩子： 左孩子没被覆盖，左孩子以下的点都被覆盖
		// 左孩子被覆盖但没相机，左孩子以下的点都被覆盖
		// 右孩子被覆盖也没相机，右孩子以下的点都被覆盖
		long uncovered = left.coveredNoCamera + right.coveredNoCamera;

		// x下方的点都被covered，x也被cover，但x上没相机
		long coveredNoCamera = Math.min(
				// 1)
				left.coveredHasCamera + right.coveredHasCamera,
				Math.min(
						// 2)
						left.coveredHasCamera + right.coveredNoCamera,
						// 3)
						left.coveredNoCamera + right.coveredHasCamera));


		// x下方的点都被covered，x也被cover，且x上有相机
		long coveredHasCamera = 
				Math.min(left.uncovered, Math.min(left.coveredNoCamera, left.coveredHasCamera))//左孩子的任何情况
				+ Math.min(right.uncovered, Math.min(right.coveredNoCamera, right.coveredHasCamera))//右孩子的任何情况
				+ 1;//该节点自己加上的相机

		return new Info(uncovered, coveredNoCamera, coveredHasCamera);
	}



	/// 2.优化解法「时间复杂度没有进行提升，但是时间复杂度同等级进行优化了」
	/// 贪心是啥？「根据业务上的优化，可以进行更好的优化，也可以说是一种自然智慧的优化」
	/// 每道题目都有自己的道，但是没有穷尽的，但是术是有穷尽的，当你脑子不够聪明找不到道的时候，可以使用术进行求解
	public static int minCameraCover2(TreeNode root) {
		Data data = process2(root);
		return data.cameras + (data.status == Status.UNCOVERED ? 1 : 0);
	}

	// 以x为头，x下方的节点都是被covered，x自己的状况，分三种
	public static enum Status {
		UNCOVERED, COVERED_NO_CAMERA, COVERED_HAS_CAMERA //使用枚举，三种可能性被化成了一种
	}

	// 以x为头，x下方的节点都是被covered，得到的最优解中：
	// x是什么状态，在这种状态下，需要至少几个相机
	public static class Data {
		public Status status;
		public int cameras;

		public Data(Status status, int cameras) {
			this.status = status;
			this.cameras = cameras;
		}
	}

	public static Data process2(TreeNode X) {
		//空节点的时候只返回一种情况
		if (X == null) {
			return new Data(Status.COVERED_NO_CAMERA, 0);
		}
		Data left = process2(X.left);
		Data right = process2(X.right);
		int cameras = left.cameras + right.cameras;

		/// 根据自己的子节点状态来判断是否要在该节点放置相机

		// 情况1:左、或右，哪怕有一个没覆盖
		if (left.status == Status.UNCOVERED || right.status == Status.UNCOVERED) {
			return new Data(Status.COVERED_HAS_CAMERA, cameras + 1);
		}

		// 情况2:左右孩子，不存在没被覆盖的情况
		if (left.status == Status.COVERED_HAS_CAMERA || right.status == Status.COVERED_HAS_CAMERA) {
			return new Data(Status.COVERED_NO_CAMERA, cameras);
		}

		// 情况3:左右孩子，不存在没被覆盖的情况，也都没有相机
		return new Data(Status.UNCOVERED, cameras);
	}

}
