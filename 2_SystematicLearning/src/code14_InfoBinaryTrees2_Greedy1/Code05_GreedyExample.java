package code14_InfoBinaryTrees2_Greedy1;


import java.util.Arrays;
import java.util.Comparator;

///从头到尾讲一道利用贪心算法求解的题目
/// 1.给定一个由字符串组成的数组strs
/// 2.必须把所有的字符串拼接起来
/// 3.返回所有可能的拼接结果中，字典序最小的结果
/// 例如：bab < bba
public class Code05_GreedyExample {

    /**
     * 贪心算法
     * 1.最自然智慧的算法
     * 2.用一种局部最功利的标准，总是做出在当前看来是最好的选择
     * 3.难点在于证明局部最功利的标准可以得到全局最优解
     * 4.对于贪心算法的学习主要以增加阅历和经验为主
     *
     *
     * 总结：
     *     贪心算法的特点：
     *     1.局部最优选择：在每一步，算法选择当前看来最好的选项，这称为做出局部最优选择。
     *     2.不可逆性：一旦做出选择，就不会被重新评估或撤销。算法会基于当前的选择继续进行下一步。
     *     3.可行性：算法确保当前的选择是可行的，并且遵循问题的约束条件。
     *     4.全局最优性：希望通过一系列局部最优选择，算法能够得到一个全局最优解。然而，这并不适用于所有问题。
     * */

    /**
     * 传递性 - ： 是拼接的意思
     * 1.a-b <= b-c
     * 2.b-c <= c-b
     * 推导出 a-c <= c-b
     *
     * a为一个 一串数字 ， b也为一串数字
     * a-b = a * 10^(b的长度) +b
     * */


    public static class MyComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return (o1 + o2).compareTo(o2 + o1);
        }
    }

    /// 贪心算法
    public static String minNumber(String[] strs) {
        if (strs == null || strs.length == 0){
            return "";
        }
        Arrays.sort(strs, new MyComparator());
        String ans = "";
        for (String str : strs) {
            ans += str;
        }
        return ans;
    }





}
