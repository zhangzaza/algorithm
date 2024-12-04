package code13_InfoBinaryTrees1;

///判断是否是平衡二叉树
/// 判断一棵树是否是平衡二叉树（又称 AVL 树）的基本原则是：对于树中的每个节点，其左子树和右子树的高度差不超过 1，并且每个子树也是平衡的。
public class Code02_BalancedBinaryBTree {


    //判断是否是二叉树
    public static boolean isBalance(TreeNode root){
        return process(root).isBalanced;
    }

    public static Info process(TreeNode root) {
        if (root == null) {
            return new Info(true, 0);
        }
        Info leftInfo = process(root.left);
        Info rightInfo = process(root.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        boolean isBalanced = leftInfo.isBalanced && rightInfo.isBalanced && Math.abs(leftInfo.height - rightInfo.height) < 2;
        return new Info(isBalanced, height);
    }

    //以某个节点开始的时候
    //1.isBalance：记录是否是平衡二叉树
    //2.height：以该节点为根节点的时候，该树的高度
    //注意点：递归的时候，每个node的info没有被释放，都是抓住了去拿下面的数据，再返回
    public static class Info{
        public boolean isBalanced;
        public int height;
        public Info(boolean balanced, int height) {
            this.isBalanced = balanced;
            this.height = height;
        }
    }



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

}
