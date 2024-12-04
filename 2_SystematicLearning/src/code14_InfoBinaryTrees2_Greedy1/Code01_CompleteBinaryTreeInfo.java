package code14_InfoBinaryTrees2_Greedy1;

///判断完全二叉树
/// 判断一棵树是否是完全二叉树
/// 思路：
/// 1.如果一个节点有右孩子，没有左孩子，那就不是完全二叉树
/// 2.遍历到最左边为节点的时候，遇到同层有节点有孩子的就不是完全二叉树
///
///使用info套路 解决
public class Code01_CompleteBinaryTreeInfo {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node() {
        }

    }

    /// 列出可能性：
    /// 1.左满 右满 左高==右高
    /// 2.左完 右满 左高==右高+1
    /// 3.左满 右满 左高=右高+1
    /// 4.左满 右完 左高=右高
    public static class Info {
        public boolean isFull;
        public boolean isCBT;
        public int height;
        public Info() {
        }
        public Info(boolean isFull, boolean isCBT, int height) {
            this.isFull = isFull;
            this.isCBT = isCBT;
            this.height = height;
        }
    }

    public static boolean isCBT(Node head) {
        if (head == null) {
            return true;
        }
        return process(head).isCBT;
    }



    public static Info process(Node head) {
        if (head == null) {
            return new Info(true, true, 0);
        }
        ///1.拿取信息
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        ///2.对可能性进行处理
        boolean isFull = false;
        boolean isCBT = false;
        //可能性1.左满 右满 左高==右高
        if (leftInfo.isFull && rightInfo.isFull&&leftInfo.height == rightInfo.height) {
            isFull = true;
            isCBT = true;
        }
        //可能性2.左完 右满 左高==右高
        if (leftInfo.isCBT && rightInfo.isFull&&leftInfo.height == rightInfo.height+1) {
            isCBT = true;
        }
        //可能性3.左满 右满 左高=右高+1
        if (leftInfo.isFull && rightInfo.isFull&&leftInfo.height == rightInfo.height+1) {
            isCBT = true;
        }
        //可能性4.左满 右完 左高=右高
        if (leftInfo.isFull && rightInfo.isCBT&&leftInfo.height == rightInfo.height) {
            isCBT = true;
        }
        ///3.返回
        return new Info(isFull, isCBT, height);

    }



}
