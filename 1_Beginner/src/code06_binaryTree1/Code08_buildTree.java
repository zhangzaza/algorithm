package code06_binaryTree1;

import java.util.HashMap;
//测试链接： https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/
public class Code08_buildTree {


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {
        }
        TreeNode(int val) {
            this.val = val;
        }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


    public static TreeNode buildTree(int[] preOrder, int[] inOrder) {
        if (preOrder == null || inOrder ==null ||preOrder.length != inOrder.length) {
            return null;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inOrder.length; i++) {
            map.put(inOrder[i], i);
        }
        return g(preOrder,0,preOrder.length-1,inOrder,0,inOrder.length-1,map);

    }


    //有一颗树，先xu遍历结果是preOrder【L1.....L2】,中xu遍历【L2....R2】
    //请建出整颗树返回头节点
    private static TreeNode g(int[] preOrder, int L1, int R1, int[] inOrder, int L2, int R2, HashMap<Integer, Integer> map) {
        if (L1>R2){
            return null;
        }
        TreeNode head =new TreeNode(preOrder[L1]);
        if (L1==L2){
            return head;
        }
        int find = map.get(preOrder[L2]);
        head.left=g(preOrder,L1+1,L1+(find-L2),inOrder,L2,find-1,map);
        head.right=g(preOrder,L1+(find-L2) +1,R1,inOrder,find+1,R2,map);
        return head;
    }


}
