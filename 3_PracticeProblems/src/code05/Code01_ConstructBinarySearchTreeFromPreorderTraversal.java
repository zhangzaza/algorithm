package code05;

import java.util.Stack;

// 本题测试链接 : https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/
/// https://leetcode.cn/problems/construct-binary-search-tree-from-preorder-traversal/
///
/// 给定一个整数数组，它表示二叉搜索树（BST）的先序遍历，要求根据这个先序遍历构造出二叉搜索树并返回其根节点。
/// 二叉搜索树的定义为：对于每个节点，Node.left 的任何后代的值严格小于 Node.val，Node.right 的任何后代的值严格大于 Node.val。
/// 二叉树的前序遍历顺序是首先显示节点的值，然后遍历 Node.left，最后遍历 Node.right。
/// 示例
/// 示例 1：
/// 输入：preorder = [8,5,1,7,10,12]
/// 输出：[8,5,10,1,7,null,12]
/// 解释：（这里可能需要结合图形来理解二叉搜索树的结构，题目中未给出图形，但根据输出可以想象出树的形态，8 为根节点，5 为左子节点，10 为右子节点，1 和 7 为 5 的左子树节点，12 为 10 的右子节点，7 为 1 的右子节点，5 的右子节点和 10 的左子节点为空，用 null 表示）
/// 示例 2：
/// 输入：preorder = [1,3]
/// 输出：[1,null,3]
public class Code01_ConstructBinarySearchTreeFromPreorderTraversal {

	/// 节点类
	public static class TreeNode {
		public int val;
		public TreeNode left;
		public TreeNode right;

		public TreeNode() {
		}

		public TreeNode(int val) {
			this.val = val;
		}

		public TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}

	/// 1.暴力递归的方法「时间复杂度O(N^2)」
	public static TreeNode bstFromPreorder1(int[] pre) {
		if (pre == null || pre.length == 0) {
			return null;
		}
		return process1(pre, 0, pre.length - 1);
	}

	public static TreeNode process1(int[] pre, int L, int R) {
		//1.边界「就算是遇到一个空树，一个无效范围，也可以正确返回」
		if (L > R) {
			return null;
		}
		//2.找到临界值
		int firstBig = L + 1;
		for (; firstBig <= R; firstBig++) {
			if (pre[firstBig] > pre[L]) {
				break;
			}
		}
		//3.拼凑树节点「下面可以简化，但是这样看着容易懂一些」
		TreeNode head = new TreeNode(pre[L]);
		TreeNode leftNode = process1(pre, L + 1, firstBig - 1);
		head.left = leftNode;
		TreeNode rightNode = process1(pre, firstBig, R);
		head.right = rightNode;
		return head;
	}

	/// 2.使用单调栈 「已经是时间复杂度最优的方法了，但是常数项还能优化」
	public static TreeNode bstFromPreorder2(int[] pre) {
		if (pre == null || pre.length == 0) {
			return null;
		}
		int N = pre.length;
		int[] nearBig = new int[N];
		for (int i = 0; i < N; i++) {
			nearBig[i] = -1;
		}
		// 使用单调栈形成 nearBig数组
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < N; i++) {
			while (!stack.isEmpty() && pre[stack.peek()] < pre[i]) {
				nearBig[stack.pop()] = i;
			}
			stack.push(i);
		}
		return process2(pre, 0, N - 1, nearBig);
	}

	public static TreeNode process2(int[] pre, int L, int R, int[] nearBig) {
		if (L > R) {
			return null;
		}

		//nearBig[L] == -1 右边比我大的不存在
		//nearBig[L] > R 比我大的数 在R边界的右边
		//int firstBig = (nearBig[L] == -1 ) ? R + 1 : nearBig[L]; 这样也是对的
		int firstBig = (nearBig[L] == -1 || nearBig[L] > R) ? R + 1 : nearBig[L];


		TreeNode head = new TreeNode(pre[L]);
		head.left = process2(pre, L + 1, firstBig - 1, nearBig);
		head.right = process2(pre, firstBig, R, nearBig);
		return head;
	}

	/// 3.最优解，使用原始数组替换掉单调栈结构
	public static TreeNode bstFromPreorder3(int[] pre) {
		if (pre == null || pre.length == 0) {
			return null;
		}
		int N = pre.length;
		int[] nearBig = new int[N];
		for (int i = 0; i < N; i++) {
			nearBig[i] = -1;
		}
		int[] stack = new int[N];
		int size = 0;
		for (int i = 0; i < N; i++) {
			while (size != 0 && pre[stack[size - 1]] < pre[i]) {
				nearBig[stack[--size]] = i;
			}
			stack[size++] = i;
		}
		return process3(pre, 0, N - 1, nearBig);
	}

	public static TreeNode process3(int[] pre, int L, int R, int[] nearBig) {
		if (L > R) {
			return null;
		}
		int firstBig = (nearBig[L] == -1 || nearBig[L] > R) ? R + 1 : nearBig[L];
		TreeNode head = new TreeNode(pre[L]);
		head.left = process3(pre, L + 1, firstBig - 1, nearBig);
		head.right = process3(pre, firstBig, R, nearBig);
		return head;
	}

}
