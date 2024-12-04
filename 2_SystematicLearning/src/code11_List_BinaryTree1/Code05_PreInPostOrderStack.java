package code11_List_BinaryTree1;

import java.util.Stack;

//先序遍历，中序遍历，后序遍历 的 栈实现
public class Code05_PreInPostOrderStack {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int data) {
            this.val = data;
        }
    }


    //先序遍历
    //1.弹出节点的时候打印
    //2.压入该节点的右子节点，再压入该节点的左子节点
    //因为拿出来的时候就是先拿出左子节点打印，再拿出右子节点打印
    public void preOrder(TreeNode root) {
        if (root == null) return;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            System.out.print(node.val + " "); // 访问根节点

            if (node.right != null) {
                stack.push(node.right); // 先推入右子树
            }
            if (node.left != null) {
                stack.push(node.left);  // 后推入左子树
            }
        }
    }


    //中序遍历
    //1.当前节点为cur，cur头数，整条左边界进栈，知道遇到空节点
    //2.栈中弹节点打印，节点有辫子为cur，重新开始第1.步
    //3.栈为空的时候停止
    public void inOrder(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current); // 走到最左子节点
                current = current.left;
            }

            current = stack.pop();
            System.out.print(current.val + " "); // 访问根节点
            current = current.right; // 转向右子树
        }
    }


    //后序遍历
    //首先将根节点压入 stack1。
    //当 stack1 不为空时，执行以下步骤：
    //1.从 stack1 弹出一个节点，并将其压入 stack2。
    //2.如果该节点有左子节点，则将左子节点压入 stack1。
    //3.如果该节点有右子节点，则将右子节点压入 stack1。
    public void postOrder(TreeNode root) {
        if (root == null) return;

        //stack1 用于遍历节点。
        //stack2 用于存储按照根 -> 右子树 -> 左子树的顺序节点。
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();

        //1.stack2最先压入的头
        //2.遍历stack1的过程中，先压入stack2 的是右边节点，因为先压入stack1的是左子节点再是右子节点
        stack1.push(root);
        while (!stack1.isEmpty()) {
            TreeNode node = stack1.pop();
            stack2.push(node);

            if (node.left != null) {
                stack1.push(node.left);
            }
            if (node.right != null) {
                stack1.push(node.right);
            }
        }

        //遍历这个数组
        while (!stack2.isEmpty()) {
            TreeNode node = stack2.pop();
            System.out.print(node.val + " "); // 访问根节点
        }
    }

}
