package code31_Morris;


/// Morris遍历：
///时间复杂度：O(N)，空间复杂度：O(1)
///
///
/// 二叉树的空间复杂度：数的高度 「系统会给你申请一个栈，如果可以，最大高度就是书的高度」
/// 所以：所有二叉树的遍历，高度这个空间是省不掉的
/// 前序遍历、中序遍历、后序遍历和层序遍历。在标准实现中，每种遍历的时间复杂度都是 O(n)
public class Code01_Morris_PreOrder_InOrder {



    /// morris 遍历
    public static void morrisTraversal(Node head) {
        if (head == null){
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }

                //mostRight cur左树上最右节点
                if (mostRight.right == null){
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else { // mostRight.right == cur
                    mostRight.right = null;

                }
            }
            cur= cur.right;
        }

    }


    /// morris改成先序遍历
    /// 1.第一次来到节点的时候打印
    /// 2.没有左树的时候打印
    public static void preTraversal(Node head) {
        if (head == null){
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }

                //mostRight cur左树上最右节点
                if (mostRight.right == null){
                    System.out.println(cur.value);  /// 这里添加打印
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else { // mostRight.right == cur
                    mostRight.right = null;

                }
            }else {
                System.out.println(cur.value); /// 这里添加打印
            }
            cur= cur.right;
        }

    }



    /// morris改成中序遍历
    /// 2.第二次来到节点
    public static void inTraversal(Node head) {
        if (head == null){
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }

                //mostRight cur左树上最右节点
                if (mostRight.right == null){

                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else { // mostRight.right == cur
                    mostRight.right = null;

                }
            }
            System.out.println(cur.value);  /// 这里添加打印
            cur= cur.right;
        }

    }




    static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node(int data)
        {
            this.value = data;
        }
    }


}
