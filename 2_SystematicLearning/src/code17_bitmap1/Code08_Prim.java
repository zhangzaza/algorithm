package code17_bitmap1;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/// 最小生成树算法之Prim
/// 1.可以从任意节点出发来寻找最小生成树
/// 2.某个点加入到被选取的点中后，解锁这个点出发的所有新的边
/// 3.在所有解锁的边中选最小的边，然后看看这个边会不会形成环
/// 4.如果会，不要加入到最小生成树中，否则加入到最小生成树中，重复3
/// 5.如果不会，要当前边，将该边的指向点加入到被选取的点中，重复2
/// 6.当所有点都被选取，最小生成树就得到了
///
/// 属于贪心算法
public class Code08_Prim {

    public static class EdgeComparator implements Comparator<BitmapEdge> {
        public int compare(BitmapEdge o1, BitmapEdge o2) {
            return o1.weight - o2.weight;
        }
    }


    public static Set<BitmapEdge> primMST(BitMapGraph graph) {
        //解锁的边进入小根堆
        PriorityQueue<BitmapEdge> priorityQueue = new PriorityQueue<>(new EdgeComparator());

        //哪些点被解锁了
        HashSet<BitmapNode> nodesSet = new HashSet<>();
        //依次挑选的边在result中
        Set<BitmapEdge> result = new HashSet<>();

        //随便解锁一个点
        for (BitmapNode node : graph.nodes.values()) {
            //node 是开始点
            if (!nodesSet.contains(node)) {
                nodesSet.add(node);
                //由一个点，解锁所有相连的边
                for (BitmapEdge edge : node.edges) {
                    priorityQueue.add(edge);
                }
                while (!priorityQueue.isEmpty()) {
                    //弹出解锁的边,最小的边
                    BitmapEdge edge = priorityQueue.poll();
                    //可能是一个新的点
                    BitmapNode toNode = edge.to;
                    //不含有的时候就是新的点
                    if (!nodesSet.contains(toNode)) {
                        nodesSet.add(toNode);
                        result.add(edge);
                        for (BitmapEdge nextEdge : toNode.edges) {
                            priorityQueue.add(nextEdge);
                        }
                    }
                }
            }
            break;
        }
        return result;
    }


}
