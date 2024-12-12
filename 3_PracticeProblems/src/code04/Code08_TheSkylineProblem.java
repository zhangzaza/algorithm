package code04;

import java.util.*;
import java.util.Map.Entry;

// 本题测试链接 : https://leetcode.com/problems/the-skyline-problem/
/// 给定一系列建筑物的位置和高度，以数组 buildings 表示，其中每个元素 buildings[i] = [left_i, right_i, height_i] 表示建筑物从 left_i 到 right_i（左闭右开区间），高度为 height_i。要求返回由这些建筑物形成的天际线，天际线应该表示为 “关键点” 的列表，格式为 [[x1,y1],[x2,y2],...]，并按照 x 坐标进行排序。天际线中的关键点是水平线段的左端点，且关键点的 y 坐标是在该 x 坐标处最高建筑物的高度（如果有多个最高建筑物，则取最右边的那个）。
/// 示例
/// 示例 1：
/// 输入：buildings = [[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]
/// 输出：[[2,10],[3,15],[7,12],[12,0],[15,10],[20,8],[24,0]]
/// 解释：图 A 显示输入的所有建筑物的位置和高度，图 B 显示由这些建筑物形成的天际线。图 B 中的红点表示输出列表中的关键点。
/// 示例 2：
/// 输入：buildings = [[0,2,3],[2,5,3]]
/// 输出：[[0,3],[5,0]]
public class Code08_TheSkylineProblem {

	public static class Node {
		public int x;
		public boolean isAdd;
		public int h;

		public Node(int x, boolean isAdd, int h) {
			this.x = x;
			this.isAdd = isAdd;
			this.h = h;
		}
	}

	public static class NodeComparator implements Comparator<Node> {
		@Override
		public int compare(Node o1, Node o2) {
			return o1.x - o2.x;
		}
	}

	public static List<List<Integer>> getSkyline(int[][] matrix) {
		Node[] nodes = new Node[matrix.length * 2];//生成几个对象，就是大楼的两倍
		for (int i = 0; i < matrix.length; i++) {
			nodes[i * 2] = new Node(matrix[i][0], true, matrix[i][2]);//上升的大楼高度
			nodes[i * 2 + 1] = new Node(matrix[i][1], false, matrix[i][2]);//下降的大楼高度
		}
		Arrays.sort(nodes, new NodeComparator());//对大楼进行排序
		// key：高度  value：次数
		TreeMap<Integer, Integer> mapHeightTimes = new TreeMap<>();
		// key：x坐标  value：高度 例如：<1,9>,<2,9>,3,19>
		TreeMap<Integer, Integer> mapXHeight = new TreeMap<>();
		for (int i = 0; i < nodes.length; i++) {
			if (nodes[i].isAdd) {//如果是 + 的高度
				if (!mapHeightTimes.containsKey(nodes[i].h)) {
					mapHeightTimes.put(nodes[i].h, 1);
				} else {
					mapHeightTimes.put(nodes[i].h, mapHeightTimes.get(nodes[i].h) + 1);
				}
			} else {// 不是 + 的高度，就是 - 的高度
				if (mapHeightTimes.get(nodes[i].h) == 1) {
					mapHeightTimes.remove(nodes[i].h);//为什么这里是remove？因为需要知道这时候的最大的高度，为0还保留会干扰判断
				} else {
					mapHeightTimes.put(nodes[i].h, mapHeightTimes.get(nodes[i].h) - 1);
				}
			}
			if (mapHeightTimes.isEmpty()) {
				mapXHeight.put(nodes[i].x, 0);
			} else {
				//.lastKey() 是 TreeMap 类的一个方法，用于返回映射中的最大键。具体来说：
				//返回值：返回 TreeMap 中最大的键。
				//应用场景：通常用于获取当前集合中的最大值，特别是在需要动态维护有序数据的场景中。
				//在你的代码中，mapHeightTimes 是一个 TreeMap，存储了高度及其出现的次数。调用 mapHeightTimes.lastKey() 的目的是获取当前最高的建筑物高度
				mapXHeight.put(nodes[i].x, mapHeightTimes.lastKey());
			}
		}

		//对mapXHeight 生成二维表
		List<List<Integer>> ans = new ArrayList<>();
		for (Entry<Integer, Integer> entry : mapXHeight.entrySet()) {
			int curX = entry.getKey();
			int curMaxHeight = entry.getValue();
			if (ans.isEmpty() || ans.get(ans.size() - 1).get(1) != curMaxHeight) {
				ans.add(new ArrayList<>(Arrays.asList(curX, curMaxHeight)));
			}
		}
		return ans;
	}

}
