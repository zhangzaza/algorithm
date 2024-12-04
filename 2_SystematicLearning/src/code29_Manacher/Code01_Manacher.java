package code29_Manacher;


/// 给定一个字符串，你需要找到它的最长回文子串。一个回文串是指正读和反读都相同的字符串。「子串是连续不间断的」
/// 具体用途：
/// 基因中的回文串，DNA序列中回文子序列的个数，DNA序列中回文子串的个数，DNA序列中回文子串的长度等等。
/// 可能导致一系列的现实生活中的意思。「研究者只研究研究本身」
public class Code01_Manacher {


    /// 回文直径：这是指一个回文字符串的长度。例如，回文串 "aba" 的直径是 3。
    /// 回文半径：对于一个给定中心的回文串，其回文半径是指从中心开始向两边扩展的最大距离（不包括中心本身）。
    ///     如果包括中心的话，半径即为此距离加1。例如，对于回文串 "#a#"（而不是原串），中心为 #，回文半径是 1，因为从 # 向两边扩展，最多能扩展到相邻的 a 和 a 的位置。
    ///
    /// 思路：「预处理字符串，在各字符之间插入 '#'，并在开头和结尾插入边界符号」
    /// 1. 每个位置的回文直径数组长度，半径数组长度
    /// 2. 每个位置的回文半径数组
    /// 3. 每个位置的回文串的右边界 R
    /// 4. 每个位置的回文串的中心点 C

    /// mavacher「i为下标开始遍历的情况」
    /// 1.如果 i 没有 被R 包含，那么就是暴力向两边扩展进行查询
    /// 2. 「i 被 R 包含，位置如下所示： L[ i' ......  C ...... i ]R 」
    /// 2.1. i' 为中心的回文 在  L[ ... ]R 内 ，所以i的回文区域至少和 i‘ 一样，不用计算直接下一个。   时间复杂度O(1)
    /// 2.2. i' 为中心的回文 在  L[ ... ]R 外 ，i的回文半径就是 R-i 「使用反证法可以得出这个结论」。  时间复杂度O(1)
    /// 2.3. i' 为中心的回文 刚刚好 在 L上 ，这里需要验证 R+1位置和 i-(i'的回文半径) 进行比较。
    ///

    /// 1.待讨论
    /// 2.1.时间复杂度O(1)
    /// 2.2.时间复杂度O(1)
    /// 2.3.待讨论
    /// 讨论时间复杂度：根据 1 和 2.3 情况进行分析，只要 1或者2.3 成功，R就会增大，如果增大，那就会验证2.1和2.2，R是有界限的，就是N ，所以时间复杂度为O(N)。


    /// 查找最长回文子串 「时间复杂度O(N)」
    public static String manacher(String s) {
        String T = preprocess(s); // 预处理后的字符串
        int n = T.length();
        int[] P = new int[n]; // 数组P来存储以每个字符为中心的回文半径
        int C = 0, R = 0; // 记录当前回文的中心和右边界

        // 遍历每个字符，尝试扩展回文
        for (int i = 1; i < n - 1; i++) {
            int mirror = 2 * C - i; // 计算i关于C的对称位置
            if (R > i) {
                P[i] = Math.min(R - i, P[mirror]); // 利用对称性，减少扩展次数
            }

            // 尝试扩展以i为中心的回文
            while (T.charAt(i + P[i] + 1) == T.charAt(i - P[i] - 1)) {
                P[i]++;
            }

            // 如果扩展后的回文超过了当前的右边界，则更新中心和右边界
            if (i + P[i] > R) {
                C = i;
                R = i + P[i];
            }
        }

        // 寻找P中最大元素对应的回文子串
        int maxLen = 0;
        int centerIndex = 0;
        for (int i = 1; i < n - 1; i++) {
            if (P[i] > maxLen) {
                maxLen = P[i];
                centerIndex = i;
            }
        }

        // 计算原字符串中最长回文子串的起始位置
        int start = (centerIndex - maxLen) / 2;
        return s.substring(start, start + maxLen);
    }

    // 预处理字符串，在各字符之间插入 '#'，并在开头和结尾插入边界符号
    public static String preprocess(String s) {
        if (s == null || s.length() == 0) {
            return "^$"; // 加入边界标记方便处理
        }
        StringBuilder sb = new StringBuilder("^");
        for (char c : s.toCharArray()) {
            sb.append("#").append(c);
        }
        sb.append("#$"); // 在结尾加入边界符号
        return sb.toString();
    }




    /// 暴力解决 「时间复杂度O(N^2)」
    // 使用暴力法查找最长回文子串
    public static String longestPalindrome(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }

        int maxLen = 0;
        String longest = "";

        // 遍历所有可能的中心，分别检查奇数和偶数长度的回文
        for (int i = 0; i < s.length(); i++) {
            // 检查奇数长度的回文
            int len1 = expandFromCenter(s, i, i);
            // 检查偶数长度的回文
            int len2 = expandFromCenter(s, i, i + 1);

            int currentMaxLen = Math.max(len1, len2);
            if (currentMaxLen > maxLen) {
                maxLen = currentMaxLen;
                int start = i - (currentMaxLen - 1) / 2;
                longest = s.substring(start, start + currentMaxLen);
            }
        }

        return longest;
    }

    // 从中心扩展回文子串
    private static int expandFromCenter(String s, int left, int right) {
        // 尝试从中心位置扩展回文
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        // 返回回文的长度
        return right - left - 1;
    }

}
