package code25_windowsMaxMin;

import java.util.LinkedList;

/// 给定一个环形路线上的一系列加油站，每个加油站有一定量的汽油和到达下一个加油站需要消耗的汽油。
/// 你的任务是找到一个起始加油站，使得汽车能够绕环形路线一周而不耗尽汽油。如果存在这样的起始点，返回其索引；否则，返回 -1。
///
/// - 设有 \( n \) 个加油站，编号从 0 到 \( n-1 \)。
/// - `gas[i]` 表示第 \( i \) 个加油站的汽油量。
/// - `cost[i]` 表示从第 \( i \) 个加油站到第 \( i+1 \) 个加油站需要的汽油量。
///
/// 如果汽油总量小于总消耗量，则无论从哪个加油站出发都无法完成一周，直接返回 -1。
public class Code03_GasCost {

    /// 思路：
    /// 1.先数组长度*2，对前面原先的长度的值进行累加和的计算「前缀和」
    /// 2.后面大于数组长度的下标，可以看成将一个长度为原数组长度的窗口向后移动，拿取length个元素的累加和，完成了 2*length 长度的数组
    /// 3.index从0开始，窗口长度为length，如果这个数组中存在值任意一个小于0，则该窗口无法完成一周，直接返回false
    /// 4.index从1开始，窗口长度为length，该窗口的每个数减去上一个数，如果存在值任意一个小于0，则该窗口无法完成一周，直接返回false
    /// 5.根据总结，我们可以知道，最小累加和必须大于0，所以我们可以根据双向端口来实现 最小值的更新
    public boolean[] canCompleteCircuitFromEachStation(int[] gas, int[] cost) {
        int n = gas.length;
        boolean[] result = new boolean[n];

        // 构造净收益数组
        int[] netGain = new int[n];
        for (int i = 0; i < n; i++) {
            netGain[i] = gas[i] - cost[i];
        }

        // 1.2.构造2倍长度的累加和数组
        int[] restGasPreSum = new int[2 * n];
        for (int i = 0; i < restGasPreSum.length; i++) {
            if (i == 0) {
                restGasPreSum[0] = netGain[i];
            } else if ( i <= n) {
                restGasPreSum[i] = netGain[i] + netGain[i-1];
            }else {
                restGasPreSum[i] = netGain[i] + restGasPreSum[i%n];
            }
        }
        //3.使用窗口最小值
        LinkedList<Integer> minWindow = new LinkedList<>();
        int R=0;
        for (int i = 0; i < n; i++) {
            //3.1.加载窗口最小值
            while (R<n){
                while (!minWindow.isEmpty() && restGasPreSum[minWindow.peekLast()]<=restGasPreSum[R]){
                    minWindow.pollLast();
                }
                minWindow.addLast(R);
                R++;
            }
            //3.2.判断是否可以完成一周
            if (minWindow.peekFirst()-restGasPreSum[i]>0) {
                result[i]=true;
            }

            //3.3.更新最小值：L向右移动，窗口缩小，判断最小值是否是头元素，是的话进行删除
            if (minWindow.peekFirst()==restGasPreSum[i]){
                minWindow.pollFirst();
            }
        }

        return result;
    }

}
