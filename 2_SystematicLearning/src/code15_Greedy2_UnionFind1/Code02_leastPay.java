package code15_Greedy2_UnionFind1;

import java.util.PriorityQueue;

/// 最少钱数
/// 一块金条切成两半，是需要花费和长度数值一样的铜板
/// 比如长度为20的金条，不管怎么切，都要花费20个铜板，一群人香想分整块金条，怎么分最省铜板？
///
/// 例如给定数组{10,20,30},代表一共三个人，整块金条长度为60，金条要分成10，20，30 三个部分
///
/// 如果先把长度60的金条分成10和50，花费60；再把长度50的金条分成20和30，花费50； 一共花费110铜板
/// 但如果先把长度为60的金条分成30和20，花费60；再把长度30金条分成10和20，花费30；一共花费90铜板
///
/// 输入一个数组，返回分割的最下代价
public class Code02_leastPay {

    public static int leastPay(int[] arr) {
        PriorityQueue<Integer> payQueue = new PriorityQueue<>();
        for (int i = 0; i < arr.length; i++) {
            payQueue.add(arr[i]);
        }
        int sum = 0;
        int cur = 0;
        while (payQueue.size() > 1) {
            cur = payQueue.poll() + payQueue.poll();
            sum += cur;
            payQueue.add(cur);
        }
        return sum;
    }

}
