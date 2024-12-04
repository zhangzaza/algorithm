package code13_InfoBinaryTrees1;


/// 二叉树的递归套路深度实践
/// 给定一颗二叉树的头节点head，任何两个节点之间都存在距离，返回整棵树的最大距离
public class Code04_maxDistanceBinaryTree {


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

    ///Node x 为根节点
    ///1.距离与x无关
    ///第一种可能性：在 x左树上 的最大距离
    ///第二种可能性：在 x右树上 的最大距离
    ///2.距离与x有关
    ///第三种可能性：x左树与x最远「左子树深度」 + x右树与x最远「右子树深度」 + 1

    public static class Info{
        public int maxDistance;
        public int height;
        public Info(int maxDistance, int height)
        {
            this.maxDistance = maxDistance;
            this.height = height;
        }
    }


    public static int maxDistance(TreeNode head) {
        if (head == null){
            return 0;
        }
        return process(head).maxDistance;
    }

    /// 1.拿取并定义我们需要的信息
    /// 2.加工处理完成该节点的定义 「需要知道可能性」
    /// 3.返回该节点的信息
    public static Info process(TreeNode head) {
        if (head == null){
            return new Info(0, 0);
        }
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);

        //第一种可能性
        int p1 = leftInfo.maxDistance;
        //第二种可能性
        int p2 = rightInfo.maxDistance;
        //第三种
        int p3 = leftInfo.height + rightInfo.height + 1;
        int maxDistance = Math.max(Math.max(p1, p2), p3);

        int height = Math.max(leftInfo.height, rightInfo.height)+1;

        return new Info(maxDistance, height);
    }

}
