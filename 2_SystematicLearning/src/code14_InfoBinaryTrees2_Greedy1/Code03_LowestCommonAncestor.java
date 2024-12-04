package code14_InfoBinaryTrees2_Greedy1;

/// LeetCode 236: 给定一颗二叉树的头节点head，和另外两个节点a和b，返回a和b的最低公共祖先 「mid」
public class Code03_LowestCommonAncestor {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    /// 罗列可能性：
    /// 1.与head无关「x不是最低汇聚点」
    /// 1.1.左节点有 a 或者 b，但是 右节点没有
    /// 1.2.右节点有 a 或者 b，但是 左节点没有
    /// 1.3.左右节点都没有a 或者 b
    ///
    /// 2.与head有关「x是答案」
    /// 2.1.左节点有 a 或者 b，右节点也有 a 或者 b
    /// 2.2.head为a，左节点或者右节点为b
    /// 2.3.head为b，左节点或者右节点为a
    public static class Info {
        boolean findA;// 是否找到a
        boolean findB;// 是否找到b
        TreeNode treeNode;// 最低公共祖先

        public Info(boolean findA, boolean findB, TreeNode ans) {
            this.findA = findA;
            this.findB = findB;
            this.treeNode = ans;
        }
    }



    public static TreeNode lowestCommonAncestor(TreeNode head, TreeNode a, TreeNode b) {
        if (head == null) {
            return null;
        }
        return process(head, a, b).treeNode;
    }


    public static Info process(TreeNode head, TreeNode a, TreeNode b) {
        if (head == null) {
            return new Info(false, false, null);
        }
        /// 1.拿取我们想要的信息
        Info leftInfo = process(head.left, a, b);
        Info rightInfo = process(head.right, a, b);
        boolean findA = leftInfo.findA || rightInfo.findA || head == a;
        boolean findB = leftInfo.findB || rightInfo.findB || head == b;
        /// 2.处理我们想要的信息
        TreeNode node = null;
        if (leftInfo.treeNode != null) {
            node = leftInfo.treeNode;
        } else if (rightInfo.treeNode != null) {
            node = rightInfo.treeNode;
        } else {
            if (findA && findB) {
                node = head;
            }
        }
        /// 3.返回信息
        return new Info(findA, findB, node);
    }


}
