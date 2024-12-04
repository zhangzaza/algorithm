package code07_binaryTree2;


//平衡二叉树
//特点：每个节点的左子树和右子树的高度差最多为1。
//注意点：判断只需要，每个节点的左节点和右节点 的高度差 最多为1
public class Code02_binaryBalanceTree {


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
