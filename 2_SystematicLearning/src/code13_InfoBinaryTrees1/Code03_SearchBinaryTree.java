package code13_InfoBinaryTrees1;


//搜索二叉树的概念
//1.每个节点最多有两个子节点：即二叉树的基本结构。
//2.左子树小于根节点：任意节点的左子树中所有节点的值都小于该节点的值。
//3.右子树大于根节点：任意节点的右子树中所有节点的值都大于该节点的值。
//4.左右子树都是二叉搜索树：左子树和右子树本身也必须是二叉搜索树。

//对于二叉搜索树，进行中序遍历（In-order Traversal）可以得到一个从小到大的有序序列。中序遍历的顺序是：左子树 -> 根节点 -> 右子树。

//测试链接：http://leetcode.com/problems/validate-binary-search-tree
public class Code03_SearchBinaryTree {



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

    ///思路：
    /// 1.需要左子树的 max 与现在的点进行比较
    /// 2.需要右子树的 min 与现在的点进行比较
    /// 3.需要知道 左子树是不是搜索二叉树
    /// 4.需要知道 右子树是不是搜索二叉树
    /// 做该节点对左节点，右节点 要求的 总和
    public static class Info{
        public boolean isSearchBinary;
        public int min;
        public int max;
        public Info(boolean searchBinary, int max, int min) {
            this.isSearchBinary = searchBinary;
            this.max = max;
            this.min = min;
        }
    }


    public static boolean isSBT(TreeNode root) {
        if (root == null){
            return true;
        }
        return process(root).isSearchBinary;
    }


    ///思路：
    /// 1.拿取并定义我们需要的信息
    ///
    /// 2.加工处理完成该节点的定义
    /// 2.1.拿取该节点为根节点的最大值 max
    /// 2.2.拿取该节点为根节点的最小值 min
    /// 2.3.判断该节点是否是搜索二叉树 isSearchBinary
    /// 2.3.1.如果左右子树不是 搜索二叉树，就直接返回false
    /// 2.3.2.如果左右子树是搜索二叉树，但是左子树的最大值 大于等于 该节点，就返回false
    /// 2.3.3.如果左右子树是搜索二叉树，但是右子树的最小值 小于等于 该节点，就返回false
    ///
    /// 3.返回该节点的信息
    private static Info process(TreeNode node ) {
        //如果该节点为空，就直接让上层去执行
        if (node ==null){
            return null;
        }
        //1.拿取并定义我们需要的信息
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);
        boolean isSearchBinary= true;
        int min =node.val;
        int max =node.val;

        //2.加工处理完成该节点的定义
        //2.1.拿取该节点为根节点的最大值 max
        if (leftInfo != null) {
            max = Math.max(max, leftInfo.max);
        }
        if (rightInfo != null){
            max = Math.max(max, rightInfo.max);
        }
        //2.2.拿取该节点为根节点的最小值 min
        if (leftInfo != null){
            min = Math.min(min, leftInfo.min);
        }
        if (rightInfo != null){
            min = Math.min(min, rightInfo.min);
        }

        //2.3.判断该节点是否是搜索二叉树 isSearchBinary
        //2.3.1.如果左右子树不是 搜索二叉树，就直接返回false
        if (leftInfo != null && !leftInfo.isSearchBinary){
            isSearchBinary = false;
        }
        if (rightInfo != null && !rightInfo.isSearchBinary){
            isSearchBinary = false;
        }
        //2.3.2.如果左右子树是搜索二叉树，但是左子树的最大值 大于等于 该节点，就返回false
        if (leftInfo != null && leftInfo.max >= node.val){
            isSearchBinary = false;
        }
        //2.3.3.如果左右子树是搜索二叉树，但是右子树的最小值 小于等于 该节点，就返回false
        if (rightInfo != null && rightInfo.min <= node.val){
            isSearchBinary = false;
        }

        //3.返回该节点的信息
        return new Info(isSearchBinary,max,min);
    }


}
