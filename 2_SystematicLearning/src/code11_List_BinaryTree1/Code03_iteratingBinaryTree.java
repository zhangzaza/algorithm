package code11_List_BinaryTree1;

/*二叉树的遍历*/
public class Code03_iteratingBinaryTree {


    ///递归序
    ///使用递归，每个点都会来三次。
    /// ｜第一次来（来的时候没看左边也没看右边印就是先序打印）｜
    /// ｜第二次来（左边结束了，要去后边之前打印就是中序打印）｜
    /// ｜第三次来（左边结束，后边也结束，最后要走的时候打印就是后序打印）｜
    /// 先序，中序，后序 都是递归序加工而来的
    public static void f(Node root) {
        if (root == null) {
            return;
        }
        //1.先序
        f(root.left);
        //2.中序
        f(root.right);
        //3.后序
    }


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
