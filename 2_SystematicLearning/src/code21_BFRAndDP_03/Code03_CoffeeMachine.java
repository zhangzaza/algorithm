package code21_BFRAndDP_03;

import java.util.Comparator;
import java.util.PriorityQueue;

/// 给定一个数组arr，arr[i] 代表第i号咖啡机泡一杯咖啡的时间
/// 给定一个正数N，表示N个人等着咖啡机泡咖啡，每台咖啡机只能轮流泡咖啡
/// 只有一台咖啡机，一次只能洗一个杯子，时间耗费为a，洗完才能洗下一杯
/// 每个咖啡杯也可以自己会发干净，时间耗费b，咖啡杯可以并行会发
/// 假设所有人拿到咖啡之后立刻喝干净,返回从开始等到所有咖啡机变干净的最短时间
/// 三个参数：int[] arr,int N ,int a,int b
///
/// 题意解读：
/// arr[3,1,7] : 三台咖啡机，各自泡完咖啡需要的时间
/// 一台戏咖啡杯机器：洗一个杯子需要 a 时间
/// 杯子自然挥发干净需要 b 时间
/// N 个人 从涌向咖啡机，到最后所有人人的杯都干净，返回最短的时间
///
/// 思路：
/// 第一步：泡咖啡
/// 使用小根堆实现 {key,value}
/// 1.key 是当前时间
/// 2.value 当前咖啡机，泡完咖啡需要的时间
/// 3.小根堆中的比较是（key+value）进行排序，表示下一人喝到咖啡的时间
/// 流程：arr[3,1,7]
/// 1.小根堆中为 （0,1），（0,3），（0,7）
/// 2.第一个人来了喝完咖啡之后：（1,1），（0,3），（0,7）
/// 2.第二个人来了喝完咖啡之后：（2,1），（0,3），（0,7）
/// 3.第三个人来了喝完咖啡之后：（0,3），（3,1），（0,7）
/// 4.第四个人来了喝完咖啡之后：（3,1），（3,3），（0,7）
///
/// 第二步：洗杯子
/// 洗：串行，挥发：并行
/// 比如说 3 这个时间点来了 4个人 a为2，b为5
/// 1.如果都要洗 那就是 3+2*4 = 11
/// 2.如果不用洗，那就 a+b = 8 「同时挥发」
public class Code03_CoffeeMachine {

    /*
     * timePoint 表示当前时间，workTime 表示当前机器泡一杯咖啡需要的时间
     * */
    public static class Machine {
        public int timePoint;
        public int workTime;

        public Machine(int t, int w) {
            timePoint = t;
            workTime = w;
        }
    }


    public static class MachineComparator implements Comparator<Machine> {
        @Override
        public int compare(Machine o1, Machine o2) {
            return (o1.timePoint + o1.workTime) - (o2.timePoint + o2.workTime);
        }
    }

    public static int minTime(int[] arr, int n, int a, int b) {
        PriorityQueue<Machine> heap = new PriorityQueue<>(new MachineComparator());
        for (int i = 0; i < arr.length; i++) {
            heap.add(new Machine(0, arr[i]));
        }
        // 表示什么时间点可以开始洗，经过小根堆之后，是最快喝完咖啡的时间
        int[] drinks = new int[n];
        for (int i = 0; i < n; i++) {
            Machine poll = heap.poll();
            poll.timePoint += poll.workTime;
            drinks[i] = poll.timePoint;
            heap.add(poll);
        }
        return process(drinks, a, b, 0, 0);
    }


    /// 第一种：暴力递归
    /// drinks[] : 每个人喝完咖啡的时间从小到大排列
    /// a: 洗杯子的时间
    /// b: 挥发的时间
    /// index: drinks[]的下标，表示到了第几个人
    /// lastTime: 上一个机器的结束时间
    public static int process(int[] drinks, int a, int b, int index, int lastTime) {
        if (index == drinks.length) {
            return 0;
        }
        //1.index号杯子 决定使用咖啡机洗
        //1.1.该杯子洗咖啡用的时间 「drinks[index]为当前杯咖啡喝完的时间，lastTime为洗咖啡机空出的时间，这两者求最大值就是看那个迟一些」
        int selfClean1 = Math.max(drinks[index], lastTime) + a;
        //1.2.剩余杯子洗完的时间点
        int restClean1 = process(drinks, a, b, index + 1, selfClean1);
        int p1 = Math.max(restClean1, selfClean1);//这里求最大值就是看那个迟结束，最终答案就是谁

        //2.index号杯子 决定挥发
        //2.1.该杯子挥发的时间点
        int selfClean2 = Math.max(drinks[index], lastTime) + b;
        //2.2.剩余杯子洗完的时间点「因为这里没有用咖啡机，所以还是lastTime」
        int restClean2 = process(drinks, a, b, index + 1, lastTime);
        int p2 = Math.max(restClean2, selfClean2);//与上述相同

        return Math.min(p1, p2);
    }


    /// 第二种方法：动态规划
    public static int processDP(int[] drinks, int washTime, int airTime) {
        int N = drinks.length;
        int maxFree = 0;
        // 找到所有杯子最大结束时间，假设全是用洗的方式，没有挥发，这是最坏的情况
        // 比如说：[3,5,17,22] washTime为3
        // 3+3 = 6 -> 6大于5 所以 6+3 = 9 -> 17大于7 所以 17+3 = 20 -> 22大于20 所以 22+3 = 25
        for (int i = 0; i < N; i++) {
            maxFree = Math.max(maxFree, drinks[i]) + washTime;
        }

        int[][] dp = new int[N + 1][maxFree + 1];
        // dp[N][0~maxFree] =0 默认值
        for (int index = N - 1; index >= 0; index--) {
            for (int free = 0; free <= maxFree; free++) {
                int selfClean1 = Math.max(drinks[index], free) + washTime;
                /// 就这里需要考虑数组越界的问题，下标restClean1 越界
                if (selfClean1 >= maxFree) {
                    continue;
                }
                int restClean1 = dp[index + 1][selfClean1];//selfClean1可能会越界，因为free已经接近maxFree，加上washTime可能会越界
                int p1 = Math.max(restClean1, selfClean1);

                int selfClean2 = drinks[index] + airTime;
                int restClean2 = dp[index + 1][free];
                int p2 = Math.max(restClean2, selfClean2);
                dp[index][free] = Math.min(p1, p2);
            }
        }
        return dp[0][0];
    }
}
