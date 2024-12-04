package code18_bitmap2_ForceRecursion1;


import code17_bitmap1.BitmapEdge;
import code17_bitmap1.BitmapNode;

import java.util.HashMap;

/// 优化了 从集合中更新并寻找最小值的方法
/// 并优化了主方法
public class Code01_Dijkstra2_ReinforcedHeap {


    public static class BitmapNodeRecord{
        public BitmapNode node;
        public int distance;
        public BitmapNodeRecord(BitmapNode node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }


    /// 加强堆的使用
    public static class BitmapHeap{
        /// 堆的数组
        private BitmapNode[] nodes;
        /// bitmapNode -> 堆上的什么位置 「反向索引表」
        private HashMap<BitmapNode, Integer> heapIndexMap;
        private HashMap<BitmapNode, Integer> distanceMap;
        private int size;


        public BitmapHeap(int size) {
            nodes = new BitmapNode[size];
            heapIndexMap = new HashMap<>();
            distanceMap = new HashMap<>();
            size = 0;
        }


        public boolean isEmpty() {
            return size == 0;
        }


        /// 该方法表示该节点是否进入过堆
        private boolean isEntered(BitmapNode node) {
            return heapIndexMap.containsKey(node);
        }

        /// 该方法表示该节点是否在堆中
        private boolean inHeap(BitmapNode node) {
            return heapIndexMap.get(node) != null;
        }

        private void swap(int index1, int index2){
            heapIndexMap.put(nodes[index1],index2);
            heapIndexMap.put(nodes[index2],index1);
            BitmapNode tmp = nodes[index1];
            nodes[index1] = nodes[index2];
            nodes[index2] = tmp;
        }

        public BitmapNodeRecord pop(){
            BitmapNodeRecord nodeRecord = new BitmapNodeRecord(nodes[0],distanceMap.get(nodes[0]));
            swap(0,--size);//0 > size -1 ; size -1 > 0
            heapIndexMap.put(nodes[size-1],-1);
            distanceMap.remove(nodes[size-1]);
            nodes[size -1] =null;
            heapify(0,--size);
            return nodeRecord;
        }


        private void insertHeapify(BitmapNode node,int index){
            while (distanceMap.get(nodes[index]) < distanceMap.get(nodes[(index-1)/2])){
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }


        private void heapify(int index,int heapSize){
            int left = index * 2 + 1;
            while (left < heapSize){
                int smallest = left + 1 < heapSize && distanceMap.get(nodes[left]) > distanceMap.get(nodes[left+1]) ? left+1:left;
                smallest = distanceMap.get(nodes[smallest]) < distanceMap.get(nodes[index]) ? smallest:index;
                if (smallest == index){
                    break;
                }
                swap(smallest,index);
                index = smallest;
                left = index * 2 + 1;
            }
        }


        /// 有一个点叫node，现在发现了一个从源节点出发到达node的距离为distance
        /// 判断要不要更新，如果需要的话，就更新
        public void addOrUpdateOrIgnore(BitmapNode node, int distance) {
            if(inHeap(node)){/// update
                distanceMap.put(node,Math.min(distanceMap.get(node),distance));
                insertHeapify(node,heapIndexMap.get(node));
            }else if(!isEntered(node)){/// add
                nodes[size] = node;
                distanceMap.put(node,distance);
                heapIndexMap.put(node,size);
                insertHeapify(node,size++);
            }
        }


    }



    /// 改进后的dijkstra算法
    /// 从head出发，所有head能到达的节点，生成到达每个节点的最小路径并返回
    public static HashMap<BitmapNode, Integer> dijkstra2(BitmapNode head, int size) {
        BitmapHeap nodeHeap = new BitmapHeap(size);
        nodeHeap.addOrUpdateOrIgnore(head,0);
        HashMap<BitmapNode, Integer> result = new HashMap<>();
        while (!nodeHeap.isEmpty()) {
            BitmapNodeRecord record = nodeHeap.pop();
            BitmapNode cur = record.node;
            int distance = record.distance;
            for (BitmapEdge edge : cur.edges) {
                nodeHeap.addOrUpdateOrIgnore(edge.to, distance + edge.weight);
            }
            result.put(cur, distance);
        }
        return  result;
    }





}
