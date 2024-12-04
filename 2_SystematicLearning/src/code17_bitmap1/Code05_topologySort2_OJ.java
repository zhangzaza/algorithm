package code17_bitmap1;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

//OJ链接：https://www.lintcode.com/problem/topological-sorting/description
/// 这里的排序规则：点次 概念「缓存：记忆化搜索」
public class Code05_topologySort2_OJ {

    /// 题目提供的类
    class DirectedGraphNode {
        int label;
        List<DirectedGraphNode> neighbors;

        DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }


    public static ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        ///1.所有过的节点 点次都在 cacheMap中
        HashMap<DirectedGraphNode, Record> cacheMap = new HashMap<>();
        for (DirectedGraphNode cur : graph){
            f(cur, cacheMap);
        }

        ///2.将所有的cacheMap 的节点都放入到 arraylist中
        ArrayList<Record> recordArr = new ArrayList<>();
        for(Record cur : cacheMap.values()){
            recordArr.add(cur);
        }

        ///3.对recordArr进行排序，从大到小
        recordArr.sort(new RecordComparator());

        ///4.返回结果
        ArrayList<DirectedGraphNode> ans = new ArrayList<>();
        for (Record cur : recordArr){
            ans.add(cur.node);
        }

        return ans;

    }


    // 当前来到cur点，请返回cur点所到之处，所有的点次！
    // 返回（cur，点次）
    // 缓存！！！ order
    //  key：某一个点的点次，之前算过了
    //  value：点次是多少
    public static Record f(DirectedGraphNode cur, HashMap<DirectedGraphNode, Record> cacheMap){
        if (cacheMap.containsKey(cur)){
            return cacheMap.get(cur);
        }
        //cur 的点次之前没算过
        long nodes = 0;
        for (DirectedGraphNode next : cur.neighbors){
            nodes += f(next, cacheMap).nodes;
        }
        Record ans = new Record(cur ,nodes+1);
        cacheMap.put(cur, ans);
        return ans;
    }




    public static class RecordComparator implements Comparator<Record> {
        @Override
        public int compare(Record o1, Record o2) {
            //o1.nodes == o2.nodes ? 0 : (o1.nodes>o2.nodes ? -1 : 1)
            return Long.compare(o2.nodes, o1.nodes);
        }
    }

    public static class Record{
        public DirectedGraphNode node;
        public long nodes;
        public Record(DirectedGraphNode node, long nodes){
            this.node = node;
            this.nodes = nodes;
        }
    }



}
