package code06_binaryTree1;


//测试链接：http://leetcode.com/problems/symmetric-tree
/*镜像树*/
public class Code06_symmetricTree {


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }


    public static boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isSymmetricTree(root,root);
    }


    //每次调用递归的时候 传入的两个节点都不一样
    private static boolean isSymmetricTree(TreeNode root, TreeNode root1) {
        if (root == null && root1 == null) {
            return true;
        }
        if (root != null ^ root1 != null) {
            return false;
        }
        return root.val==root1.val && isSymmetricTree(root.left, root1.right) && isSymmetricTree(root.right, root1.left);

    }


}
