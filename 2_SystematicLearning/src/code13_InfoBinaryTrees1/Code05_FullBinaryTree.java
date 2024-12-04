package code13_InfoBinaryTrees1;



//满二叉树的定义：
//满二叉树的每一层都被完全填满
//节点数量与高度的关系：nodes = 2^h - 1。 「1向左 移动 h 位」
public class Code05_FullBinaryTree {


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

    //只需要高度和节点数量
    public static class Info {
        int nodes;
        int height;
        public Info(int nodes, int height) {
            this.nodes = nodes;
            this.height = height;
        }
    }


    ///注意点：只需要最后判断一次即可，并不用每次都进行判断
    public static boolean isFull(TreeNode head) {
        if (head == null) {
            return true;
        }
        return process(head).nodes == (1 << process(head).height) - 1;
    }

    /// 1.拿取并定义我们需要的信息
    /// 2.加工处理完成该节点的定义 「需要知道可能性」
    /// 3.返回该节点的信息
    public static Info process(TreeNode head) {
        if (head == null) {
            return new Info(0, 0);
        }
        //1.
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);
        //2.
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        int nodes = leftInfo.nodes + rightInfo.nodes + 1;
        //3.
        return new Info(nodes, height);

    }




}
