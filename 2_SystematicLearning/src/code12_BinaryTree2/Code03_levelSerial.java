package code12_BinaryTree2;

import java.util.LinkedList;
import java.util.Queue;

///分层遍历
public class Code03_levelSerial {

    public static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node (int value){
            this.value = value;
        }

    }


    ///序列化
    public static Queue<String> levelSerialize(Node head){
        Queue<String > levleQueue =new LinkedList<>();
        if (head == null){
            levleQueue.add(null);
        }else {
            levleQueue.add(String.valueOf(head.value));
            Queue<Node> helpQueue =new LinkedList<>();
            helpQueue.add(head);
            while (!helpQueue.isEmpty()){
                head=helpQueue.poll();// head 父节点

                //添加左节点
                if (head.left!=null){
                    levleQueue.add(String.valueOf(head.left.value));
                    helpQueue.add(head.left);
                }else{
                    levleQueue.add(null);
                }

                //添加右节点
                if (head.right!=null){
                    levleQueue.add(String.valueOf(head.right.value));
                    helpQueue.add(head.right);
                }else{
                    levleQueue.add(null);
                }
            }
        }
        return levleQueue;
    }


    ///反序列
    /// 注意点：
    /// 1.需要有一个辅助队列，队列中不能有null，否则无法判断是否是叶子节点
    /// 2.已经序列化的是一个队列，需要一个辅助队列来辅助反序列化
    public static Node levelDeserialize(Queue<String> levleQueue){
        if (levleQueue ==null || levleQueue.isEmpty()){
            return null;
        }
        //创造头节点
        Node head = generateNode(levleQueue.poll());
        //辅助队列
        Queue<Node> helpQueue =new LinkedList<>();

        if (head!=null){
            helpQueue.add(head);
        }

        while (!helpQueue.isEmpty()){
            Node parent = helpQueue.poll();
            //左节点
            String leftVal = levleQueue.poll();
            Node leftNode = generateNode(leftVal);
            parent.left = leftNode;
            //右节点
            String rightVal = levleQueue.poll();
            Node rightNode = generateNode(rightVal);
            parent.right = rightNode;
            //左右节点入队
            if (leftNode!=null){
                helpQueue.add(leftNode);
            }
            if (rightNode!=null){
                helpQueue.add(rightNode);
            }
        }
        return head;

    }



    //如果节点为null，返回null，否则返回节点
    public static Node generateNode(String val){
        if(val ==null){
            return null;
        }else{
            return new Node(Integer.parseInt(val));
        }
    }


}
