package code29_Manacher;

/// 给定一个字符串，在尾部需要添加多少字符串，变成回文串
/// 题意：必须包含尾部的回文串  再加上 前面不是回文串的，就可以变成回文串
public class Code02_addStr {


    /// 思路：manacher算法，我们只需要R 到最后一个数字 就 就可以知道需要添加多少字符 即 (R-2C)
    public static int minCharsToAddToEnd(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        // 预处理字符串，中间插入特殊字符 '#'
        StringBuilder modifiedStr = new StringBuilder();
        modifiedStr.append("^");  // 开始标记符号，避免越界
        for (char c : s.toCharArray()) {
            modifiedStr.append("#").append(c);
        }
        modifiedStr.append("#$");  // 结束标记符号，避免越界

        // 使用Manacher算法计算每个位置的回文半径
        int[] p = new int[modifiedStr.length()];
        int center = 0, right = 0;
        for (int i = 1; i < modifiedStr.length() - 1; i++) {
            int mirror = 2 * center - i;  // 对应的对称位置

            if (right > i) {
                p[i] = Math.min(right - i, p[mirror]);
            }

            // 尝试扩展回文中心
            while (modifiedStr.charAt(i + p[i] + 1) == modifiedStr.charAt(i - p[i] - 1)) {
                p[i]++;
            }

            // 如果回文超过最右边界，则调整中心和右边界
            if (i + p[i] > right) {
                center = i;
                right = i + p[i];
            }
        }

        // 找到以字符串开头的最长回文
        int maxLen = 0;
        for (int i = 1; i < modifiedStr.length() - 1; i++) {
            if (i - p[i] == 1) {  // 表示覆盖到字符串头部
                maxLen = Math.max(maxLen, p[i]);
            }
        }

        // 计算需要添加的字符数
        return s.length() - maxLen;
    }


}
