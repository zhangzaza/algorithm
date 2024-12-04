package code17_bitmap1;

import java.util.HashMap;
import java.util.HashSet;

/// 整张图的结构
public class BitMapGraph {

    /// 几号 城市 的节点
    public HashMap<Integer,BitmapNode> nodes;
    public HashSet<BitmapEdge> edges;

    public BitMapGraph()
    {
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }


    public static BitMapGraph createGraph(int[][] matrix){
        BitMapGraph bitMapGraph = new BitMapGraph();
        for (int i = 0; i < matrix.length; i++) {
            /// 1.拿取需要的值
            int weight = matrix[i][0];
            int from = matrix[i][1];
            int to = matrix[i][2];

            /// 2.创建节点，看看节点之前有没有添加
            if (!bitMapGraph.nodes.containsKey(from)){
                bitMapGraph.nodes.put(from, new BitmapNode(from));
            }
            if (!bitMapGraph.nodes.containsKey(to)){
                bitMapGraph.nodes.put(to, new BitmapNode(to));
            }

            /// 3.创建节点和边，放入到 NOde。和 Edge对象中去
            BitmapNode fromNode = bitMapGraph.nodes.get(from);
            BitmapNode toNode = bitMapGraph.nodes.get(to);
            BitmapEdge newEdge = new BitmapEdge(weight, fromNode, toNode);
            /// 3.1.增加邻居
            fromNode.nexts.add(toNode);
            /// 3.2.增加出度
            fromNode.out++;
            /// 3.3.增加入度
            toNode.in++;
            /// 3.4.节点增加边
            fromNode.edges.add(newEdge);
            /// 3.5.图增加边
            bitMapGraph.edges.add(newEdge);


        }

        return bitMapGraph;
    }

}
