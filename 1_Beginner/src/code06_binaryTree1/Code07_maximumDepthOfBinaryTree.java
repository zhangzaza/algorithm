package code06_binaryTree1;


//测试链接：http://leetcode.com/problems/maximum-depth-of-binary-tree
public class Code07_maximumDepthOfBinaryTree {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return  Math.max(maxDepth(root.left),maxDepth(root.right))+1;
    }


}
