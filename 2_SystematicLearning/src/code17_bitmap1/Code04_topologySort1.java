package code17_bitmap1;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/// 拓扑排序算法 说明
/// 循环依赖的说明
/// 定义：
/// 在一个有向图中，如果存在一个从节点 A 到节点 B 的路径，同时又存在从节点 B 返回到节点 A 的路径，那么我们就说这两个节点之间存在循环依赖。
/// 影响：循环依赖使得图不再是有向无环图（DAG）。拓扑排序只适用于 DAG，因此如果图中存在循环依赖，就无法进行拓扑排序。
///
///
/// 图的拓扑排序算法：
/// 1.在图中找到所有入度为0的点输出
/// 2.把所有入度为0的点在图中删掉，继续找入度为0的点输出，周而复始
/// 3.图的所有点都被删除后，一次输出的顺序就是拓扑排序
///
/// 要求：有向图且其中没有环
/// 应用：时间安排，编译顺序
public class Code04_topologySort1 {

    public static List<BitmapNode> topologySort(BitMapGraph graph) {
        //key 某个节点 ; value 为剩余的入度
        HashMap<BitmapNode, Integer> inMap = new HashMap<>();

        //只有剩余入度为0的点，才进入这个队列
        Queue<BitmapNode> zeroInQueue = new LinkedList<>();

        //将所有的节点和所有的节点 -> 放入到集合中
        for (BitmapNode node : graph.nodes.values()) {
            inMap.put(node, node.in);
            if (node.in == 0) {
                zeroInQueue.add(node);
            }
        }

        ///1.先拿取入度为0的点
        ///2.拿取掉它的影响力，就是它的nexts的入度都 -1
        ///3.再判断拿掉的点中哪些入度为0，就加入到队列中，以此反复
        List<BitmapNode> result = new LinkedList<>();
        while (!zeroInQueue.isEmpty()) {
            BitmapNode cur = zeroInQueue.poll();
            result.add(cur);
            for (BitmapNode next : cur.nexts) {
                inMap.put(next, inMap.get(next) - 1);
                if (inMap.get(next) == 0) {
                    zeroInQueue.add(next);
                }
            }
        }

        return result;
    }



}
