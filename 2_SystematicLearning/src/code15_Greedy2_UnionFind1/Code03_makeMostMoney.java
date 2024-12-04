package code15_Greedy2_UnionFind1;

import java.util.Comparator;
import java.util.PriorityQueue;

/// LeetCode - 502. IPO 「hard」
/// 给定两个数组 capital 和 profits，
/// 其中 capital[i] 表示第 i 个项目的花费，profits[i] 表示第 i 个项目的利润。你的任务是完成以下操作：
///
/// 你可以选择至多 K 个项目。
/// 你的初始资金为 M。
/// 说明：每做完一个项目，马上获得的利益，可以支持你去做下一个项目，不能并行的做项目
/// 你需要找到最大可能的利润。
public class Code03_makeMostMoney {


    public static class Program {
        public int cost;
        public int profit;

        public Program(int cost, int profit) {
            this.cost = cost;
            this.profit = profit;
        }
    }


    //比较器：大根堆，小根堆
    public static class MinCostComparator implements Comparator<Program> {
        public int compare(Program o1, Program o2) {
            return o2.cost - o1.cost;
        }
    }

    public static class MaxProfitComparator implements Comparator<Program> {
        public int compare(Program o1, Program o2) {
            return o2.profit - o1.profit;
        }
    }


    //自然智慧
    public static int findMaximizedCapita(int K, int M, int[] profits, int[] capital) {
        //小根堆
        PriorityQueue<Program> minCostQueue = new PriorityQueue<>(new MinCostComparator());
        //大根堆
        PriorityQueue<Program> maxProfitQueue = new PriorityQueue<>(new MaxProfitComparator());
        for (int i = 0; i < capital.length; i++) {
            minCostQueue.add(new Program(capital[i], profits[i]));
        }
        for (int i = 0; i < K; i++) {
            while (!minCostQueue.isEmpty() && minCostQueue.peek().cost <= M) {
                maxProfitQueue.add(maxProfitQueue.poll());
            }
            if (maxProfitQueue.isEmpty()) {
                return M;
            }
            M += maxProfitQueue.poll().profit;
        }
        return M;
    }








}
