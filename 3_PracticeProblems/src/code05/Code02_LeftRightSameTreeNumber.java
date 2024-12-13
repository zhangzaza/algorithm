package code05;

// 如果一个节点X，它左树结构和右树结构完全一样，
// 那么我们说以X为头的子树是相等子树
// 给定一棵二叉树的头节点head，返回head整棵树上有多少棵相等子树
public class Code02_LeftRightSameTreeNumber {

	public static class Node {
		public int value;
		public Node left;
		public Node right;
		public Node(int v) {
			value = v;
		}
	}

	/// 1.暴力递归「时间复杂度O(N * logN)『因为每个节点都要遍历一遍，所以时间复杂度会高』」
	public static int sameNumber1(Node head) {
		if (head == null) {
			return 0;
		}
		return sameNumber1(head.left) + sameNumber1(head.right) + (same(head.left, head.right) ? 1 : 0);
	}

	public static boolean same(Node h1, Node h2) {
		if (h1 == null ^ h2 == null) { // 一个为空一个不为空就不相等
			return false;
		}
		if (h1 == null && h2 == null) { // 两个都为空
			return true;
		}
		// 两个都不为空
		return h1.value == h2.value && same(h1.left, h2.left) && same(h1.right, h2.right);
	}




	/// 2.使用信息传递Info时间复杂度O(N)
	public static class Info {
		public int ans;//完全相等时候节点的个数
		public String str;//hashcode后形成的 字符串「hashcode形成的字符串与原本的stri字符串无关」

		public Info(int a, String s) {
			ans = a;
			str = s;
		}
	}

	public static int sameNumber2(Node head) {
		String algorithm = "SHA-256";
		Hash hash = new Hash(algorithm);
		return process(head, hash).ans;
	}


	public static Info process(Node head, Hash hash) {
		if (head == null) {
			return new Info(0, hash.hashCode("#,"));
		}
		Info l = process(head.left, hash);
		Info r = process(head.right, hash);
		int ans = (l.str.equals(r.str) ? 1 : 0) + l.ans + r.ans;
		String str = hash.hashCode(String.valueOf(head.value) + "," + l.str + r.str);
		return new Info(ans, str);
	}



	/// 对数器：生成一个随机二叉树
	public static Node randomBinaryTree(int restLevel, int maxValue) {
		if (restLevel == 0) {
			return null;
		}
		Node head = Math.random() < 0.2 ? null : new Node((int) (Math.random() * maxValue));
		if (head != null) {
			head.left = randomBinaryTree(restLevel - 1, maxValue);
			head.right = randomBinaryTree(restLevel - 1, maxValue);
		}
		return head;
	}

	// for test
	public static void main(String[] args) {
		int maxLevel = 8;
		int maxValue = 4;
		int testTime = 100000;
		System.out.println("测试开始");
		for (int i = 0; i < testTime; i++) {
			Node head = randomBinaryTree(maxLevel, maxValue);
			int ans1 = sameNumber1(head);
			int ans2 = sameNumber2(head);
			if (ans1 != ans2) {
				System.out.println("出错了！");
				System.out.println(ans1);
				System.out.println(ans2);
			}
		}
		System.out.println("测试结束");

	}

}
