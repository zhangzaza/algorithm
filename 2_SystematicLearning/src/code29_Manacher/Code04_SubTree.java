package code29_Manacher;

/// 判断一个树是否是另一个树的子树是一个经典的问题，通常可以用递归的方法来解决。问题描述是：给定两棵二叉树 s 和 t，编写一个函数来检查 t 是否是 s 的子树。
/// 步骤
/// 1.树的序列化：将树序列化成字符串，可以使用前序遍历，同时在序列化过程中处理空节点（例如，用特殊字符表示）。这样可以确保结构信息不会丢失。
/// 2.KMP 字符串匹配：使用 KMP 算法在序列化后的字符串中查找匹配。
public class Code04_SubTree {

    ///1.序列化的时间复杂度为 O(m + n)，其中 m 和 n 是两棵树的节点数。
    ///2.在序列化后的字符串中使用 KMP 算法进行匹配，时间复杂度为 O(m + n)，其中 m 是树 t 的节点数，n 是树 s 的节点数。


    /// KMP算法查找模式串是否为文本串的子串
    private boolean KMPSearch(String text, String pattern) {
        int[] lps = computeLPSArray(pattern);
        int i = 0; // text的下标
        int j = 0; // pattern的下标

        while (i < text.length()) {
            if (pattern.charAt(j) == text.charAt(i)) {
                i++;
                j++;
            }
            if (j == pattern.length()) {
                return true; // 完全匹配
            } else if (i < text.length() && pattern.charAt(j) != text.charAt(i)) {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }
        return false;
    }


    // 序列化二叉树为字符串
    private String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serializeHelper(root, sb);
        return sb.toString();
    }

    private void serializeHelper(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append("#,"); // 用 '#' 表示空节点
            return;
        }
        sb.append(node.val).append(","); // 记录当前节点值
        serializeHelper(node.left, sb);  // 序列化左子树
        serializeHelper(node.right, sb); // 序列化右子树
    }


    // 计算 字符串的最长前缀后缀数组（LPS）
    private int[] computeLPSArray(String pattern) {
        int[] lps = new int[pattern.length()];
        int length = 0;
        int i = 1;

        while (i < pattern.length()) {
            if (pattern.charAt(i) == pattern.charAt(length)) {
                length++;
                lps[i] = length;
                i++;
            } else {
                if (length != 0) {
                    length = lps[length - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }

    // 二叉树节点定义
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}
