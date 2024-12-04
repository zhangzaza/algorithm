package code12_BinaryTree2;


//二叉树中，找到一个节点的后继节点通常是指在中序遍历中的下一个节点。要找到后继节点，我们必须考虑以下几种情况：
//1.节点有右子树： 如果节点有右子树，则后继节点是右子树中最左边的节点。
//2.节点没有右子树： 如果节点没有右子树，我们需要找到该节点的最低祖先节点，该祖先节点的左孩子是该节点的祖先。
public class Code06_InorderSuccessorNode {

    public static class BinaryTreeNode {
        int value;
        BinaryTreeNode left;
        BinaryTreeNode right;
        BinaryTreeNode parent; // 假设每个节点都有一个指向父节点的指针

        BinaryTreeNode(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
            this.parent = null;
        }
    }

    ///分情况讨论：
    ///后继节点：中序遍历中的下一个节点
    /// 1.节点有右子树： 如果节点有右子树，则后继节点是右子树中最左边的节点。
    /// 2.节点没有右子树： 如果节点没有右子树，我们需要找到该节点的最低祖先节点，并且是最先满足该祖先节点的左孩子是该节点的祖先。
    public static BinaryTreeNode getInorderSuccessor(BinaryTreeNode node) {
        if (node == null) {
            return null;
        }

        /// 情况1：节点有右子树
        if (node.right != null) {
            return findMin(node.right);
        }

        /// 情况2：节点没有右子树
        BinaryTreeNode current = node;
        BinaryTreeNode parent = node.parent;
        // 向上查找直到找到一个节点，它是其父节点的左孩子
        while (parent != null && current == parent.right) {
            current = parent;
            parent = parent.parent;
        }

        return parent;
    }

    // 辅助函数：找到该节点的最左边的子节点
    private static BinaryTreeNode findMin(BinaryTreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

}
