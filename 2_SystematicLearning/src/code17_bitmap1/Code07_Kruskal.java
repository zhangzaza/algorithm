package code17_bitmap1;

import java.util.*;

/// 最小权值生成树
/// 1.总是从权值最小的边开始考虑，依次考察权值变大的边
/// 2.当前的边要么进入最小生成树的集合，要么丢弃
/// 3.如果当前的边进入最小生成树的集合中不会形成环，就要当前边
/// 4.如果当前的边进入最小生成树的结合中会形成环，就不要当前边
/// 5.考察完所有的边之后，最小生成树的集合也得到了
///
/// 要结合并查集：并查集的作用是考察当前边是否会形成环，不会就加入，会就丢弃
/// 属于贪心算法
public class Code07_Kruskal {


    /// 并查询集合
    public static class UnionFind {

        public HashMap<BitmapNode, BitmapNode> fatherMap;

        public HashMap<BitmapNode, Integer> sizeMap;

        public UnionFind() {
            fatherMap = new HashMap<>();
            sizeMap = new HashMap<>();
        }


        public void makeSets(Collection<BitmapNode> nodes) {
            fatherMap.clear();
            sizeMap.clear();
            for (BitmapNode node : nodes) {
                fatherMap.put(node, node);
                sizeMap.put(node, 1);
            }
        }


        private BitmapNode findFather(BitmapNode node) {
            Stack<BitmapNode> path = new Stack<>();
            while (node != fatherMap.get(node)) {
                path.push(node);
                node = fatherMap.get(node);
            }
            while (!path.isEmpty()) {
                fatherMap.put(path.pop(), node);
            }
            return node;
        }


        public boolean isSameSet(BitmapNode a, BitmapNode b) {
            return findFather(a) == findFather(b);
        }


        public void union(BitmapNode a, BitmapNode b) {
            if (a == null || b == null) {
                return;
            }
            BitmapNode aFather = findFather(a);
            BitmapNode bFather = findFather(b);
            if (aFather != bFather) {
                int aSetSize = sizeMap.get(aFather);
                int bSetSize = sizeMap.get(bFather);
                if (aSetSize <= bSetSize) {
                    fatherMap.put(aFather, bFather);
                    sizeMap.put(bFather, aSetSize + bSetSize);
                    sizeMap.remove(aFather);
                } else {
                    fatherMap.put(bFather, aFather);
                    sizeMap.put(aFather, aSetSize + bSetSize);
                    sizeMap.remove(bFather);
                }
            }
        }
    }


    public static class EdgeComparator implements Comparator<BitmapEdge> {
        public int compare(BitmapEdge o1, BitmapEdge o2) {
            return o1.weight - o2.weight;
        }
    }


    public static Set<BitmapEdge> kruskalMST(BitMapGraph graph) {
        UnionFind unionFind = new UnionFind();
        unionFind.makeSets(graph.nodes.values());
        //小根堆，加入所有遍历过的边
        PriorityQueue<BitmapEdge> priorityQueue = new PriorityQueue<>(new EdgeComparator());
        for (BitmapEdge edge : graph.edges) {// M 条边
            priorityQueue.add(edge);// O(logM)
        }


        Set<BitmapEdge> result = new HashSet<>();
        while (!priorityQueue.isEmpty()) {// M 条边
            BitmapEdge edge = priorityQueue.poll(); // O(logM)
            /// O(1) 如果不形成环，就加入最小生成树的集合中，只要父节点不是同一个就不会形成环
            if (!unionFind.isSameSet(edge.from, edge.to)) {
                result.add(edge);
                unionFind.union(edge.from, edge.to);
            }
        }
        return result;

    }



}



