package code16;

// 本题测试链接 : https://leetcode-cn.com/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof/
/// 1. 题目描述
/// 给定一个整数 n 和一个整数 m，0 <= m < n，n 个人围成一个圈，按顺时针顺序从 0 到 n - 1 编号，
/// 从编号 0 的人开始报数，报到 m 的人出圈，下一个人接着从 0 开始报数，如此循环，直到只剩下一个人，求最后剩下的人的编号。
///
/// 2. 示例
/// 示例 1：
/// 输入：n = 5，m = 2
/// 输出：3
/// 解释：初始圈里的人编号依次为 0、1、2、3、4。
/// 第一次报数，报到 2 的人出圈（即编号 2 的人出圈），此时圈里剩下 0、1、3、4；
/// 第二次报数从 3 开始，报到 2 的人出圈（即编号 0 的人出圈），此时圈里剩下 1、3、4；
/// 第三次报数从 1 开始，报到 2 的人出圈（即编号 4 的人出圈），此时圈里剩下 1、3；
/// 第四次报数从 1 开始，报到 2 的人出圈（即编号 1 的人出圈），最后剩下 3。
///
/// 示例 2：
/// 输入：n = 10，m = 17
/// 输出：2
///
/// 3. 限制条件
/// 1 <= n <= 10^5
/// 0 <= m < n
public class Code05_JosephusProblem {

	/// 1.y=x%m 的方法
	// 给定的编号是0~n-1的情况下，数到m就杀
	// 返回谁会活？
	public int lastRemaining1(int n, int m) {
		return getLive(n, m) - 1;
	}

	// 课上题目的设定是，给定的编号是1~n的情况下，数到m就杀
	// 返回谁会活？
	public static int getLive(int n, int m) {
		if (n == 1) {
			return 1;
		}
		return (getLive(n - 1, m) + m - 1) % n + 1;// getLive(n - 1, m)  杀之后的编号
	}

	// 提交直接通过
	// 给定的编号是0~n-1的情况下，数到m就杀
	// 返回谁会活？
	/// 2.迭代版本
	public int lastRemaining2(int n, int m) {
		int ans = 1;
		int r = 1;
		while (r <= n) {
			ans = (ans + m - 1) % (r++) + 1;
		}
		return ans - 1;
	}

	// 以下的code针对单链表，不要提交
	public static class Node {
		public int value;
		public Node next;

		public Node(int data) {
			this.value = data;
		}
	}

	public static Node josephusKill1(Node head, int m) {
		if (head == null || head.next == head || m < 1) {
			return head;
		}
		Node last = head;
		while (last.next != head) {
			last = last.next;
		}
		int count = 0;
		while (head != last) {
			if (++count == m) {
				last.next = head.next;
				count = 0;
			} else {
				last = last.next;
			}
			head = last.next;
		}
		return head;
	}

	public static Node josephusKill2(Node head, int m) {
		if (head == null || head.next == head || m < 1) {
			return head;
		}
		Node cur = head.next;
		int size = 1; // tmp -> list size
		while (cur != head) {
			size++;
			cur = cur.next;
		}
		int live = getLive(size, m); // tmp -> service node position
		while (--live != 0) {
			head = head.next;
		}
		head.next = head;
		return head;
	}

	public static void printCircularList(Node head) {
		if (head == null) {
			return;
		}
		System.out.print("Circular List: " + head.value + " ");
		Node cur = head.next;
		while (cur != head) {
			System.out.print(cur.value + " ");
			cur = cur.next;
		}
		System.out.println("-> " + head.value);
	}

	public static void main(String[] args) {
		Node head1 = new Node(1);
		head1.next = new Node(2);
		head1.next.next = new Node(3);
		head1.next.next.next = new Node(4);
		head1.next.next.next.next = new Node(5);
		head1.next.next.next.next.next = head1;
		printCircularList(head1);
		head1 = josephusKill1(head1, 3);
		printCircularList(head1);

		Node head2 = new Node(1);
		head2.next = new Node(2);
		head2.next.next = new Node(3);
		head2.next.next.next = new Node(4);
		head2.next.next.next.next = new Node(5);
		head2.next.next.next.next.next = head2;
		printCircularList(head2);
		head2 = josephusKill2(head2, 3);
		printCircularList(head2);
	}

}