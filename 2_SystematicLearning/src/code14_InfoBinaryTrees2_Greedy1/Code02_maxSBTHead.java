package code14_InfoBinaryTrees2_Greedy1;

/// 给定一颗二叉树的头节点head，返回这颗二叉树中最大的二叉搜索子树的头节点
public class Code02_maxSBTHead {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }
    }

    /// 每个节点需要的信息：
    /// 1.最大 SBT 的大小
    /// 2.当前节点
    /// 3.max
    /// 4.min
    /// 5.isSBT
    public static class Info {
        public int maxSBTSize;
        public TreeNode bstRoot;
        public int max;
        public int min;
        public boolean isSBT;

        public Info(int maxSBTSize, TreeNode bstRoot, int max, int min, boolean isSBT) {
            this.maxSBTSize = maxSBTSize;
            this.bstRoot = bstRoot;
            this.max = max;
            this.min = min;
            this.isSBT = isSBT;
        }
    }

    public static TreeNode largestBSTSubtree(TreeNode head) {
        return process(head).bstRoot;
    }

    public static Info process(TreeNode head) {
        if (head == null) {
            return new Info(0, null, Integer.MIN_VALUE, Integer.MAX_VALUE, true);
        }

        // 1. 拿取并定义我们需要的信息
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);

        int maxBSTSize = 0;
        TreeNode bstRoot = null;
        boolean isSBT = false;
        int max = head.val;
        int min = head.val;

        // 2. 加工处理完成该节点的定义
        // 2.1. 拿到该节点为根节点的最大值 max 和 最小值 min
        if (leftInfo != null) {
            max = Math.max(max, leftInfo.max);
            min = Math.min(min, leftInfo.min);
        }
        if (rightInfo != null) {
            max = Math.max(max, rightInfo.max);
            min = Math.min(min, rightInfo.min);
        }

        // 2.2. 判断左右子树是否是搜索二叉树
        boolean leftIsBST = leftInfo.isSBT;
        boolean rightIsBST = rightInfo.isSBT;

        // 2.3. 判断要不要将该根节点也加入搜索二叉树中
        if (leftIsBST && rightIsBST) {
            boolean leftMaxLessHead = leftInfo == null || (leftInfo.max < head.val);
            boolean rightHeadLessMin = rightInfo == null || (rightInfo.min > head.val);
            if (leftMaxLessHead && rightHeadLessMin) {
                isSBT = true;
                bstRoot = head; // 该节点为当前 BST 的根节点
            }
        }

        // 2.4. 在没有 BST 的情况下，取左右子树中最大的 BST
        if (bstRoot == null) {
            maxBSTSize = Math.max(leftInfo.maxSBTSize, rightInfo.maxSBTSize);
            bstRoot = (leftInfo.maxSBTSize > rightInfo.maxSBTSize) ? leftInfo.bstRoot : rightInfo.bstRoot;
        }

        // 3. 返回该节点的信息
        return new Info(maxBSTSize, bstRoot, max, min, isSBT);
    }

}
