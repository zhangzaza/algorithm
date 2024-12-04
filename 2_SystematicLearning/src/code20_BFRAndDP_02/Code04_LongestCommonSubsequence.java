package code20_BFRAndDP_02;

//测试链接：https://leetcode.com/problems/longest-common-subsequence/

/// 给定两个字符串text1和text2 ，返回它们的最长公共子序列的长度。如果没有公共子序列，则返回0 。
/// 字符串的子序列是在原字符串中删除一些字符（可以没有）而生成的新字符串，而不改变其余字符的相对顺序。
/// 例如， "ace"是"abcde"的子序列。
/// 两个字符串的公共子序列是两个字符串共有的子序列。
///
/// 思路：
/// 1.样本对位模型主要是看最后一个数字进行比较「经验」
public class Code04_LongestCommonSubsequence {


    public static int longestCommonSubsequence(String text1, String text2) {
        if (text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) {
            return 0;
        }
        //尝试
        return process(text1.toCharArray(), text2.toCharArray(), text1.length() - 1, text2.length() - 1);
    }


    /// 思路：
    /// 1.数组长度是否都为1
    /// 2.str1长度为1，str2长度大于1
    /// 3.str1长度大于1，str2长度为1
    /// 4.str1长度大于1，str2长度大于1
    /// 4.1.绝对不用 str1 的 i 字符，可能用 str2 的 j 字符的子序列
    /// 4.2.绝对不用 str2 的 j 字符，可能用 str1 的 i 字符的子序列
    /// 4.3.必须用 str1 的 i 字符，也用 str2 的 j 字符 的子序列「重要：但是这时候就重复了，所以要换一种写法，先判断两者是否一样，一样就都+1就为0『可能性不存在』」
    public static int process(char[] str1, char[] str2, int i, int j) {
        if (i == 0 && j == 0) {
            return str1[0] == str2[0] ? 1 : 0;
        } else if (i == 0) {
            if (str1[0] == str2[j]) {
                return 1;
            } else {
                return process(str1, str2, i, j - 1);
            }
        } else if (j == 0) {
            if (str1[i] == str2[0]) {
                return 1;
            } else {
                return process(str1, str2, i - 1, j);
            }
        } else {
            int p1 = process(str1, str2, i - 1, j);
            int p2 = process(str1, str2, i, j - 1);
            int p3 = str1[i] == str2[j] ? 1 + process(str1, str2, i - 1, j - 1) : 0;
            return Math.max(p1, Math.max(p2, p3));
        }
    }


    /// 第二种：dp动态规划
    public static int longestCommonSubsequence2(String text1, String text2) {
        if (text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) {
            return 0;
        }
        char[] str1 = text1.toCharArray();
        char[] str2 = text2.toCharArray();
        int N = str1.length;
        int M = str2.length;
        int[][] dp = new int[N][M];
        dp[0][0] = str1[0] == str2[0] ? 1 : 0; // 先判断第一个是否相等
        // 填表，形成一个二维表
        // 1.填好第一列
        for (int i = 1; i < N; i++) {
            dp[i][0] = str1[i] == str2[0] ? 1 : dp[i - 1][0];
        }
        // 2.填好第一行
        for (int j = 1; j < M; j++) {
            dp[0][j] = str1[0] == str2[j] ? 1 : dp[0][j - 1];
        }
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                int p1 = dp[i - 1][j];
                int p2 = dp[i][j - 1];
                int p3 = str1[i] == str2[j] ? 1 + dp[i - 1][j - 1] : 0;
                dp[i][j] = Math.max(p1, Math.max(p2, p3));
            }
        }
        return dp[N - 1][M - 1];


    }


}
