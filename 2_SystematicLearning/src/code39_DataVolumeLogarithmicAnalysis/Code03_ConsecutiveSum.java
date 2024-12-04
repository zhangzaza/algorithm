package code39_DataVolumeLogarithmicAnalysis;

/// 给定一个正整数
/// 找出一组连续的正整数，使得它们的和等于给定的数字
/// 能找出 就返回true ，没有就为false
///
/// 思路：
/// 1.从2 ，3，4 .... 个能组成的这个数进行尝试
/// 2.先求平均值，然后前后调整进行判断
public class Code03_ConsecutiveSum {

    /// 暴力解法
    /// 思路：
    /// 1.从1开始累加，能否累加到num，如果有就是true
    /// 2.从2开始累加，能否累加到num，如果有就是true
    public static boolean isSum1(int num) {
        for (int i = 0; i < num; i++) {
            int sum = i;
            for (int j = i + 1; j <= num; j++) {
                if (sum + j > num) {
                    break;
                }
                if (sum + j == num) {
                    return true;
                }
                sum += j;
            }
        }
        return false;
    }


    /// 根据答案拿结果:如果不是2的某次方就为false
    /// 思路：
    /// 1.如果这个数的二进制码只有一个数字上为1 那么就是true
    /// 2.拿出 最右侧的 1 即 ： index & (~index + 1)
    public static boolean isSum2(int num) {
        //第1中写法：
        return (num & (~num + 1)) == num;
        //第2种写法： return (num & (-num)) == 0;
        //第3种写法： return (num & (num - 1)) != 0;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 200; i++) {
            System.out.println(i + " : " + isSum1(i));
        }
        // 得出只有2的某次方 的不出

    }


}
