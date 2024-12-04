package code17_bitmap1;

/// 拓扑序，深度优先遍历
//OJ链接：https://www.lintcode.com/problem/topological-sorting/description

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/// 这里的排序规则：点次 概念「缓存：记忆化搜索」
public class Code06_topologySort3_deep {

    /// 题目提供的类
    class DirectedGraphNode {
        int label;
        List<DirectedGraphNode> neighbors;

        DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }



    public static Record f(DirectedGraphNode cur, HashMap<DirectedGraphNode, Record> cacheMap){
        if (cacheMap.containsKey(cur)){
            return cacheMap.get(cur);
        }
        //深度优先遍历，求点次
        int follow =0;
        for (DirectedGraphNode next : cur.neighbors){
            follow= (int) Math.max(follow,f(next, cacheMap).deep);
        }
        Record ans = new Record(cur ,follow+1);
        cacheMap.put(cur, ans);
        return ans;
    }


    public static class RecordComparator implements Comparator<Record> {
        @Override
        public int compare(Record o1, Record o2) {
            return Long.compare(o2.deep, o1.deep);
        }
    }



    public static class Record{
        public DirectedGraphNode node;
        public long deep;
        public Record(DirectedGraphNode node, long deep){
            this.node = node;
            this.deep = deep;
        }
    }



}
