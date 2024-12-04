package code12_BinaryTree2;

import java.util.LinkedList;
import java.util.Queue;

//要求二叉树中最宽的层的节点数量，我们可以使用层序遍历（广度优先搜索）的方法
public class Code05_maxWidthBinaryTree {

    public static class Node{
        int value;
        Node left;
        Node right;

    }


    public static int maxWidthNoMap(Node head){
        if (head == null){
            return 0;
        }
        Queue<Node> queue =new LinkedList<>();
        queue.add(head);
        Node curEnd = head;//当前层最右节点
        Node nextEnd = null;//下一层最右节点
        int maxWidth = 0;
        int curLevelWidth = 0;//当前层的节点数
        while (!queue.isEmpty()){
            //1.弹出队列头，判断左右节点是否为空，不为空则加入队列，并更新nextEnd
            Node curNode = queue.poll();
            if (curNode.left != null){
                queue.add(curNode.left);
                nextEnd = curNode.left;
            }
            if (curNode.right != null){
                queue.add(curNode.right);
                nextEnd = curNode.right;
            }
            //2.更新当前层数节点数量，+1
            curLevelWidth++;

            ///只有该层的最后一个节点会走这一步
            //3.判断当前节点是否为当前层最右节点，
            // 如果是，则更新最大宽度，并更新当前层节点数量为0，并更新当前层最右节点为下一层最右节点
            if (curNode == curEnd){
                maxWidth = Math.max(maxWidth,curLevelWidth);
                curLevelWidth=0;
                curEnd = nextEnd;
            }
        }

        return  0;
    }


}
