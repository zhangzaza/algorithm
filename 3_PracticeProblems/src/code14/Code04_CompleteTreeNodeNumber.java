package code14;

//本题测试链接 : https://leetcode.cn/problems/count-complete-tree-nodes/
/// 给定一棵树完全二叉树，
/// 返回这棵树的节点个数，
/// 要求时间复杂度小于O(树的节点个数)
///
/// 题目描述
/// 给定一棵完全二叉树的根节点 root，需要求出该树的节点个数。
/// 阐述了完全二叉树的定义：除最底层节点可能未满外，其余每层节点数达最大值，且最底层节点集中在最左边若干位置，最底层（第 h 层，从第 0 层开始）包含 1 到 2^h 个节点。
///
/// 示例
/// 示例 1：
/// 输入：root = [1,2,3,4,5,6]（以树状结构呈现，1 为根节点，2 和 3 为其左右子节点，4、5 为 2 的左右子节点，6 为 3 的左子节点）
/// 输出：6
/// 示例 2：
/// 输入：root = []（空树）
/// 输出：0
/// 示例 3：
/// 输入：root = [1]（只有一个根节点的树）
/// 输出：1
///
/// 提示
/// 树中节点数目范围是 [0, 5 * 10^4]
/// 0 <= Node.val <= 5 * 10^4
/// 题目数据保证输入的树是完全二叉树
public class Code04_CompleteTreeNodeNumber {

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
	}


	/// 主方法
	/// 时间复杂度：O(logN * logN)
	public static int countNodes(TreeNode head) {
		if (head == null) {
			return 0;
		}
		return bs(head, 1, mostLeftLevel(head, 1));
	}

	// 当前来到node节点，node节点在level层，总层数是h
	// 返回node为头的子树(必是完全二叉树)，有多少个节点
	public static int bs(TreeNode node, int Level, int h) {
		if (Level == h) {
			return 1;
		}
		if (mostLeftLevel(node.right, Level + 1) == h) { // 判断右树的最左节点是 是不是 h层，是的话说明左树是满的，不是的话右树是满的
			return (1 << (h - Level)) + bs(node.right, Level + 1, h); // 左树满，求右边的树
		} else {
			return (1 << (h - Level - 1)) + bs(node.left, Level + 1, h);// 右树满，求左边的树
		}
	}

	/// 通过求最左节点的层数，来确定层数
	// 如果node在第level层，
	// 求以node为头的子树，最大深度是多少
	// node为头的子树，一定是完全二叉树
	public static int mostLeftLevel(TreeNode node, int level) {
		while (node != null) {
			level++;
			node = node.left;
		}
		return level - 1;
	}

}
