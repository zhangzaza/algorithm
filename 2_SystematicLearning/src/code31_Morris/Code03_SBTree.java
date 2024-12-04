package code31_Morris;


/// 使用Morris遍历 判断是否是搜索二叉树
public class Code03_SBTree {

    public static boolean morrisTraversal(Node head) {
        if (head == null) {
            return true;
        }
        Node cur = head;
        Node mostRight = null;

        ///添加两个参数
        Integer pre=null;
        boolean isSBT=true;

        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }

                //mostRight cur左树上最右节点
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else { // mostRight.right == cur
                    mostRight.right = null;

                }
            }

            /// 这里添加
            if (pre!=null && pre>=cur.value){
                isSBT=false;//不能直接返回，因为整棵树来说，我们有遍历整棵树，并且改动树的动作
            }
            pre=cur.value;

            cur = cur.right;
        }
        return isSBT;

    }


    static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }


}
