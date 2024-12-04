package code07_binaryTree2;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//测试链接 ： http://leetcode.com/problems/binary-tree-level-order-traversal-ii
//java中栈有自己的实现，但是效率还不如arraylist
public class Code01_binaryTreeOrderTraversal {


    public class TreeNode {
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

    /*
    * 1.使用队列实现【单向队列】
    * 2.当前节点排除后，就把它的子节点放到队列中
    * 3.使用循环，循环的次数：就是这次循环开始之前已经记在的queue的size，因为队列的数量是动态添加的，所以需要提前需要从队列中排出几个
    * */
    public List<List<Integer>> binaryTreeOrderTraversal(TreeNode root) {
        List<List<Integer>> linkedList = new LinkedList<>();
        if (root == null) {
            return linkedList;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();

            //需要添加到linkedList
            List<Integer> list = new ArrayList<>();

            //往数组里面添加节点
            for (int i = 0; i < size; i++) {
                TreeNode curNode = queue.poll();
                list.add(curNode.val);
                if (curNode.left != null) {
                    queue.add(curNode.left);
                }
                if (curNode.right != null) {
                    queue.add(curNode.right);
                }
            }

            //返回当层的节点
            linkedList.add(list);
        }

        return linkedList;


    }











}
