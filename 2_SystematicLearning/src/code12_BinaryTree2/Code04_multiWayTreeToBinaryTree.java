package code12_BinaryTree2;

///leetCode 431 「hard」
//题目描述：
//给定一棵多叉树（N叉树），请将其转换为等价的二叉树。
//要求：
//1.多叉树的每个节点可以有多个子节点。
//2.在转换后的二叉树中，每个节点的左子节点表示原多叉树中第一个子节点，右子节点表示其兄弟节点。
//3.实现一个函数来完成这个转换，并输出转换后的二叉树的结构。


import java.util.ArrayList;
import java.util.List;

//思路；
//假设给定的多叉树结构如下：
//
//       A
//     / | \
//    B  C  D
//      / \
//     E   F
//转换后的二叉树结构应该是：
//
//       A
//      /
//     B
//      \
//       C
//      / \
//     E   D
//      \
//       F
public class Code04_multiWayTreeToBinaryTree {


    ///多叉树
    public static class NaryTreeNode {
        int value;
        List<NaryTreeNode> children;

        NaryTreeNode(int value) {
            this.value = value;
            this.children = new ArrayList<>();
        }

        NaryTreeNode(int value ,List<NaryTreeNode> children){
            this.value=value;
            this.children = children;
        }

    }

    ///二叉树
    public static class BinaryTreeNode {
        int value;
        BinaryTreeNode left;
        BinaryTreeNode right;

        BinaryTreeNode(int value){
            this.value = value;
        }

        BinaryTreeNode(int value, BinaryTreeNode left) {
            this.value = value;
        }
    }


    ///多叉树转二叉树
    public static BinaryTreeNode convert(NaryTreeNode root) {
        if (root == null){
            return null;
        }
        BinaryTreeNode head = new BinaryTreeNode(root.value);
        head.left=en(root.children);
        return head;


    }

    //多叉树转二叉树
    //思路：
    //1.拿到一个节点后，需要遍历该节点的子节点
    //2.将该节点的子节点全部放在左子节点为开头的右边界树
    //2.1.所以每次遍历都需要用到一个新的二叉树节点
    //2.2.多叉树的子节点都要放在新的二叉树节点的左子节点为开头的有边界树上「原来的第2.步」
    private static BinaryTreeNode en(List<NaryTreeNode> children) {
        BinaryTreeNode head=null;
        BinaryTreeNode cur =null;
        //2.
        for (NaryTreeNode node:children){
            //2.1
            BinaryTreeNode treeNode = new BinaryTreeNode(node.value);
            if (head==null){
                head=treeNode;
                cur=treeNode;
            }else {
                cur.right=treeNode;
                cur=treeNode;
            }
            //2.2.
            cur.left=en(node.children);
        }
        return head;
    }



    ///二叉树转多叉树
    public static NaryTreeNode naryTreeNode(BinaryTreeNode root){
        if (root==null){
            return null;
        }
        return  new NaryTreeNode(root.value,de(root.left));
    }


    //二叉树转多叉树
    //思路：
    //1.拿到这个二叉树的节点，需要将其左子节点为开头的右边界树全部放在一个集合中
    //2.在遇到每个节点的过程中，都要重复第一步
    private static List<NaryTreeNode> de(BinaryTreeNode root) {
        List<NaryTreeNode> children=new ArrayList<>();
        while (root!=null){
            NaryTreeNode node=new NaryTreeNode(root.value,de(root.left));
            children.add(node);
            root=root.right;
        }
        return children;
    }





}
