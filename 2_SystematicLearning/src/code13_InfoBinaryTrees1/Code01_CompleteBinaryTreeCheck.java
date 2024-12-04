package code13_InfoBinaryTrees1;


import java.util.LinkedList;

/// 判断一棵树是否是完全二叉树
/// 思路：
/// 1.如果一个节点有右孩子，没有左孩子，那就不是完全二叉树
/// 2.遍历到最左边为节点的时候，遇到同层有节点有孩子的就不是完全二叉树
public class Code01_CompleteBinaryTreeCheck {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node() {
        }

    }

    public static boolean isCBT(Node head) {
        if (head == null) {
            return true;
        }

        LinkedList<Node> queue = new LinkedList<>();

        //开关：是否到了叶子节点层
        boolean leaf = false;

        Node left = null;
        Node right = null;
        queue.add(head);
        while (!queue.isEmpty()) {
            head = queue.poll();
            left = head.left;
            right = head.right;

            //1.打开开关
            //1.遇到了不双全的节点之后，又发现当前节点不是叶子节点层
            //2.左空，右非空
            if (
                    (leaf && (left != null || right != null))
                            ||
                            (left == null && right != null)
            ) {
                return false;
            }

            //将左右孩子入队
            if (left !=null){
                queue.add(left);
            }
            if (right!=null){
                queue.add(right);
            }

            //遍历到第一个叶子节点，就打开开关
            if (left ==null || right==null){
                leaf=true;
            }

        }
        return true;


    }


}
