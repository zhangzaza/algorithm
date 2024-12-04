package code06_binaryTree1;


//测试链接：http://leetcode.com/problems/same-tree
public class Code05_sameTree {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }


    public static boolean isSameTree(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        if (root1 != null^ root2!=null) {
            return false;
        }
        return root2.val == root1.val && isSameTree(root1.left, root2.left) && isSameTree(root1.right, root2.right);
    }


}
