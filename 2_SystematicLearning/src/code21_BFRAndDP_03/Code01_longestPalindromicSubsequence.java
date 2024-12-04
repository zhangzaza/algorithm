package code21_BFRAndDP_03;


/// 给定一个字符串str，返回这个字符串的最长回文 子序列的长度
/// 比如：str = "abcddcba"，返回7，最长回文子序列是"abcddcba"，长度为7
/// 比如：str = “a12b3c43def2ghi1kpm” ,最长回文子序列“1234321” 或者“123c321”，返回长度7
/// 重要：「子序列是不连续的，子串是连续的」
/// 测试链接：https://leetcode.com/problems/longest-palindromic-subsequence
/// 思路：
/// 一个字符串的最长回文子序列是 与该字符串逆序 最大的公共子序列
public class Code01_longestPalindromicSubsequence {

    /// 第一步：暴力递归
    public static int lpsl(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        return process(str, 0, str.length - 1);
    }


    /// str[L.....R] 最长回文子序列长度返回
    public static int process(char[] str, int L, int R) {
        if (L == R) {//两者相距
            return 1;
        }
        if (L == R - 1) {// 两者相距，但是隔着一个位置
            return (str[L] == str[R]) ? 2 : 1;
        }
        // 两者间距大于2
        // 四种情况：
        // 1. X L ；X R
        // 2. X L ；Y R
        // 3. Y L ；X R
        // 4. Y L ；Y R 这个情况是str[L] == str[R]
        int p1 = 0, p2 = 0, p3 = 0, p4 = 0;
        if (str[L] == str[R]) {
            p1 = process(str, L + 1, R - 1) + 2;
        } else {
            p2 = process(str, L + 1, R);
            p3 = process(str, L, R - 1);
            p4 = process(str, L + 1, R - 1);
        }
        return Math.max(p1, Math.max(p2, Math.max(p3, p4)));
    }


    /// 第二种：动态规划
    public static int lpsl2(String s) {
        if (s == null || s.length()==0){
            return 0;
        }
        char[] str = s.toCharArray();
        int N=str.length;
        int[][] dp = new int[N][N];
        // 根据base case得出，直接填好 两个对角线
        for (int i=0;i<N-1;i++){
            dp[i][i]=1;
            dp[i][i+1]=(str[i]==str[i+1])?2:1;
        }

        // N-3 开始，是因为 N-1 ，和N-2 两条对角线已经填好了
        for(int i=N-3;i>=0;i--){
            // j 从 i+2 开始，因为 i 和 i+1 已经填好了
            for(int j=i+2;j<N;j++){
                int p1=0,p2=0,p3=0,p4=0;
                if (str[i]==str[j]){
                    p1=dp[i+1][j-1]+2;
                }else {
                    p2=dp[i+1][j];
                    p3=dp[i][j-1];
                    p4=dp[i+1][j-1];
               }
                dp[i][j]=Math.max(p1,Math.max(p2,Math.max(p3,p4)));
            }
        }

        // 因为返回的是 process(str, 0, str.length - 1)
        return dp[0][N-1];
    }


    /// 第三种：对第二种动态规划的优化
    public static int lpsl3(String s) {
        if (s == null || s.length()==0){
            return 0;
        }
        char[] str = s.toCharArray();
        int N=str.length;
        int[][] dp = new int[N][N];
        for (int i=0;i<N-1;i++){
            dp[i][i]=1;
            dp[i][i+1]=(str[i]==str[i+1])?2:1;
        }

        // 只有在这里进行优化，小优化 「看 Code01_4」
        for(int i=N-3;i>=0;i--){
            for(int j=i+2;j<N;j++){
                dp[i][j]=Math.max(dp[i+1][j],dp[i][j-1]);
                if (str[i]==str[j]){
                    dp[i][j]=Math.max(dp[i][j],dp[i+1][j-1]+2);
                }
            }
        }
        return dp[0][N-1];
    }

}
