package code03;

import java.util.*;


/// 给定三个参数：
/// 二叉树的头节点head，树上某个节点target，正数K
/// 从target开始，开始向上或者向下走
/// 返回与target的距离为K的所有节点，返回一个数组
public class Code08_DistanceKNodes {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int v) {
			value = v;
		}
	}

	public static List<Node> distanceKNodes(Node root, Node target, int K) {
		HashMap<Node, Node> parents = new HashMap<>();
		parents.put(root, null);
		/// 1.建立父亲节点<前一个为子节点，后一个为父亲节点>
		createParentMap(root, parents);

		/// 2.看图
		Queue<Node> queue = new LinkedList<>();
		HashSet<Node> visited = new HashSet<>();
		queue.offer(target);
		visited.add(target);
		int curLevel = 0;
		List<Node> ans = new ArrayList<>();
		while (!queue.isEmpty()) {
			int size = queue.size();//确定队列遍历次数

			//放入原本队列节点中的子节点
			while (size-- > 0) {
				Node cur = queue.poll();
				if (curLevel == K) {
					ans.add(cur);
				}
				if (cur.left != null && !visited.contains(cur.left)) {
					visited.add(cur.left);
					queue.offer(cur.left);
				}
				if (cur.right != null && !visited.contains(cur.right)) {
					visited.add(cur.right);
					queue.offer(cur.right);
				}
				if (parents.get(cur) != null && !visited.contains(parents.get(cur))) {
					visited.add(parents.get(cur));
					queue.offer(parents.get(cur));
				}
			}
			curLevel++;
			if (curLevel > K) {
				break;
			}
		}
		return ans;
	}


	//设置cur子节点的记录，不是设置cur的记录
	public static void createParentMap(Node cur, HashMap<Node, Node> parents) {
		if (cur == null) {
			return;
		}
		if (cur.left != null) {
			parents.put(cur.left, cur);
			createParentMap(cur.left, parents);
		}
		if (cur.right != null) {
			parents.put(cur.right, cur);
			createParentMap(cur.right, parents);
		}
	}

	public static void main(String[] args) {
		Node n0 = new Node(0);
		Node n1 = new Node(1);
		Node n2 = new Node(2);
		Node n3 = new Node(3);
		Node n4 = new Node(4);
		Node n5 = new Node(5);
		Node n6 = new Node(6);
		Node n7 = new Node(7);
		Node n8 = new Node(8);

		n3.left = n5;
		n3.right = n1;
		n5.left = n6;
		n5.right = n2;
		n1.left = n0;
		n1.right = n8;
		n2.left = n7;
		n2.right = n4;

		Node root = n3;
		Node target = n5;
		int K = 2;

		List<Node> ans = distanceKNodes(root, target, K);
		for (Node o1 : ans) {
			System.out.println(o1.value);
		}

	}

}
