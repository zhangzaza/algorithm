package code17_bitmap1;
import java.util.HashMap;
import java.util.HashSet;


///初始化：
/// 选择一个源节点，设为起始点。
/// 1.创建一个距离数组 dist，用来存储从源节点到各个节点的最短路径长度。初始化时，源节点的距离设为 0，其他节点的距离设为无穷大（∞）。
/// 2.创建一个优先队列（通常使用最小堆实现），用来存储待处理的节点及其当前的最短路径估计值。将源节点加入队列。
///
/// 当然，下面是 Dijkstra 算法的流程图说明，通过图形化的方式来展示这个算法的主要步骤。
///
/// ### Dijkstra 算法流程图
///
/// 1. **初始化**：
///    - 选择源节点，初始化距离数组 `dist` 和优先队列。
///
///    ```
///    +-------------------+
///    |  选择源节点 A    |
///    +-------------------+
///              |
///    +-------------------+
///    |  初始化 dist数组 |
///    |  dist[A] = 0     |
///    |  dist[B] = ∞     |
///    |  dist[C] = ∞     |
///    |  dist[D] = ∞     |
///    +-------------------+
///              |
///    +-------------------+
///    |  将源节点 A 加入 |
///    |  优先队列        |
///    +-------------------+
///    ```
///
/// 2. **处理节点**：
///    - 从优先队列中提取节点，更新相邻节点的距离。
///
///    ```
///    +-------------------+
///    |  从优先队列取出  |
///    |  节点 A          |
///    +-------------------+
///              |
///    +-------------------+
///    |  更新相邻节点    |
///    |  dist[B] = 1     |
///    |  dist[C] = 4     |
///    +-------------------+
///              |
///    +-------------------+
///    |  将 B 和 C 加入  |
///    |  优先队列        |
///    +-------------------+
///    ```
///
/// 3. **重复处理**：
///    - 继续从优先队列中取出节点，直到队列为空。
///
///    ```
///    +-------------------+
///    |  从优先队列取出  |
///    |  节点 B          |
///    +-------------------+
///              |
///    +-------------------+
///    |  更新相邻节点    |
///    |  dist[D] = 4     |
///    +-------------------+
///              |
///    +-------------------+
///    |  将 D 加入优先队列 |
///    +-------------------+
///    ```
///
/// 4. **最终结果**：
///    - 最后，所有节点的最短路径长度都被计算出来。
///
///    ```
///    +-------------------+
///    |  处理完所有节点  |
///    +-------------------+
///              |
///    +-------------------+
///    |  输出最短路径    |
///    |  dist[A] = 0     |
///    |  dist[B] = 1     |
///    |  dist[C] = 4     |
///    |  dist[D] = 4     |
///    +-------------------+
///    ```
///
/// ### 完整流程总结
///
/// 通过以上的图示，可以清晰地看到 Dijkstra 算法的主要步骤：
/// 1. 初始化源节点和距离数组。
/// 2. 从优先队列中取出当前距离最小的节点。
/// 3. 更新该节点所有相邻节点的距离。
/// 4. 重复以上过程直至所有节点被处理。
///
/// 这个算法的关键在于如何有效地更新距离并且选择下一个最小的节点。
public class Code09_Dijkstra {



    public static HashMap<BitmapNode, Integer> Dijkstra( BitmapNode from){
        HashMap<BitmapNode, Integer> distanceMap = new HashMap<>();
        distanceMap.put(from,0);
        //打过对号的点
        HashSet<BitmapNode> selectedNodes = new HashSet<>();
        //剩余未解锁的点中最小的点
        BitmapNode minNode = getMinDistanceAndUnselectedNode(distanceMap,selectedNodes);
        while (minNode != null){
            // 原始点-> minNode(跳转点) 最小的距离distance
            int distance = distanceMap.get(minNode);
            for (BitmapEdge edge : minNode.edges) {
                //跳转点
                BitmapNode toNode = edge.to;
                //跳转点到原始点的距离
                int nextDistance = distance + edge.weight;

                if (!distanceMap.containsKey(toNode)){//没有放入过
                    distanceMap.put(toNode,nextDistance);
                }else {//放入过的话就进行比较
                    distanceMap.put(toNode,Math.min(distanceMap.get(toNode),nextDistance));
                }
            }
            selectedNodes.add(minNode);
            minNode = getMinDistanceAndUnselectedNode(distanceMap,selectedNodes);
        }
        return distanceMap;
    }


    // 优化，再返回距离最小的点
    /// 这里可以进行优化
    private static BitmapNode getMinDistanceAndUnselectedNode(HashMap<BitmapNode, Integer> distanceMap, HashSet<BitmapNode> selectedNodes) {
        BitmapNode minNode = null;
        int minDistance = Integer.MAX_VALUE;
        for (BitmapNode node : distanceMap.keySet()) {
            if (!selectedNodes.contains(node) && distanceMap.get(node) < minDistance) {
                minNode = node;
                minDistance = distanceMap.get(node);
            }
        }
        return minNode;
    }


}
