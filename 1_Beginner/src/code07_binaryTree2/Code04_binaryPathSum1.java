package code07_binaryTree2;


//测试链接 ： https://leetcode.com/problems/path-sum/description/
public class Code04_binaryPathSum1 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


    private static boolean isSumSymbol =false;


    public static boolean isSum(TreeNode node,int sum){
        if (node == null){
            return false;
        }
        isSumSymbol =false;//因为可能之前有人调用过了，所以这里要改成false
        process(node,0,sum); //当前节点加上 当前节点的值在下一步操作，所以这里传递0，不传node.value
        return isSumSymbol;
    }

    //presum:当前的累加和；sum：目标和
    public  static void process(TreeNode node,int preSum,int sum){
        if (node.left == null && node.right == null){
            if ( (preSum+node.val) == sum){
                isSumSymbol = true;
            }
            return;
        }

        //非叶子节点
        if (node.left != null){
            process(node.left,preSum+node.val,sum);
        }
        if (node.right != null){
            process(node.right,preSum+node.val,sum);
        }
    }







}
