package code29_Manacher;

/// 判断一个字符串 s2 是否是另一个字符串 s1 的旋转子串
///
///详细定义
///旋转操作：将字符串的一部分移动到字符串的另一端。例如，对于字符串 "abcde"，如果你从位置 2 切分，你得到两个部分 "ab" 和 "cde"。将 "ab" 移到 "cde" 的尾部，你得到 "cdeab"。
///旋转串：如果对于字符串 s1 存在某个切分位置，使得通过旋转操作能得到字符串 s2，则称 s2 为 s1 的旋转串。
public class Code03_reserveSubStr {


    ///思路：
    ///1.检查长度：如果 s1 和 s2 的长度不相等，s2 不可能是 s1 的旋转子串。
    ///2.构造双重字符串：将 s1 和自己拼接，形成 s1 + s1。
    ///3.KMP 子串查找：利用 KMP 算法在 s1 + s1 中查找 s2。

    /// 时间复杂度「O(n+m)」

    // KMP算法实现，用于查找模式串是否是文本串的子串
    private static boolean KMPSearch(String text, String pattern) {
        int[] lps = computeLPSArray(pattern);
        int i = 0; // text的下标
        int j = 0; // pattern的下标

        // 遍历文本字符串
        while (i < text.length()) {
            if (pattern.charAt(j) == text.charAt(i)) {
                i++;
                j++;
            }

            if (j == pattern.length()) {
                // 找到了一个匹配
                return true;
            } else if (i < text.length() && pattern.charAt(j) != text.charAt(i)) {
                if (j != 0) {
                    // 使用LPS数组来避免不必要的对比
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }
        return false; // 未找到匹配
    }

    // 计算模式字符串的最长前缀后缀匹配数组（LPS数组）
    private static int[] computeLPSArray(String pattern) {
        int[] lps = new int[pattern.length()];
        int length = 0; // 前缀的长度
        int i = 1; // 当前处理字符的位置

        // 遍历模式字符串来计算LPS数组
        while (i < pattern.length()) {
            if (pattern.charAt(i) == pattern.charAt(length)) {
                length++;
                lps[i] = length;
                i++;
            } else {
                if (length != 0) {
                    // 回退到之前的最长前缀
                    length = lps[length - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }


    // 判断s2是否是s1的旋转子串
    public static boolean isRotateSubstring(String s1, String s2) {
        // 长度不相等时，s2不可能是s1的旋转子串
        if (s1.length() != s2.length()) {
            return false;
        }
        // 将s1与自身拼接，查找s2是否为其子串
        String s1s1 = s1 + s1;
        return KMPSearch(s1s1, s2);
    }

}
