package code07_binaryTree2;


//搜索二叉树的概念
//1.每个节点最多有两个子节点：即二叉树的基本结构。
//2.左子树小于根节点：任意节点的左子树中所有节点的值都小于该节点的值。
//3.右子树大于根节点：任意节点的右子树中所有节点的值都大于该节点的值。
//4.左右子树都是二叉搜索树：左子树和右子树本身也必须是二叉搜索树。

//对于二叉搜索树，进行中序遍历（In-order Traversal）可以得到一个从小到大的有序序列。中序遍历的顺序是：左子树 -> 根节点 -> 右子树。
//详细的看：2_SystematicLearning/src/code13_BasicAlgorithmsAndRecursiveBinaryTrees/Code03_SearchBinaryTree.java
//测试链接：http://leetcode.com/problems/validate-binary-search-tree
public class Code03_binarySearchTree {

    /*
    * 判断是否是搜索二叉树有两种形式
    * 1.通过中序遍历，形成一个数组，这个数组的顺序从小到大，那就是搜索二叉树
    * 2.使用递归
    *
    * 主要讲第二种方式
    * */


    //使用递归的实现
    public static boolean isSBT(TreeNode root) {
        return process(root).isSearchBinary;
    }


    public static Info process(TreeNode root){
        if (root == null){
            return null;//不能调0，因为可能存在负数
        }
        Info leftInfo = process(root.left);
        Info rightInfo = process(root.right);
        int max=root.val;
        int min=root.val;
        if (leftInfo != null ){
            max=Math.max(max, leftInfo.max);
            min=Math.min(min, leftInfo.min);
        }
        if (rightInfo != null ){
            max=Math.max(max, rightInfo.max);
            min=Math.min(min, rightInfo.min);
        }


        //先默认是搜索二叉树，存在违规的条件，再否认
        boolean isSearchBinary =true;
        if (leftInfo != null && !leftInfo.isSearchBinary){
            isSearchBinary=false;
        }
        if (rightInfo != null && !rightInfo.isSearchBinary){
            isSearchBinary=false;
        }
        // left max < x 并且 right min > x
        boolean leftMaxLessX= leftInfo == null ? true : (leftInfo.max < root.val);
        boolean rightMinMoreX = rightInfo == null ? true : (rightInfo.min > root.val);
        isSearchBinary = leftMaxLessX && rightMinMoreX;

        return new Info(isSearchBinary,min,max);
    }



    /*每个几点应该一样*/
    //要做到一个节点信息同等全
    //1.需要知道左子树的最大值【该节点可能是左节点，也可能是右节点】
    //2.需要知道右子树的最小值【该节点可能是左节点，也可能是右节点】
    //3.它自己是否是搜索二叉树
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
