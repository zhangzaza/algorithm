package code31_Morris;


/// 给定一颗二叉树的头节点head
/// 求以head为头的树中，最小深度是多少
public class Code04_minDeepth {

    static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }


    /// 第一种：使用递归解决「笔试可以直接这么写」
    public static int minHeight1(Node head) {
        if (head == null) {
            return 0;
        }
        return process(head);
    }

    /// 返回x为头的树，最小深度是多少
    public static int process(Node x) {
        if (x.left == null && x.right == null) {
            return 1;
        }
        // 左右子树起码有一个不为空
        int leftH = Integer.MAX_VALUE;
        if (x.left != null) {
            leftH = process(x.left);
        }
        int rightH = Integer.MAX_VALUE;
        if (x.right != null) {
            rightH = process(x.right);
        }
        return 1 + Math.min(leftH, rightH) + 1;
    }


    /// 第二种：morris遍历
    /// 需要解决的问题：
    /// 1.第几层：就是右边界+1
    /// 2.如果判断该节点不是右边界的子节点往回指向cur的时候，如果判断 根节点
    public static int minHeight2(Node head) {
        if (head == null){
            return 0;
        }
        Node cur =head;
        Node mostRight = null;
        int curLevel = 0;
        int minHeight = Integer.MAX_VALUE;
        while (cur != null){
            mostRight=cur.left;
            if (mostRight!=null){
                int rightBoardSize =1;
                while (mostRight.right!=null && mostRight.right!=cur){
                    mostRight=mostRight.right;
                    rightBoardSize++;
                }
                if (mostRight.right==null){/// 第一次到达
                    curLevel++;
                    mostRight.right=cur;
                    cur=cur.left;
                    continue;
                }else { /// 第二次到达
                    if (mostRight.left ==null){
                        minHeight = Math.min(minHeight,curLevel);
                    }
                    curLevel -=rightBoardSize;
                    mostRight.right=null;
                }
            }else { /// 只有一次到达
                curLevel++;
            }
            cur=cur.right;
        }
        int finalRight =1;
        cur = head;
        while (cur.right!=null){
            cur=cur.right;
            finalRight++;
        }
        if (cur.left == null && cur.right == null){
            minHeight = Math.min(minHeight,finalRight);
        }
        return minHeight;
    }

}
