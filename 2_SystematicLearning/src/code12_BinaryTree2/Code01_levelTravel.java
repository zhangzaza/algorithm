package code12_BinaryTree2;


import java.util.LinkedList;
import java.util.Queue;

//层级遍历
public class Code01_levelTravel {

    public static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node (){}

    }


    public static void levelTravel(Node head) {

        if (head == null){
            return;
        }
        //双端队列
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()) {
            Node curNode = queue.poll();
            System.out.println(curNode.value);
            if (curNode.left != null) {
                queue.add(curNode.left);
            }
            if (curNode.right != null) {
                queue.add(curNode.right);
            }
        }


    }


}
