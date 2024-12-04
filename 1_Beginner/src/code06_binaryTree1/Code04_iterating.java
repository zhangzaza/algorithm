package code06_binaryTree1;

/*二叉树的遍历*/
public class Code04_iterating {


    //先序顺序：头，左，右
    public static void preNode(Node root) {
        if (root == null) {
            return;
        }
        System.out.println(root.data);
        preNode(root.left);
        preNode(root.right);
    }


    //中序遍历：左，头，右
    public static void inOrder(Node root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        System.out.println(root.data);
        inOrder(root.right);
    }


    //后续遍历 左，右，头
    public static void postOrder(Node root) {
        if (root == null) {
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.println(root.data);
    }




    public static class Node {
        int data;
        Node left;
        Node right;
        public Node(int data) {
            this.data = data;
        }
    }

}
